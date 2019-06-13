package org.jc.avalonsetting.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player")
data class PlayerEntity(
        /**
         * 玩家編號
         */
        @PrimaryKey
        val id: Int,
        /**
         * 玩家名稱
         */
        @ColumnInfo(name = "p_name")
        var pName: String,
        /**
         * 角色名稱
         */
        @ColumnInfo(name = "c_name")
        var cName: String,
        /**
         * 所屬陣營
         */
        var group: Int
)