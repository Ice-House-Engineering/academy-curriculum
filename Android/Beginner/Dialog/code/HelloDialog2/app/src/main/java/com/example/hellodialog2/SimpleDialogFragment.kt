package com.example.hellodialog2

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class SimpleDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.dialog_message)
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