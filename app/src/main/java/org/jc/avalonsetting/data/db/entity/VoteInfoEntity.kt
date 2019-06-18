package org.jc.avalonsetting.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jc.avalonsetting.references.WHITE_BALL

@Entity(tableName = "vote_info")
data class VoteInfoEntity(
        /**
         * 玩家編號
         */
        @PrimaryKey
        val id: Int,
        /**
         * 玩家名稱
         */
        @ColumnInfo(name = "p_name")
        var playerName: String,
        /**
         * 遊戲局數（出一次任務一局）
         */
        var game: Int,
        /**
         * 遊戲輪數（一次投票一輪）
         */
        var round: Int,
        /**
         * 第一次投票
         */
        @ColumnInfo(name = "vote_1")
        var vote1: Int = WHITE_BALL,
        /**
         * 第二次投票
         */
        @ColumnInfo(name = "vote_2")
        var vote2: Int = WHITE_BALL,
        /**
         * 第三次投票
         */
        @ColumnInfo(name = "vote_3")
        var vote3: Int = WHITE_BALL,
        /**
         * 第四次投票
         */
        @ColumnInfo(name = "vote_4")
        var vote4: Int = WHITE_BALL,
        /**
         * 第五次投票
         */
        @ColumnInfo(name = "vote_5")
        var vote5: Int = WHITE_BALL
)