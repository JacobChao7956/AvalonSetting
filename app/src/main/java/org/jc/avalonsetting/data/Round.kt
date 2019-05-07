package org.jc.avalonsetting.data

import org.jc.avalonsetting.NO_GODDESS

class Round(
        /**
         * 紀錄持有湖中女神的玩家編號
         */
        val goddess: Int = NO_GODDESS,
        /**
         * 紀錄該輪起始玩家編號
         */
        val firstPlayer: Int
) {
    /**
     * 紀錄任務成功與失敗
     */
    var missions = ArrayList<Int>(5)
    /**
     * 紀錄出任務的隊伍組成
     */
    var party = ArrayList<Character?>(5)
}