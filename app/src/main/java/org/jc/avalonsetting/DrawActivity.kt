package org.jc.avalonsetting

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_draw.*
import org.jc.avalonsetting.framework.BaseActivity
import org.jc.avalonsetting.references.GAME_10P
import org.jc.avalonsetting.references.GAME_8P
import org.jc.avalonsetting.references.Players
import org.jc.avalonsetting.references.TITLE_GAME_PLAYERS
import org.jc.avalonsetting.viewmodel.PlayerViewModel

class DrawActivity : BaseActivity(), View.OnClickListener {

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)
        title = when (Players) {
            GAME_8P -> GAME_8P.toString() + TITLE_GAME_PLAYERS
            GAME_10P -> GAME_10P.toString() + TITLE_GAME_PLAYERS
            else -> ""
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
        playerViewModel.addNewPlayer()
    }

    override fun onClick(view: View?) {
        showCharacterCard()
    }

    private fun showCharacterCard() {
        if (playerViewModel.currentPlayer.value!! < Players) {
            val characterDialog = CharacterDialog()
            characterDialog.isCancelable = false
            characterDialog.show(supportFragmentManager, "Character")
        } else {
//            startActivity(Intent(this, TableActivity::class.java))
//            finish()
        }
    }
}