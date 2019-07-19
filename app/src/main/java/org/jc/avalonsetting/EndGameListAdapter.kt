package org.jc.avalonsetting

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.jc.avalonsetting.manager.inflater
import org.jc.avalonsetting.references.GAME_10P
import org.jc.avalonsetting.references.GAME_8P
import org.jc.avalonsetting.references.Players

class EndGameListAdapter : RecyclerView.Adapter<EndGameListAdapter.PlayerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerHolder =
            PlayerHolder(parent.inflater(R.layout.item_end_game))

    override fun getItemCount(): Int = when (Players) {
        GAME_8P -> 4
        GAME_10P -> 5
        else -> 5
    }

    override fun onBindViewHolder(holder: PlayerHolder, position: Int) {
        holder.setPlayerInfo()
    }

    inner class PlayerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setPlayerInfo() {

        }
    }
}