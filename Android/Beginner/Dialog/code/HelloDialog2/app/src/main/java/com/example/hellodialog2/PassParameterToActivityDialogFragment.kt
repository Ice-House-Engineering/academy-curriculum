package com.example.hellodialog2

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class PassParameterToActivityDialogFragment : DialogFragment() {

    private lateinit var listener: PassParameterToActivityListener

    interface PassParameterToActivityListener {
        fun onPositiveButtonClick(dialog: DialogFragment)
        fun onNegativeButtonClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = context as PassParameterToActivityListener
        } catch (e: ClassCastException) {
            throw java.lang.ClassCastException((context.toString() + " must implement PassParameterToActivityListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.positive_button_message) { dialog, id ->
                    listener.onPositiveButtonClick(this)
                }
                .setNegativeButton(R.string.negative_button_message) { dialog, id ->
                    listener.onNegativeButtonClick(this)
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}