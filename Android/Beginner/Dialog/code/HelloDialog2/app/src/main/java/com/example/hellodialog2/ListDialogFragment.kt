package com.example.hellodialog2

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class ListDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val arrayOfStrings = arrayOf("Bitcoin", "Ethereum", "Monero", "Bitcoin Case")
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder
                .setItems(arrayOfStrings) { dialog, id ->
                    Log.d("alert-dialog", "Item pressed " + id.toString())
                }
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.positive_button_message) { dialog, id ->
                    Log.d("alert-dialog", "OK Button Pressed")
                }
                .setNegativeButton(R.string.negative_button_message) { dialog, id ->
                    Log.d("alert-dialog", "Cancel Button Pressed")
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}