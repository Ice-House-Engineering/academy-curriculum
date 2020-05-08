package com.example.hellofragment5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.transaction
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm : CryptocurrenciesListViewModel = ViewModelProviders.of(this).get()
        view.findViewById<RecyclerView>(R.id.list).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = CryptocurrencyListAdapter(vm.cryptocurrencies) { navigate(it) }
        }
    }

    fun navigate(model: CryptocurrencyModel) {
        fragmentManager?.transaction {
            replace(android.R.id.content, DetailFragment.newInstance(model.id))
            addToBackStack(null)
        }
    }
}