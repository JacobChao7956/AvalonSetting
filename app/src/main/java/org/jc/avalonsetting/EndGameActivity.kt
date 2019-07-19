package org.jc.avalonsetting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import kotlinx.android.synthetic.main.activity_end_game.*
import org.jc.avalonsetting.manager.initGridList
import org.jc.avalonsetting.references.GAME_10P
import org.jc.avalonsetting.references.GAME_8P
import org.jc.avalonsetting.references.Players
import org.jc.avalonsetting.viewmodel.PlayerViewModel

class EndGameActivity : AppCompatActivity() {

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_end_game)
        val spanCount = when (Players) {
            GAME_8P -> 4
            GAME_10P -> 5
            else -> 5
        }
        playerViewModel = ViewModelProviders.of(this).get(playerViewModel::class.java)
        endGameList.initGridList(this, spanCount, EndGameListAdapter())
    }
}
