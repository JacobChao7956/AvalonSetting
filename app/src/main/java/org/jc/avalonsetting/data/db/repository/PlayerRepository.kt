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

    @WorkerThread
    suspend fun update(vararg players: PlayerEntity) {
        playerDao.update(*players)
    }

    @WorkerThread
    suspend fun deleteAll() {
        playerDao.deleteAll()
    }

    fun getPlayer(id: Int): PlayerEntity = playerDao.getPlayer(id)

    fun isPlayerEmpty(): Boolean = playerDao.isPlayerEmpty().isNullOrEmpty()
}