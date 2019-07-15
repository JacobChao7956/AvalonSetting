package org.jc.avalonsetting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.get
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_main.*
import org.jc.avalonsetting.framework.BaseActivity
import org.jc.avalonsetting.viewmodel.GameBoardViewModel
import org.jc.avalonsetting.viewmodel.PlayerViewModel
import org.jc.avalonsetting.references.*

class MainActivity : BaseActivity(), View.OnClickListener {

    private lateinit var gameBoardViewmodel: GameBoardViewModel
    private lateinit var playerViewModel: PlayerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.title_main)
        playerViewModel = ViewModelProviders.of(this).get(PlayerViewModel::class.java)
        gameBoardViewmodel = ViewModelProviders.of(this).get(GameBoardViewModel::class.java)
        //本地資料庫資料初始化
        playerViewModel.deleteAll()
        gameBoardViewmodel.initOperations()
    }

    override fun onClick(view: View?) {
        when (view) {
            gameSet8 -> Players = GAME_8P
            gameSet10 -> Players = GAME_10P
        }
        val intent = Intent(this, DrawActivity::class.java)
        startActivity(intent)
        finish()
    }
}
