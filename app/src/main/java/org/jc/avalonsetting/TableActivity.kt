package org.jc.avalonsetting

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.socks.library.KLog
import kotlinx.android.synthetic.main.activity_table.*
import kotlinx.android.synthetic.main.view_result.view.*
import org.jc.avalonsetting.data.db.entity.GameBoardEntity
import org.jc.avalonsetting.framework.BaseActivity
import org.jc.avalonsetting.viewmodel.GameBoardViewModel
import org.jc.avalonsetting.references.GAME_10P
import org.jc.avalonsetting.references.GAME_8P
import org.jc.avalonsetting.references.Players
import org.jc.avalonsetting.references.TITLE_GAME_PLAYERS

class TableActivity : BaseActivity(), View.OnClickListener {

    private lateinit var gameBoardViewmodel: GameBoardViewModel

//    private val winnerIcon by lazy { getDrawable(R.drawable.token_success) }
//    private val loserIcon by lazy { getDrawable(R.drawable.token_failed) }
//    private val goodMan by lazy { getDrawable(R.drawable.ic_player_blue_oval_48p) }
//    private val badGuy by lazy { getDrawable(R.drawable.ic_player_red_oval_48p) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_table)
        title = when (Players) {
            GAME_8P -> GAME_8P.toString() + TITLE_GAME_PLAYERS
            GAME_10P -> GAME_10P.toString() + TITLE_GAME_PLAYERS
            else -> GAME_10P.toString() + TITLE_GAME_PLAYERS
        }
        gameBoardViewmodel = ViewModelProviders.of(this).get(GameBoardViewModel::class.java)
        initObserve()
    }

    private fun initObserve() {
        gameBoardViewmodel.operation1.observe(this, Observer {
            showOperateResult(result1.resultText, it)
            result1.resultText.visibility = View.VISIBLE
        })
        gameBoardViewmodel.operation2.observe(this, Observer {
            showOperateResult(result2.resultText, it)
            result2.resultText.visibility = View.VISIBLE
        })
        gameBoardViewmodel.operation3.observe(this, Observer {
            showOperateResult(result3.resultText, it)
            result3.resultText.visibility = View.VISIBLE
        })
        gameBoardViewmodel.operation4.observe(this, Observer {
            showOperateResult(result4.resultText, it)
            result4.resultText.visibility = View.VISIBLE
        })
        gameBoardViewmodel.operation5.observe(this, Observer {
            //TODO 目前這邊的it會是null導致閃退，尚不知原因
            showOperateResult(result5.resultText, it)
            result5.resultText.visibility = View.VISIBLE
        })
    }

    private fun showOperateResult(result: TextView, operation: GameBoardEntity) {
        result.text = when (operation.id) {
            1 -> getString(R.string.mission_result_3p, operation.operator1, operation.operator2,
                    operation.operator3, operation.bad)
            2, 3 -> getString(R.string.mission_result_4p, operation.operator1, operation.operator2,
                    operation.operator3, operation.operator4, operation.bad, operation.goddess,
                    operation.asked)
            4, 5 -> getString(R.string.mission_result_5p, operation.operator1, operation.operator2,
                    operation.operator3, operation.operator4, operation.operator5, operation.bad,
                    operation.goddess, operation.asked)
            else -> ""
        }
    }

    override fun onClick(view: View) {
        val resultDialog = ResultDialog()
        resultDialog.isCancelable = false
        resultDialog.show(supportFragmentManager, "Result")
    }

    private fun showGameResult() {

    }
}
