package org.jc.avalonsetting.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
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

        fun getDatabase(context: Context, scope: CoroutineScope): InfoDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        InfoDatabase::class.java,
                        "info_database"
                )
//                        .fallbackToDestructiveMigration()
//                        .addCallback(InfoDatabaseCallback(scope))
                        .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class InfoDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onOpen(database: SupportSQLiteDatabase) {
            super.onOpen(database)
            INSTANCE?.let { db ->
                scope.launch {
                    populateDatabase(db.playerDao(), db.gameBoardDao(), db.voteInfoDao())
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         */
        suspend fun populateDatabase(playerDao: PlayerDao,
                                     gameBoardDao: GameBoardDao,
                                     voteInfoDao: VoteInfoDao) {
            // Start the app with a clean database every time.
            playerDao.deleteAll()
        }
    }
}