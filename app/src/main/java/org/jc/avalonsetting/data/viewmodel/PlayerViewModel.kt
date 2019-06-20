package org.jc.avalonsetting.data.viewmodel

import android.app.Application
import android.os.Debug
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.socks.library.KLog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jc.avalonsetting.data.db.InfoDatabase
import org.jc.avalonsetting.data.db.entity.PlayerEntity
import org.jc.avalonsetting.data.db.repository.PlayerRepository
import org.jc.avalonsetting.references.BAD_GUY
import org.jc.avalonsetting.references.GOOD_MAN
import org.jc.avalonsetting.references.Players
import org.jc.avalonsetting.references.VILLAIN

class PlayerViewModel(app: Application) : AndroidViewModel(app) {

    private val repository: PlayerRepository
    var allPlayers: LiveData<List<PlayerEntity>>
    lateinit var players: List<PlayerEntity>
    var currentPlayer = MutableLiveData(0)

    init {
        val playerDao = InfoDatabase.getDatabase(app, viewModelScope).playerDao()
        repository = PlayerRepository(playerDao)
        allPlayers = repository.allLivePlayers
    }

    /**
     * Add new players to database and initial it.
     */
    fun addNewPlayer(characters: ArrayList<String>) = viewModelScope.launch(Dispatchers.IO) {
        //亂數排列角色
        characters.shuffle()
        if (Debug.isDebuggerConnected()) {
            KLog.d(characters)
        }
        for (i in 0 until Players) {
            val order = if (i == 9) 0 else i + 1
            val player = PlayerEntity(i, order, "", characters[i], checkSide(characters[i]))
            repository.insert(player)
        }
    }

    fun updatePlayerInfo(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.getPlayer(id)
    }

    /**
     * Check character's side.
     */
    private fun checkSide(character: String): Int {
        VILLAIN.forEach { if (it == character) return BAD_GUY }
        return GOOD_MAN
    }
}