package org.jc.avalonsetting

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : DialogFragment() {

    private var currentPlayer = 0
    private val whoIsMerlin by lazy { getString(R.string.chosen_one) }
    private val knowBadGuys by lazy { getString(R.string.omniscient) }
    //Characters
    private val merlin by lazy { getString(R.string.merlin) }
    private val percival by lazy { getString(R.string.percival) }
    private val mordred by lazy { getString(R.string.mordred) }
    private val morgana by lazy { getString(R.string.morgana) }
    private val assassin by lazy { getString(R.string.assassin) }
    private val oberon by lazy { getString(R.string.oberon) }
    private val arthursLoyalty by lazy { getString(R.string.arthurs_loyalty) }
    //Field of vision
    private val merlinSaw = ArrayList<Int>()
    private val percivalSaw = ArrayList<Int>()
    private val mordredSaw = ArrayList<Int>()
    private val morganaSaw = ArrayList<Int>()
    private val assassinSaw = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPlayer = arguments!!.getInt(CURRENT_PLAYER)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        remembered.setOnClickListener {
            dismiss()
        }
        initCharacters()
        chooseCharacter()
    }

    private fun initCharacters() {
        repeat(CHARACTERS.size) {
            when (CHARACTERS[it]) {
                merlin -> {
                    percivalSaw.add(it + 1)
                }
                mordred -> {
                    morganaSaw.add(it + 1)
                    assassinSaw.add(it + 1)
                }
                morgana -> {
                    mordredSaw.add(it + 1)
                    assassinSaw.add(it + 1)
                    merlinSaw.add(it + 1)
                    percivalSaw.add(it + 1)
                }
                assassin -> {
                    mordredSaw.add(it + 1)
                    morganaSaw.add(it + 1)
                    merlinSaw.add(it + 1)
                }
                oberon -> {
                    merlinSaw.add(it + 1)
                }
            }
        }
        tenToZero(merlinSaw)
        tenToZero(percivalSaw)
        tenToZero(mordredSaw)
        tenToZero(morganaSaw)
        tenToZero(assassinSaw)
    }

    /**
     * 把第10位玩家的敘述轉為編號0家
     */
    private fun tenToZero(characterSaw: ArrayList<Int>) {
        repeat(characterSaw.size) {
            if (10 == characterSaw[it]) {
                characterSaw[it] = 0
            }
        }
    }

    private fun chooseCharacter() {
        var showCharacter = ""
        when (CHARACTERS[currentPlayer]) {
            merlin -> showCharacter = merlin
            percival -> showCharacter = percival
            mordred -> {
                characterIcon.background = context?.getDrawable(R.drawable.ic_account_circle_red_24dp)
                showCharacter = mordred
            }
            morgana -> {
                characterIcon.background = context?.getDrawable(R.drawable.ic_account_circle_red_24dp)
                showCharacter = morgana
            }
            assassin -> {
                characterIcon.background = context?.getDrawable(R.drawable.ic_account_circle_red_24dp)
                showCharacter = assassin
            }
            oberon -> {
                characterIcon.background = context?.getDrawable(R.drawable.ic_account_circle_red_24dp)
                showCharacter = oberon
            }
            arthursLoyalty -> showCharacter = arthursLoyalty
        }
        val showInfo = whoYouAre(showCharacter)
        character.text = showCharacter
        characterInfo.text = showInfo
    }

    private fun whoYouAre(character: String): String {
        return when (character) {
            merlin -> String.format(knowBadGuys, merlinSaw[0], merlinSaw[1], merlinSaw[2])
            percival -> String.format(whoIsMerlin, percivalSaw[0], percivalSaw[1])
            mordred -> youAreBadGuys(mordred)
            morgana -> youAreBadGuys(morgana)
            assassin -> youAreBadGuys(assassin)
            else -> ""
        }
    }

    private fun youAreBadGuys(character: String): String {
        var badguysSaw = ArrayList<Int>()
        when (character) {
            mordred -> badguysSaw = mordredSaw
            morgana -> badguysSaw = morganaSaw
            assassin -> badguysSaw = assassinSaw
        }
        return String.format(getString(R.string.you_are_bad_guys), badguysSaw[0], badguysSaw[1])
    }

    override fun onResume() {
        super.onResume()
        dialog.window!!.setLayout(-1, -1)
    }
}