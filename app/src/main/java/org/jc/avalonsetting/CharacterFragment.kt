package org.jc.avalonsetting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_character.*
import org.jc.avalonsetting.data.db.entity.PlayerEntity
import org.jc.avalonsetting.data.viewmodel.PlayerViewModel
import org.jc.avalonsetting.references.CURRENT_PLAYER
import androidx.lifecycle.Observer
import java.util.*

class CharacterFragment : DialogFragment() {

    private var currentPlayer = 0
    private lateinit var playerViewModel: PlayerViewModel
    private var player: PlayerEntity? = null
    // characters
    private val merlin by lazy { getString(R.string.merlin) }
    private val percival by lazy { getString(R.string.percival) }
    private val mordred by lazy { getString(R.string.mordred) }
    private val morgana by lazy { getString(R.string.morgana) }
    private val assassin by lazy { getString(R.string.assassin) }
    private val oberon by lazy { getString(R.string.oberon) }
    private val loyalty by lazy { getString(R.string.arthurs_loyalty) }
    // character icons
    private val characterIcons = ArrayList<Pair<String, Int>>()
    // info string
    private val whoIsMerlin by lazy { getString(R.string.chosen_one) }
    private val knowBadGuys by lazy { getString(R.string.omniscient) }
    private val youAreBadGuys by lazy { getString(R.string.you_are_bad_guys) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPlayer = arguments!!.getInt(CURRENT_PLAYER)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerViewModel = ViewModelProviders.of(activity!!).get(PlayerViewModel::class.java)
        //TODO playerViewModel.allPlayers.value撈出來是null
//        player = playerViewModel.allPlayers.value?.get(currentPlayer)
//        // add icon to list
        characterIcons.add(Pair(merlin, R.drawable.merlin))
        characterIcons.add(Pair(percival, R.drawable.percival))
        characterIcons.add(Pair(mordred, R.drawable.mordred))
        characterIcons.add(Pair(morgana, R.drawable.morgana))
        characterIcons.add(Pair(assassin, R.drawable.assassin))
        characterIcons.add(Pair(oberon, R.drawable.oberon))
        characterIcons.add(Pair(loyalty, R.drawable.loyalty))
        remembered.setOnClickListener { dismiss() }
    }

    override fun onResume() {
        super.onResume()
        dialog.window!!.setLayout(-1, -1)
        showCharacter()
    }

    private fun showCharacter() {
        var showInfo = ""
        when (player?.cName) {
            merlin -> {
                val perspective = arrayOf(assassin, morgana, oberon)
                showInfo = "$merlin\n" + whoYouAre(merlin, perspective)
            }
            percival -> {
                val perspective = arrayOf(merlin, morgana)
                showInfo = "$percival\n" + whoYouAre(percival, perspective)
            }
            mordred -> {
                val perspective = arrayOf(assassin, morgana)
                showInfo = "$mordred\n" + whoYouAre(mordred, perspective)
            }
            morgana -> {
                val perspective = arrayOf(assassin, mordred)
                showInfo = "$morgana\n" + whoYouAre(morgana, perspective)
            }
            assassin -> {
                val perspective = arrayOf(morgana, mordred)
                showInfo = "$assassin\n" + whoYouAre(assassin, perspective)
            }
            oberon -> showInfo = oberon
            loyalty -> showInfo = loyalty
        }
        characterInfo.text = showInfo
    }

    private fun whoYouAre(cName: String, perspective: Array<String>): String {
        characterIcons.forEach {
            if (it.first.contains(cName)) {
                characterIcon.background = context?.getDrawable(it.second)
            }
        }
        val saw = ArrayList<Int>()
        playerViewModel.allPlayers.value!!.forEach { player ->
            perspective.forEach {
                if (player.cName == it) {
                    var id = player.id + 1
                    if (id == 10) id = 0
                    saw.add(id)
                }
            }
        }
        saw.sort()
        return when (cName) {
            merlin -> String.format(knowBadGuys, saw[0], saw[1], saw[2])
            percival -> String.format(whoIsMerlin, saw[0], saw[1])
            else -> String.format(youAreBadGuys, saw[0], saw[1])
        }
    }
}