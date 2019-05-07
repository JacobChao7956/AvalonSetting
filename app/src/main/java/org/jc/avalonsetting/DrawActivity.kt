package org.jc.avalonsetting

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_draw.*
import org.jc.avalonsetting.data.GameBoard

class DrawActivity : AppCompatActivity(), View.OnClickListener {
    private val gameBoard = GameBoard()

    private var currentPlayer = 0
    private val players = ArrayList<TextView>()
//    private val playerObjs by lazy {
//        arrayOf(player1, player2, player3, player4, player5,
//                player6, player7, player8, player9, player10)
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)
        title = when (Players) {
            GAME_8P -> PLAYER_8
            GAME_10P -> PLAYER_10
            else -> PLAYER_10
        }
//        playerObjs.forEach {
//            players.add(it)
//        }
//        CHARACTERS.addAll(resources.getStringArray(when (Players) {
//            GAME_8P -> R.array.character_8
//            GAME_10P -> R.array.character_all
//            else -> R.array.character_all
//        }))
//        //填入玩家數量
//        repeat(CHARACTERS.size) {
//            players[it].visibility = VISIBLE
//            players[it].text = when (Players) {
//                GAME_8P -> if (it < 7) (it + 1).toString() else "0"
//                GAME_10P -> if (it < 9) (it + 1).toString() else "0"
//                else -> "0"
//            }
//        }
//        //亂數排列角色
//        CHARACTERS.shuffle()
//        if (Debug.isDebuggerConnected()) {
//            KLog.d(CHARACTERS)
//        }
    }

    override fun onClick(view: View?) {
//        showCharacterCard()
    }

    private fun showCharacterCard() {
        if (currentPlayer < 10) {
            val bundle = Bundle()
            bundle.putInt(CURRENT_PLAYER, currentPlayer)
            val fragment = CharacterFragment()
            fragment.arguments = bundle
            fragment.show(supportFragmentManager, "Character")
            if (currentPlayer == 9) {
                cardback.background = getDrawable(R.drawable.shild)
            }
            players[currentPlayer].background = getDrawable(R.drawable.ic_player_black_oval_48p)
            currentPlayer += 1
        } else {
            startActivity(Intent(this, TableActivity::class.java))
            finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK && event.repeatCount == 0) {
            ReStartDialog(this).create().show()
            true
        } else super.onKeyDown(keyCode, event)
    }
}
