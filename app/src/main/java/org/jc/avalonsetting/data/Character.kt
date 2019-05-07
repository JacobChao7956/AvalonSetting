package org.jc.avalonsetting.data

import android.graphics.drawable.Drawable
import org.jc.avalonsetting.WHITE_BALL

class Character(
        /**
         * 玩家編號
         */
        val id: Int,
        /**
         * 角色名稱
         */
        val name: String,
        /**
         * 角色圖像
         */
        val icon: Drawable,
        /**
         * 所屬陣營
         */
        val group: Int
) {
    /**
     * 派票
     */
    var assignment = WHITE_BALL
    /**
     * 是否當過湖中女神
     */
    var wasGoddess = false
}