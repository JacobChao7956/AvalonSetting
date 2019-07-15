package org.jc.avalonsetting.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jc.avalonsetting.references.NO_ONE

@Entity(tableName = "game_board")
data class GameBoardEntity(
        @PrimaryKey
        val id: Int = 0,
        /**
         * 任務結果
         */
        var result: Boolean = true,
        /**
         * 出任務玩家1（玩家名稱）
         */
        @ColumnInfo(name = "operator_1")
        var operator1: Int = NO_ONE,
        /**
         * 出任務玩家2（玩家名稱）
         */
        @ColumnInfo(name = "operator_2")
        var operator2: Int = NO_ONE,
        /**
         * 出任務玩家3（玩家名稱）
         */
        @ColumnInfo(name = "operator_3")
        var operator3: Int = NO_ONE,
        /**
         * 出任務玩家4（玩家名稱）
         */
        @ColumnInfo(name = "operator_4")
        var operator4: Int = NO_ONE,
        /**
         * 出任務玩家5（玩家名稱）
         */
        @ColumnInfo(name = "operator_5")
        var operator5: Int = NO_ONE,

        /**
         * 任務失敗時有幾個壞人
         */
        var bad: Int = 0,
        /**
         * 湖中女神（玩家名稱）
         */
        var goddess: Int = NO_ONE,
        /**
         * 被湖玩家（玩家名稱）
         */
        var asked: Int = NO_ONE
)