package com.example.hellopaging1.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hellopaging1.R
import com.example.hellopaging1.viewmodel.CryptocurrenciesViewModel


class ListFragment : Fragment() {

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
        val adapter = ListAdapter()
        vm.getCryptocurrencies().observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
    }
}