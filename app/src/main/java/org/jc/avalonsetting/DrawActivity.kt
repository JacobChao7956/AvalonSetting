package org.jc.avalonsetting

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_draw.*
import org.jc.avalonsetting.data.viewmodel.PlayerViewModel
import org.jc.avalonsetting.references.*

class DrawActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPlayer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)
        title = when (Players) {
            GAME_8P -> GAME_8P.toString() + TITLE_GAME_PLAYERS
            GAME_10P -> GAME_10P.toString() + TITLE_GAME_PLAYERS
            else -> GAME_10P.toString() + TITLE_GAME_PLAYERS
        }
    }

    override fun onClick(view: View?) {
        showCharacterCard()
    }

    private fun showCharacterCard() {
        if (currentPlayer < Players) {
            val bundle = Bundle()
            bundle.putInt(CURRENT_PLAYER, currentPlayer)
            val fragment = CharacterFragment()
            fragment.arguments = bundle
            fragment.show(supportFragmentManager, "Character")
            cardNote.text = String.format(getString(R.string.which_player),
                    if (currentPlayer + 1 == 10) 0 else currentPlayer + 1)
            currentPlayer += 1
        } else {
//            startActivity(Intent(this, TableActivity::class.java))
//            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            ReStartDialog(this).create().show()
            true
        } else super.onKeyDown(keyCode, event)
    }
}
