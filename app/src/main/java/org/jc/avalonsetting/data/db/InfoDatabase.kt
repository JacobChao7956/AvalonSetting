package org.jc.avalonsetting.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.jc.avalonsetting.data.db.dao.GameBoardDao
import org.jc.avalonsetting.data.db.dao.PlayerDao
import org.jc.avalonsetting.data.db.dao.VoteInfoDao
import org.jc.avalonsetting.data.db.entity.GameBoardEntity
import org.jc.avalonsetting.data.db.entity.PlayerEntity
import org.jc.avalonsetting.data.db.entity.VoteInfoEntity

@Database(entities = [PlayerEntity::class, VoteInfoEntity::class, GameBoardEntity::class], version = 1)
abstract class InfoDatabase : RoomDatabase() {
    abstract fun playerDao(): PlayerDao
    abstract fun voteInfoDao(): VoteInfoDao
    abstract fun gameBoardDao(): GameBoardDao

    companion object {
        @Volatile
        private var INSTANCE: InfoDatabase? = null

        fun getDatabase(context: Context): InfoDatabase {
            return INSTANCE ?: synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        InfoDatabase::class.java,
                        "info_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}