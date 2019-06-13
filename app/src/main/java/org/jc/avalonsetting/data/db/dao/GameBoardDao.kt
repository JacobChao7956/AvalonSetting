package org.jc.avalonsetting.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import org.jc.avalonsetting.data.db.entity.GameBoardEntity

@Dao
interface GameBoardDao {
    @Insert
    suspend fun insert(gameBoard: GameBoardEntity)

    @Delete
    suspend fun delete(vararg gameBoard: GameBoardEntity)
}