package org.jc.avalonsetting

import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_POSITIVE
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity

class ReStartDialog(private val activity: AppCompatActivity) : AlertDialog.Builder(activity), DialogInterface.OnClickListener {

    override fun create(): AlertDialog {
        setTitle(R.string.app_name)
        setMessage(context.getString(R.string.restart_game))
        setPositiveButton(context.getString(android.R.string.yes), this)
        setNegativeButton(context.getString(android.R.string.no), this)
        return super.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        if (BUTTON_POSITIVE == which) {
            Players = 0
            CHARACTERS.clear()
            activity.finish()
        }
    }
}