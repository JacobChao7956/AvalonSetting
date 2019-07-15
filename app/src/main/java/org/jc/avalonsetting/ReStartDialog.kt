package org.jc.avalonsetting

import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity

class ReStartDialog(private val activity: FragmentActivity) : AlertDialog.Builder(activity), DialogInterface.OnClickListener {

    override fun create(): AlertDialog {
        setTitle(R.string.app_name)
        setMessage(context.getString(R.string.restart_game))
        setPositiveButton(context.getString(android.R.string.yes), this)
        setNegativeButton(context.getString(android.R.string.no), this)
        return super.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        if (BUTTON_POSITIVE == which) {
            activity.startActivity(Intent(activity, MainActivity::class.java))
            activity.finish()
        }
    }
}