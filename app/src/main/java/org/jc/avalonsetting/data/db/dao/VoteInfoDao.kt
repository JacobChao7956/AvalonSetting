package org.jc.avalonsetting.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Update
import org.jc.avalonsetting.data.db.entity.VoteInfoEntity

@Dao
interface VoteInfoDao {

    @Insert
    suspend fun insert(voteInfo: VoteInfoEntity)

    @Update
    suspend fun update(vararg voteInfos: VoteInfoEntity)

}