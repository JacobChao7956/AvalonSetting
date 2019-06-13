package org.jc.avalonsetting.data.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import org.jc.avalonsetting.data.db.dao.PlayerDao
import org.jc.avalonsetting.data.db.entity.PlayerEntity

class PlayerRepository(private val playerDao: PlayerDao) {
    val allPlayers: LiveData<List<PlayerEntity>> = playerDao.getAllPlayers()

    @WorkerThread
    suspend fun insert(player: PlayerEntity) {
        playerDao.insert(player)
    }
}