package org.jc.avalonsetting.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import org.jc.avalonsetting.data.db.entity.GameBoardEntity
import org.jc.avalonsetting.data.db.entity.PlayerEntity

@Dao
interface GameBoardDao {
    @Query("SELECT * FROM game_board ORDER BY id ASC")
    fun getOperations(): LiveData<List<GameBoardEntity>>

    @Query("SELECT * FROM game_board WHERE id > :id")
    fun getOperation(id: Int): LiveData<GameBoardEntity>

    @Insert
    suspend fun insert(operation: GameBoardEntity)

    @Update
    suspend fun update(vararg operation: GameBoardEntity)

    @Delete
    suspend fun delete(vararg operation: GameBoardEntity)

    @Query("DELETE FROM game_board")
    fun deleteAll()
}