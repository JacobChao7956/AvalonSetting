package org.jc.avalonsetting

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.title_main)
    }

    override fun onClick(view: View?) {
        when (view) {
            gameSet8 -> Players = GAME_SET_8
            gameSet10 -> Players = GAME_SET_10
        }
        startActivity(Intent(this, DrawActivity::class.java))
    }
}
