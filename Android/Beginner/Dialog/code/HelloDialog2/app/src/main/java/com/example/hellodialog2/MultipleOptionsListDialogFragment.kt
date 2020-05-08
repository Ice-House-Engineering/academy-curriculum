package com.example.hellodialog2

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class MultipleOptionsListDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val selectedItems = ArrayList<Int>()
            builder
                .setMultiChoiceItems(R.array.dialog_options, null) { dialog, which, isChecked ->
                    if (isChecked) {
                        selectedItems.add(which)
                    } else if (selectedItems.contains(which)) {
                        selectedItems.remove(Integer.valueOf(which))
                    }
                }
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.positive_button_message) { dialog, id ->
                    Log.d("alert-dialog", "OK Button Pressed")
                    selectedItems.forEach { item ->
                        Log.d("alert-dialog", item.toString())
                    }
                }
                .setNegativeButton(R.string.negative_button_message) { dialog, id ->
                    Log.d("alert-dialog", "Cancel Button Pressed")
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}