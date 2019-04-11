package org.jc.avalonsetting

import android.content.DialogInterface
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_table.*
import kotlinx.android.synthetic.main.view_table.*

class TableActivity : AppCompatActivity(), View.OnClickListener, DialogInterface.OnClickListener {

    private var game = 0
    private val gameBoard by lazy {
        arrayOf(game1, game2, game3, game4, game5)
    }
    private val players by lazy {
        arrayOf(player1, player2, player3, player4, player5,
                player6, player7, player8, player9, player10)
    }
    private val resultDialog by lazy {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.mission_result))
                .setPositiveButton(getString(R.string.mission_completed), this)
                .setNegativeButton(getString(R.string.mission_failed), this)
                .create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        title = when (Players) {
            GAME_SET_8 -> PLAYER_8
            GAME_SET_10 -> PLAYER_10
            else -> PLAYER_10
        }
    }

    override fun onClick(view: View) {
        resultDialog.show()
    }

    override fun onClick(dialog: DialogInterface, which: Int) {
        if (game < gameBoard.size) {
            gameBoard[game].background = when (which) {
                BUTTON_POSITIVE -> getDrawable(R.drawable.ic_player_blue_oval_48p)
                BUTTON_NEGATIVE -> getDrawable(R.drawable.ic_player_red_oval_48p)
                else -> gameBoard[game].background
            }
            gameBoard[game].text = ""
            gameBoard[game].isClickable = false
            if (gameBoard[game] == game5) {
                showGameResult()
            }
            game += 1
            if (game < gameBoard.size) {
                gameBoard[game].isClickable = true
            }
        }
    }

    private fun showGameResult() {
        repeat(players.size) {
            players[it].text = CHARACTERS[it]
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            ReStartDialog(this).create().show()
            true
        } else super.onKeyDown(keyCode, event)
    }
}
