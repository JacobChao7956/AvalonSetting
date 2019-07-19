package org.jc.avalonsetting.data.db.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import org.jc.avalonsetting.data.db.dao.GameBoardDao
import org.jc.avalonsetting.data.db.entity.GameBoardEntity

class GameBoardRepository(private val gameBoardDao: GameBoardDao) {

    val operations: LiveData<List<GameBoardEntity>> = gameBoardDao.getOperations()
    var operation1: LiveData<GameBoardEntity> = gameBoardDao.getOperation(1)
    var operation2: LiveData<GameBoardEntity> = gameBoardDao.getOperation(2)
    var operation3: LiveData<GameBoardEntity> = gameBoardDao.getOperation(3)
    var operation4: LiveData<GameBoardEntity> = gameBoardDao.getOperation(4)
    var operation5: LiveData<GameBoardEntity> = gameBoardDao.getOperation(5)

    @WorkerThread
    suspend fun insert(operation: GameBoardEntity) {
        gameBoardDao.insert(operation)
    }

    @WorkerThread
    suspend fun update(vararg operation: GameBoardEntity) {
        gameBoardDao.update(*operation)
    }

    fun deleteAll() {
        gameBoardDao.deleteAll()
    }
}