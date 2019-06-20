package org.jc.avalonsetting.framework

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    override fun onDestroy() {
        super.onDestroy()
    }
}