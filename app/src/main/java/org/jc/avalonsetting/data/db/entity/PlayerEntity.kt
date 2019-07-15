package org.jc.avalonsetting.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerEntity(
        @PrimaryKey
        val id: Int,
        /**
         * 玩家編號
         */
        val order: Int,
        /**
         * 玩家名稱
         */
        @ColumnInfo(name = "p_name")
        val pName: String,
        /**
         * 角色名稱
         */
        @ColumnInfo(name = "c_name")
        val cName: String,
        /**
         * 所屬陣營(0 = 好人，1 = 壞人)
         */
        val side: Int
)