package com.example.helloviewpager1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class TextViewFragment : Fragment() {
    private val NUMBER = "number"
    private lateinit var textView : TextView

    companion object {
        fun newInstance(number : Int) = TextViewFragment().apply {
            arguments = Bundle().apply { putInt(NUMBER, number) }
        }

        fun getTitle(context: Context, number: Int) : String {
            return String.format(context.getString(R.string.text_view_string), number)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) : View? {
        val result = inflater.inflate(R.layout.full_text_view, container, false)
        textView = result.findViewById(R.id.text_view)
        return result
    }

    override fun onViewCreated(view : View, savedInstanceState : Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textView.text = String.format(resources.getString(R.string.text_view_string), arguments!!.getInt(NUMBER))
    }
}