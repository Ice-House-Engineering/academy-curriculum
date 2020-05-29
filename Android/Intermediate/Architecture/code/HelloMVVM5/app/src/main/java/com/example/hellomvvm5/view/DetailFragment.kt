package com.example.hellomvvm5.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.hellomvvm5.R
import com.example.hellomvvm5.viewmodel.CryptocurrencyViewModel


class DetailFragment : Fragment() {

    private lateinit var nameTextView: TextView
    private lateinit var titleTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        val result = inflater.inflate(R.layout.fragment_detail, container, false)
        nameTextView = result.findViewById(R.id.nameTextView)
        titleTextView = result.findViewById(R.id.titleTextView)
        return result
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm : CryptocurrencyViewModel by viewModels()
        val cryptocurrencyName = DetailFragmentArgs.fromBundle(arguments!!).parameterFromListFragment
        val model = vm.getModel(cryptocurrencyName)
        model.let {
            nameTextView.text = it?.name
            titleTextView.text = it?.title
        }
    }
}