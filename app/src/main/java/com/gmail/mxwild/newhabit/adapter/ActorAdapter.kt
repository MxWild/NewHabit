package com.gmail.mxwild.newhabit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.Actor

class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    private var actors: List<Actor> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(actors[position])
    }

    override fun getItemCount(): Int = actors.size

    fun bindActors(actors: List<Actor>) {
        this.actors = actors
        notifyDataSetChanged()
    }

    inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        private val fullName = item.findViewById<TextView>(R.id.actor_name)
        private val avatar = item.findViewById<ImageView>(R.id.actor)

        fun onBind(actor: Actor) {
            fullName.text = actor.fullName
            avatar.setImageResource(actor.avatar)
        }
    }
}