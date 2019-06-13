package org.jc.avalonsetting.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import org.jc.avalonsetting.data.db.entity.PlayerEntity

@Dao
interface PlayerDao {
    @Query("SELECT * from player ORDER BY id ASC")
    fun getAllPlayers(): LiveData<List<PlayerEntity>>

    @Insert
    suspend fun insert(player: PlayerEntity)

    @Delete
    suspend fun delete(vararg players: PlayerEntity)
}