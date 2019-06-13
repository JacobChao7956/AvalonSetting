package org.jc.avalonsetting.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_board")
data class GameBoardEntity(
        /**
         * 遊戲局數（出一次任務為一局）
         */
        @PrimaryKey
        val id: Int,
        /**
         * 任務結果
         */
        var result: Boolean,
        /**
         * 出任務玩家1（玩家名稱）
         */
        @ColumnInfo(name = "operator_1")
        var operator1: String,
        /**
         * 出任務玩家2（玩家名稱）
         */
        @ColumnInfo(name = "operator_2")
        var operator2: String,
        /**
         * 出任務玩家3（玩家名稱）
         */
        @ColumnInfo(name = "operator_3")
        var operator3: String,
        /**
         * 出任務玩家4（玩家名稱）
         */
        @ColumnInfo(name = "operator_4")
        var operator4: String,
        /**
         * 出任務玩家5（玩家名稱）
         */
        @ColumnInfo(name = "operator_5")
        var operator5: String,
        /**
         * 湖中女神（玩家名稱）
         */
        var goddess: String,
        /**
         * 被湖玩家（玩家名稱）
         */
        var asked: String
)