package org.jc.avalonsetting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_main.*
import org.jc.avalonsetting.data.viewmodel.PlayerViewModel
import org.jc.avalonsetting.references.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.title_main)
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
    }

    override fun onClick(view: View?) {
        when (view) {
            gameSet8 -> Players = GAME_8P
            gameSet10 -> Players = GAME_10P
        }
        initPlayers()
        val intent = Intent(this, DrawActivity::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * 初始化玩家人數，放入角色資訊
     */
    private fun initPlayers() {
        val characters = ArrayList<String>()
        characters.addAll(resources.getStringArray(when (Players) {
            GAME_8P -> R.array.character_8
            GAME_10P -> R.array.character_all
            else -> R.array.character_all
        }))
        playerViewModel.addNewPlayer(characters)
    }
}
