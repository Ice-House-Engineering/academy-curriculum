package com.example.hellodialog2

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Switch
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class CustomDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)

            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.custom_dialog, null)
            val usernameEditText = view.findViewById<EditText>(R.id.username)
            val passwordEditText = view.findViewById<EditText>(R.id.password)
            val rememberPasswordSwitch = view.findViewById<Switch>(R.id.rememberPassword)

            builder.setView(view)
                .setTitle(R.string.dialog_title)
                .setPositiveButton(R.string.positive_button_message) { dialog, id ->
                    Log.d("alert-dialog", "OK Button Pressed")
                    Log.d("alert-dialog", usernameEditText.text.toString())
                    Log.d("alert-dialog", passwordEditText.text.toString())
                    Log.d("alert-dialog", rememberPasswordSwitch.isChecked.toString())
                }
                .setNegativeButton(R.string.negative_button_message) { dialog, id ->
                    Log.d("alert-dialog", "Cancel Button Pressed")
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}