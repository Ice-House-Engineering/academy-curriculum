package com.example.hellomvvm6.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hellomvvm6.R
import com.example.hellomvvm6.model.CryptocurrencyModel
import com.example.hellomvvm6.viewmodel.CryptocurrenciesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class ListFragment : Fragment(), CoroutineScope by MainScope() {

    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    val vm : CryptocurrenciesViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
        view.findViewById<FloatingActionButton>(R.id.floatingButton).setOnClickListener {
            createDialog()
        }
        vm.getCryptocurrencies().observe(viewLifecycleOwner, Observer { list ->
            list?.let { recyclerView.adapter = ListAdapter(list) { navigate(it) } }
        })
    }

    fun createDialog() {
        val dialog = activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater

            val view = inflater.inflate(R.layout.create_dialog, null)

            builder.setView(view)
                .setPositiveButton(R.string.dialog_ok, null)
                .setNegativeButton(R.string.dialog_cancel, null)
            builder.create()
        }

        var nameEditText : EditText? = null
        var titleEditText : EditText? = null
        var errorTextView : TextView? = null

        dialog?.setOnShowListener {
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            positiveButton?.setOnClickListener {
                val name = nameEditText?.text.toString()
                val title = titleEditText?.text.toString()
                if (vm.validateNameAndTitle(name, title)) {
                    launch {
                        if (vm.validateCryptocurrencyName(name)) {
                            vm.addToCryptocurrencies(name, title)
                            withContext(Dispatchers.Main) {
                                onResult(true, dialog, errorTextView!!)
                            }
                        } else {
                            withContext(Dispatchers.Main) {
                                onResult(false, dialog, errorTextView!!)
                            }
                        }
                    }
                } else {
                    errorTextView?.visibility = View.VISIBLE
                }
            }
            negativeButton?.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog?.show()

        nameEditText = dialog?.findViewById(R.id.name)
        titleEditText = dialog?.findViewById(R.id.title)
        errorTextView = dialog?.findViewById(R.id.error)
    }

    fun onResult(result: Boolean, dialog: AlertDialog, errorTextView: TextView) {
        if (result) {
            errorTextView.visibility = View.GONE
            dialog.dismiss()
        } else {
            errorTextView.visibility = View.VISIBLE
        }
    }

    fun navigate(model: CryptocurrencyModel) {
        findNavController().navigate(ListFragmentDirections.goToDetailFragment(model.name))
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }
}