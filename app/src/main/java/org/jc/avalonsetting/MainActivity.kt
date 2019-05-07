package org.jc.avalonsetting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
        var players = 0
        when (view) {
            gameSet8 -> players = GAME_8P
            gameSet10 -> players = GAME_10P
        }
        val intent = Intent(this, DrawActivity::class.java)
        intent.putExtra(GAME_PLAYERS, players)
        startActivity(intent)
    }
}
