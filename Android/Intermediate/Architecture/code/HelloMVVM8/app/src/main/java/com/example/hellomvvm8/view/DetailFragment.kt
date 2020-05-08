package com.example.hellomvvm8.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hellomvvm8.R
import com.example.hellomvvm8.viewmodel.CryptocurrenciesViewModel


class DetailFragment : Fragment() {

    private lateinit var nameTextView: TextView
    private lateinit var titleTextView: TextView

    val vm : CryptocurrenciesViewModel by activityViewModels()

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
        val cryptocurrencyName = DetailFragmentArgs.fromBundle(arguments!!).parameterFromListFragment
        vm.getCryptocurrency(cryptocurrencyName) { model ->
            nameTextView.text = model.name
            titleTextView.text = model.title
        }
    }
}