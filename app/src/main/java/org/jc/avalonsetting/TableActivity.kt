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
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.mission_result))
                .setPositiveButton(getString(R.string.mission_completed), this)
                .setNegativeButton(getString(R.string.mission_failed), this)
                .create()
    }
    private val winnerIcon by lazy {
        getDrawable(R.drawable.token_success)
    }
    private val loserIcon by lazy {
        getDrawable(R.drawable.token_failed)
    }
    private val goodMan by lazy {
        getDrawable(R.drawable.ic_player_blue_oval_48p)
    }
    private val badGuy by lazy {
        getDrawable(R.drawable.ic_player_red_oval_48p)
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
                BUTTON_POSITIVE -> winnerIcon
                BUTTON_NEGATIVE -> loserIcon
                else -> gameBoard[game].background
            }
            gameBoard[game].isClickable = false

            if (!checkWinner()) {
                if (gameBoard[game] == game5) {
                    showGameResult()
                }
                game += 1
                if (game < gameBoard.size) {
                    gameBoard[game].isClickable = true
                }
            }
        }
    }

    private fun checkWinner(): Boolean {
        var win = 0
        var lose = 0
        repeat(game) {
            when (gameBoard[game].background) {
                winnerIcon -> win += 1
                else -> lose += 1
            }
        }
        if (win == 3 || lose == 3) {
            gameBoard.forEach {
                it.isClickable = false
            }
            game = 5
            showGameResult()
            return true
        }
        return false
    }

    private fun showGameResult() {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(getString(R.string.end_game))
                .setPositiveButton(getString(android.R.string.yes)) { _, _ ->
                    repeat(players.size) {
                        players[it].text = CHARACTERS[it].substring(0, 1)
                        players[it].setTextColor(getColor(android.R.color.white))
                        when (players[it].text) {
                            "莫", "魔", "奧", "刺" -> players[it].background = badGuy
                            else -> players[it].background = goodMan
                        }
                    }
                }
                .setCancelable(false)
                .create()
                .show()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            ReStartDialog(this).create().show()
            true
        } else super.onKeyDown(keyCode, event)
    }
}
