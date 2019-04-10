package org.jc.avalonsetting

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_draw.*

class DrawActivity : AppCompatActivity(), View.OnClickListener {

    private var currentPlayer = 0
    private val players = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_draw)
        title = when (Players) {
            GAME_SET_8 -> PLAYER_8
            GAME_SET_10 -> PLAYER_10
            else -> PLAYER_10
        }
        players.add(player1)
        players.add(player2)
        players.add(player3)
        players.add(player4)
        players.add(player5)
        players.add(player6)
        players.add(player7)
        players.add(player8)
        players.add(player9)
        players.add(player10)
        CHARACTERS.addAll(resources.getStringArray(when (Players) {
            GAME_SET_8 -> R.array.character_8
            GAME_SET_10 -> R.array.character_all
            else -> R.array.character_all
        }))
        //亂數排列角色
        CHARACTERS.shuffle()
    }

    override fun onClick(view: View?) {
        if (currentPlayer < 10) {
            val bundle = Bundle()
            bundle.putInt(CURRENT_PLAYER, currentPlayer)
            val fragment = CharacterFragment()
            fragment.arguments = bundle
            fragment.show(supportFragmentManager, "Character")
            if (currentPlayer == 9) {
                draw.text = getString(R.string.game_start)
            }
            players[currentPlayer].background = getDrawable(R.drawable.ic_player_black_oval_24p)
            currentPlayer += 1
        } else {
            startActivity(Intent(this, TableActivity::class.java))
            finish()
        }
    }
}
