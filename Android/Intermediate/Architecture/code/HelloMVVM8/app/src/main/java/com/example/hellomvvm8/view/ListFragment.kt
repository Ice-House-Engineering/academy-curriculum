package com.example.hellomvvm8.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hellomvvm8.R
import com.example.hellomvvm8.model.CryptocurrencyModel
import com.example.hellomvvm8.viewmodel.CryptocurrenciesViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject


class ListFragment : Fragment() {

    val vm : CryptocurrenciesViewModel by activityViewModels()
    private lateinit var recyclerView : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.list)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
        }
        view.findViewById<FloatingActionButton>(R.id.floatingButton).setOnClickListener {
            createDialog()
        }
        recyclerView.adapter = ListAdapter(emptyList()) {}
        fillDataToRecyclerView()
    }

    private fun fillDataToRecyclerView() {
        vm.getCryptocurrencies { list ->
            recyclerView.adapter = ListAdapter(list) { navigate(it) }
        }
    }

    fun createDialog() {
        val compositeDisposable = CompositeDisposable()

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

        val publishSubjectError = PublishSubject.create<Boolean>()

        dialog?.setOnShowListener {
            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)

            vm.validatePositiveButton(RxTextView.textChanges(nameEditText!!), RxTextView.textChanges(titleEditText!!)) {
                positiveButton.isEnabled = it.first and it.second
            }

            val nameStringObservable = RxTextView.textChanges(nameEditText!!)
            val titleStringObservable = RxTextView.textChanges(titleEditText!!)

            val nameValidation = vm.setupValidateCryptocurrencyNameDoesNotExist(RxView.clicks(positiveButton), nameStringObservable)

            vm.postErrorValidateCryptocurrencyNameDoesNotExist(nameValidation) {
                publishSubjectError.onNext(true)
            }

            vm.postValidateToCreateCryptocurrency(nameValidation,
                                                  nameStringObservable,
                                                  titleStringObservable, {
                    publishSubjectError.onNext(false)
                it }, {
                    fillDataToRecyclerView()
                    publishSubjectError.onNext(false)
                    dialog.dismiss()
                })

            vm.setupCancelDialogButton(RxView.clicks(negativeButton!!)) {
                dialog.dismiss()
            }

        }

        publishSubjectError.subscribe {
            if (it) {
                errorTextView?.visibility = View.VISIBLE
            } else {
                errorTextView?.visibility = View.GONE
            }
        }.addTo(compositeDisposable)

        dialog?.show()

        nameEditText = dialog?.findViewById(R.id.name)
        titleEditText = dialog?.findViewById(R.id.title)
        errorTextView = dialog?.findViewById(R.id.error)
    }

    fun navigate(model: CryptocurrencyModel) {
        findNavController().navigate(ListFragmentDirections.goToDetailFragment(model.name))
    }
}