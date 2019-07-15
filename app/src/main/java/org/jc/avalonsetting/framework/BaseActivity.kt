package org.jc.avalonsetting.framework

import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import org.jc.avalonsetting.ReStartDialog

abstract class BaseActivity: AppCompatActivity() {

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            ReStartDialog(this).create().show()
            true
        } else super.onKeyDown(keyCode, event)
    }
}