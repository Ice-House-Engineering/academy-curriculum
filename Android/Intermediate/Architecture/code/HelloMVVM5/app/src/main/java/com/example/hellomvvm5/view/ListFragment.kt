package com.example.hellomvvm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hellomvvm5.R
import com.example.hellomvvm5.model.CryptocurrencyModel
import com.example.hellomvvm5.viewmodel.CryptocurrenciesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class ListFragment : Fragment() {

    val vm : CryptocurrenciesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ListAdapter(vm.getCryptocurrencies().value!!) { navigate(it) }
        }
        view.findViewById<FloatingActionButton>(R.id.floatingButton).setOnClickListener {
            createDialog()
        }
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

        dialog?.setOnShowListener {
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            positiveButton.setOnClickListener {
                val name = nameEditText?.text.toString()
                val title = titleEditText?.text.toString()
                vm.validateCryptocurrencies(name, title)
                if (!vm.isError().value!!) {
                    vm.addToCryptocurrencies(name, title)
                    dialog.dismiss()
                }
            }
            val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
            negativeButton.setOnClickListener {
                vm.clearError()
                dialog.dismiss()
            }
        }

        dialog?.show()

        nameEditText = dialog?.findViewById(R.id.name)
        titleEditText = dialog?.findViewById(R.id.title)

        val errorTextView = dialog?.findViewById<TextView>(R.id.error)
        val cryptocurrencyObserver = Observer<Boolean> { error ->
            if (error) {
                errorTextView?.visibility = View.VISIBLE
            } else {
                errorTextView?.visibility = View.GONE
            }
        }
        vm.isError().observe(viewLifecycleOwner, cryptocurrencyObserver)
    }

    fun navigate(model: CryptocurrencyModel) {
        findNavController().navigate(ListFragmentDirections.goToDetailFragment(model.name))
    }
}