package org.jc.avalonsetting.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jc.avalonsetting.data.db.InfoDatabase
import org.jc.avalonsetting.data.db.entity.PlayerEntity
import org.jc.avalonsetting.data.db.repository.PlayerRepository

class PlayerViewModel(app: Application) : ViewModel() {

    private val repository: PlayerRepository
    private val allPlayers: LiveData<List<PlayerEntity>>

    init {
        val playerDao = InfoDatabase.getDatabase(app).playerDao()
        repository = PlayerRepository(playerDao)
        allPlayers = repository.allPlayers
    }

    fun insert(player: PlayerEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(player)
    }
}