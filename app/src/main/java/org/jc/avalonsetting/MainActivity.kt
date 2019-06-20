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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.title_main)
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
