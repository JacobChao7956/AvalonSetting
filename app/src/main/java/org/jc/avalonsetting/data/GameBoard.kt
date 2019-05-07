package org.jc.avalonsetting.data

class GameBoard {
    /**
     * 紀錄每一輪的資訊
     */
    val rounds = ArrayList<Round>(5)
    /**
     * 紀錄該局玩家數量
     */
    val players = ArrayList<Character>()
}