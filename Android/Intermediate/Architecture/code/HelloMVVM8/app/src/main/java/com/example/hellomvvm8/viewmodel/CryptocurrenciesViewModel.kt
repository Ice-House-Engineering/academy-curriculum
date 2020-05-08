package com.example.hellomvvm8.viewmodel

import androidx.lifecycle.ViewModel
import com.example.hellomvvm8.model.CryptocurrencyAPI
import com.example.hellomvvm8.model.CryptocurrencyModel
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.schedulers.Schedulers
import java.util.Locale
import java.util.concurrent.TimeUnit


class CryptocurrenciesViewModel: ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    fun getCryptocurrencies(callback: (List<CryptocurrencyModel>) -> Unit) {
        CryptocurrencyAPI.downloadCryptocurrenciesList()
            .map { list ->
                val cryptocurrencyModelList: MutableList<CryptocurrencyModel> = mutableListOf()
                for ((key, value) in list) {
                    val cryptocurrencyModel = CryptocurrencyModel(key, value["title"])
                    cryptocurrencyModelList.add(cryptocurrencyModel)
                }
                cryptocurrencyModelList
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
            .addTo(compositeDisposable)
    }

    fun getCryptocurrency(name: String, callback: (CryptocurrencyModel) -> Unit) {
        CryptocurrencyAPI.getCryptocurrency(name.toUpperCase(Locale.getDefault()))
            .map { item ->
                CryptocurrencyModel(item.getValue("name"), item["title"])
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
            .addTo(compositeDisposable)
    }

    fun createCryptocurrency(name :String, title: String) {
        CryptocurrencyAPI.createCryptocurrency(name.toUpperCase(Locale.getDefault()), title)
    }

    fun validateCryptocurrencyNameDoesNotExist(name : String) : Boolean {
        return CryptocurrencyAPI.validateCryptocurrencyNameDoesNotExist(name.toUpperCase(Locale.getDefault()))
    }

    fun setupValidateCryptocurrencyNameDoesNotExist(buttonObservable: Observable<Any>,
                                                    textObservable: Observable<CharSequence>) : Observable<Boolean> {
        return buttonObservable
            .debounce(500, TimeUnit.MILLISECONDS)
            .withLatestFrom(textObservable)
            .map {
                val name = it.second.toString()
                this.validateCryptocurrencyNameDoesNotExist(name)
            }
            .share()
    }

    fun postErrorValidateCryptocurrencyNameDoesNotExist(valObservable: Observable<Boolean>, callback: (Boolean) -> Unit) {
        valObservable
            .filter {
                !it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
            .addTo(compositeDisposable)
    }

    fun postValidateToCreateCryptocurrency(valObservable: Observable<Boolean>,
                                           nameStringObservable: Observable<CharSequence>,
                                           titleStringObservable: Observable<CharSequence>,
                                           successValidationCallback: (Boolean) -> Boolean,
                                           postSuccessCallback: (Unit) -> Unit) {
        valObservable
            .filter {
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .map(successValidationCallback)
            .observeOn(Schedulers.io())
            .withLatestFrom(nameStringObservable)
            .withLatestFrom(titleStringObservable)
            .map {
                this.createCryptocurrency(it.first.second.toString(), it.second.toString())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(postSuccessCallback)
            .addTo(compositeDisposable)
    }

    fun setupCancelDialogButton(observable: Observable<Any>, callback: (Any) -> Unit) {
        observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callback)
            .addTo(compositeDisposable)
    }

    fun validatePositiveButton(textObservable1: Observable<CharSequence>, textObservable2: Observable<CharSequence>, callback: (Pair<Boolean, Boolean>) -> Unit) {
        val textObservableBool1 = textObservable1.map {
            it.isNotEmpty()
        }
        val textObservableBool2 = textObservable2.map {
            it.isNotEmpty()
        }
        Observables.combineLatest(textObservableBool1, textObservableBool2).subscribe(callback).addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}