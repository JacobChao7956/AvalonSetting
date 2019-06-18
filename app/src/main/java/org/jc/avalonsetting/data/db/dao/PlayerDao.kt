package org.jc.avalonsetting.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.jc.avalonsetting.data.db.entity.PlayerEntity

@Dao
interface PlayerDao {
    @Query("SELECT * from player ORDER BY id ASC")
    fun getAllPlayers(): LiveData<List<PlayerEntity>>

    @Query("SELECT * from player WHERE id > :id")
    fun getPlayer(id: Int): PlayerEntity

    @Insert
    suspend fun insert(player: PlayerEntity)

    @Update
    suspend fun update(vararg player: PlayerEntity)

    @Delete
    suspend fun delete(player: PlayerEntity)

    @Query("DELETE FROM player")
    suspend fun deleteAll()
}