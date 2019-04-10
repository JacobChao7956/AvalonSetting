package org.jc.avalonsetting

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : DialogFragment() {

    private var currentPlayer = 0
    private val merlin by lazy { getString(R.string.merlin) }
    private val percival by lazy { getString(R.string.percival) }
    private val mordred by lazy { getString(R.string.mordred) }
    private val morgana by lazy { getString(R.string.morgana) }
    private val assassin by lazy { getString(R.string.assassin) }
    private val oberon by lazy { getString(R.string.oberon) }
    private val arthursLoyalty by lazy { getString(R.string.arthurs_loyalty) }
    private val merlinSaw = ArrayList<Int>()
    private val percivalSaw = ArrayList<Int>()
    private val mordredSaw = ArrayList<Int>()
    private val morganaSaw = ArrayList<Int>()
    private val assassinSaw = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        currentPlayer = arguments!!.getInt(CURRENT_PLAYER)
        initCharacters()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        remembered.setOnClickListener{
            dismiss()
        }
        chooseCharacter()
    }

    private fun initCharacters() {
        repeat(CHARACTERS.size) {
            when (CHARACTERS[it]) {
                merlin -> percivalSaw.add(it)
                mordred -> {
                    morganaSaw.add(it)
                    assassinSaw.add(it)
                }
                morgana -> {
                    mordredSaw.add(it)
                    assassinSaw.add(it)
                    merlinSaw.add(it)
                    percivalSaw.add(it)
                }
                assassin -> {
                    mordredSaw.add(it)
                    morganaSaw.add(it)
                    merlinSaw.add(it)
                }
                oberon -> {
                    merlinSaw.add(it)
                }
            }
        }
    }

    private fun chooseCharacter() {
        var characterText = ""
        var infoText = ""
        when (CHARACTERS[currentPlayer]) {
            merlin -> {
                characterText = merlin
                infoText = yourCharacterIs(merlin) + "\n" + omniscient()
            }
            percival -> {
                characterText = percival
                infoText = yourCharacterIs(percival) + "\n" + chosenOne()
            }
            mordred -> {
                characterText = mordred
                infoText = yourCharacterIs(mordred) + "\n" + youAreBadGuys(mordred)
            }
            morgana -> {
                characterText = morgana
                infoText = yourCharacterIs(morgana) + "\n" + youAreBadGuys(morgana)
            }
            assassin -> {
                characterText = assassin
                infoText = yourCharacterIs(assassin) + "\n" + youAreBadGuys(assassin)
            }
            oberon -> {
                characterText = oberon
                infoText = yourCharacterIs(oberon)
            }
            arthursLoyalty -> {
                characterText = arthursLoyalty
                infoText = yourCharacterIs(arthursLoyalty)
            }
        }
        character.text = characterText
        characterInfo.text = infoText
    }

    private fun yourCharacterIs(character: String) = String.format(getString(R.string.your_character_is), character)

    private fun youAreBadGuys(character: String): String {
        var badguysSaw = ArrayList<Int>()
        when (character) {
            mordred -> badguysSaw = mordredSaw
            morgana -> badguysSaw = morganaSaw
            assassin -> badguysSaw = assassinSaw
        }
        return String.format(getString(R.string.you_are_bad_guys), badguysSaw[0], badguysSaw[1])
    }

    private fun chosenOne() = String.format(getString(R.string.chosen_one),
            percivalSaw[0], percivalSaw[1])

    private fun omniscient() = String.format(getString(R.string.omniscient),
            merlinSaw[0], merlinSaw[1], merlinSaw[2])
}