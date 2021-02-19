package com.gmail.mxwild.newhabit.moviedetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gmail.mxwild.newhabit.R
import com.gmail.mxwild.newhabit.model.data.Actor

class ActorAdapter : RecyclerView.Adapter<ActorAdapter.ViewHolder>() {

    private var actors: List<Actor> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_holder_actor, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(actors[position])

        holder.itemView.animation = AnimationUtils.loadAnimation(
            holder.itemView.context,
            R.anim.list_animation
        )
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
            fullName.text = actor.name
            avatar.load(actor.picture) {
                crossfade(true)
            }
        }
    }
}