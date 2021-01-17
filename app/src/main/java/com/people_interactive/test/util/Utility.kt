package com.gem.bridgeinspection.util

import android.app.AlertDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.people_interactive.test.AppController
import com.people_interactive.test.MainActivity
import com.people_interactive.test.R

object Utility
{
    fun showAlert(strMessage: String?, context: Context) {
        var builder = AlertDialog.Builder(context)
        builder.setTitle(R.string.app_name)
        builder.setMessage(strMessage)

        builder.setPositiveButton("OK") { dialogInterface, which ->
            dialogInterface.dismiss()
            (context as MainActivity).finish()
        }

        var alertDialog = builder.create()
        alertDialog.show()
    }

    fun hideKeyboard(view: View) {
        val imm =
            AppController.appContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}