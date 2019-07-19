package org.jc.avalonsetting.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jc.avalonsetting.data.db.InfoDatabase
import org.jc.avalonsetting.data.db.entity.GameBoardEntity
import org.jc.avalonsetting.data.db.repository.GameBoardRepository

class GameBoardViewModel(app: Application) : AndroidViewModel(app) {
    private val repository: GameBoardRepository
    var operations: LiveData<List<GameBoardEntity>>
    val operation1: LiveData<GameBoardEntity>
    val operation2: LiveData<GameBoardEntity>
    val operation3: LiveData<GameBoardEntity>
    val operation4: LiveData<GameBoardEntity>
    val operation5: LiveData<GameBoardEntity>
    var operate = MutableLiveData<Int>()

    init {
        val gameBoardDao = InfoDatabase.getDatabase(app, viewModelScope).gameBoardDao()
        repository = GameBoardRepository(gameBoardDao)
        operations = repository.operations
        operation1 = repository.operation1
        operation2 = repository.operation2
        operation3 = repository.operation3
        operation4 = repository.operation4
        operation5 = repository.operation5
        operate.value = 0
    }

    fun initOperations() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
        repeat(5) {
            repository.insert(GameBoardEntity(it + 1))
        }
    }

    fun updateOperation(result: Boolean,
                        operator1: Int, operator2: Int,
                        operator3: Int, operator4: Int,
                        operator5: Int, bad: Int,
                        goddess: Int, asked: Int) = viewModelScope.launch(Dispatchers.IO) {
        val operation = when (operate.value) {
            1 -> operation1.value!!
            2 -> operation2.value!!
            3 -> operation3.value!!
            4 -> operation4.value!!
            5 -> operation5.value!!
            else -> operation1.value!!
        }
        operation.result = result
        operation.operator1 = operator1
        operation.operator2 = operator2
        operation.operator3 = operator3
        operation.operator4 = operator4
        operation.operator5 = operator5
        operation.bad = bad
        operation.goddess = goddess
        operation.asked = asked
        repository.update(operation)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO) {
        repository.deleteAll()
    }
}