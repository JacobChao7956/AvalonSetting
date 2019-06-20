package org.jc.avalonsetting

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_draw.*
import org.jc.avalonsetting.data.viewmodel.PlayerViewModel
import org.jc.avalonsetting.references.*

class DrawActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)
        title = when (Players) {
            GAME_8P -> GAME_8P.toString() + TITLE_GAME_PLAYERS
            GAME_10P -> GAME_10P.toString() + TITLE_GAME_PLAYERS
            else -> GAME_10P.toString() + TITLE_GAME_PLAYERS
        }
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        playerViewModel.allPlayers.observe(this, Observer {
            playerViewModel.players = playerViewModel.allPlayers.value!!
        })
        playerViewModel.currentPlayer.observe(this, Observer {
            val currentPlayer =
                    if (playerViewModel.currentPlayer.value!! + 1 == 10) 0
                    else playerViewModel.currentPlayer.value!! + 1
            if (playerViewModel.currentPlayer.value!! < Players) {
                cardNote.text = String.format(getString(R.string.which_player), currentPlayer)
            }
        })
        val characters = ArrayList<String>()
        characters.addAll(resources.getStringArray(when (Players) {
            GAME_8P -> R.array.character_8
            GAME_10P -> R.array.character_all
            else -> R.array.character_all
        }))
        playerViewModel.addNewPlayer(characters)
    }

    override fun onClick(view: View?) {
        showCharacterCard()
    }

    private fun showCharacterCard() {
        if (playerViewModel.currentPlayer.value!! < Players) {
            CharacterFragment().show(supportFragmentManager, "Character")
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

    override fun onDestroy() {
        Players = 0
        playerViewModel.deleteAll()
        super.onDestroy()
    }
}
