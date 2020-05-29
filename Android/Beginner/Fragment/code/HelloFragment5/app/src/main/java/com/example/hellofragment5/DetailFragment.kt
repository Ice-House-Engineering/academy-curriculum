package com.example.hellofragment5

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get


class DetailFragment : Fragment() {

    private val ARG_CRYPTOCURRENCY_ID = "cryptocurrencyId"
    private lateinit var nameTextView: TextView
    private lateinit var urlTextView: TextView
    private lateinit var createdOnTextView: TextView

    companion object {
        fun newInstance(cryptocurrencyId: Int) = DetailFragment().apply {
            arguments = Bundle().apply { putInt(ARG_CRYPTOCURRENCY_ID, cryptocurrencyId) }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        val result = inflater.inflate(R.layout.fragment_detail, container, false)
        nameTextView = result.findViewById(R.id.nameTextView)
        urlTextView = result.findViewById(R.id.urlTextView)
        createdOnTextView = result.findViewById(R.id.createdOnTextView)
        return result
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vm: DetailViewModel = ViewModelProviders.of(this).get()
        val model = vm.getModel(arguments!!.getInt(ARG_CRYPTOCURRENCY_ID))
        model.let {
            nameTextView.text = it!!.name
            urlTextView.text = it.url
            createdOnTextView.text = it.createdOn.toString()
        }
    }
}