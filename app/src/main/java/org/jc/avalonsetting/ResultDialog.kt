package org.jc.avalonsetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.forEach
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.chip.Chip
import kotlinx.android.synthetic.main.dialog_result.*
import kotlinx.android.synthetic.main.dialog_result.view.*
import org.jc.avalonsetting.viewmodel.GameBoardViewModel
import org.jc.avalonsetting.references.GAME_8P
import org.jc.avalonsetting.references.Players
import java.util.*

class ResultDialog : DialogFragment(), View.OnClickListener {

    private lateinit var gameBoardViewmodel: GameBoardViewModel
    private var operate = 0
    private val players = ArrayList<Int>(10)
    private val operators = ArrayList<Int>(5)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        gameBoardViewmodel = ViewModelProviders.of(activity!!).get(GameBoardViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.dialog_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        operate = gameBoardViewmodel.operate.value!!
        operationChoose.chip1.text = String.format(getString(R.string.which_player), "1")
        operationChoose.chip2.text = String.format(getString(R.string.which_player), "2")
        operationChoose.chip3.text = String.format(getString(R.string.which_player), "3")
        operationChoose.chip4.text = String.format(getString(R.string.which_player), "4")
        operationChoose.chip5.text = String.format(getString(R.string.which_player), "5")
        operationChoose.chip6.text = String.format(getString(R.string.which_player), "6")
        operationChoose.chip7.text = String.format(getString(R.string.which_player), "7")
        operationChoose.chip8.text = String.format(getString(R.string.which_player), "8")

        val whoIsGoddess: Int
        when (Players) {
            GAME_8P -> {
                operationChoose.chip9.visibility = GONE
                operationChoose.chip10.visibility = GONE
                whoIsGoddess = R.array.who_is_goddess_8p
            }
            else -> {
                operationChoose.chip9.text = String.format(getString(R.string.which_player), "9")
                operationChoose.chip10.text = String.format(getString(R.string.which_player), "10")
                whoIsGoddess = R.array.who_is_goddess_10p
            }
        }
        val spinnerAdapter = ArrayAdapter
                .createFromResource(context!!, whoIsGoddess, android.R.layout.simple_spinner_item)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        howManyBad.adapter = spinnerAdapter
        goddessFrom.adapter = spinnerAdapter
        goddessTo.adapter = spinnerAdapter
        conform.setOnClickListener(this)
        cancel.setOnClickListener(this)
        repeat(operators.size) { operators[it] = -1 }
    }

    override fun onResume() {
        super.onResume()
        dialog.window!!.setLayout(-1, -1)
    }

    override fun onClick(v: View?) {
        when (v) {
            conform -> {
                when (operate) {
                    1 -> {
                        if (3 != chipChecked()) Toast.makeText(activity!!,
                                String.format(getString(R.string.wrong_operator), 3),
                                Toast.LENGTH_SHORT).show()
                        else updateOperation()
                    }
                    2, 3 -> {
                        if (4 != chipChecked()) Toast.makeText(activity!!,
                                String.format(getString(R.string.wrong_operator), 4),
                                Toast.LENGTH_SHORT).show()
                        else updateOperation()
                    }
                    4, 5 -> {
                        if (5 != chipChecked()) Toast.makeText(activity!!,
                                String.format(getString(R.string.wrong_operator), 5),
                                Toast.LENGTH_SHORT).show()
                        else updateOperation()
                    }
                }
            }
            cancel -> dismiss()
        }
    }

    private fun chipChecked(): Int {
        players.clear()
        repeat(players.size) { players[it] = -1 }
        var currentOperator = 0
        operationChoose.forEach {
            val chip = it as Chip
            if (chip.isChecked) {
                currentOperator += 1
                players[chip.tag as Int] = chip.tag as Int
            }
        }
        return currentOperator
    }

    private fun updateOperation() {
        repeat(operators.size) {
            for (player in 0 until players.size) {
                if (-1 != player) {
                    operators[it] = player
                    players[player] = -1
                    break
                }
            }
        }
        gameBoardViewmodel.updateOperation(
                1 > howManyBad.selectedItem as Int,
                operators[0], operators[1], operators[2], operators[3], operators[4],
                howManyBad.selectedItem as Int,
                goddessFrom.selectedItem as Int,
                goddessTo.selectedItem as Int
        )
    }
}