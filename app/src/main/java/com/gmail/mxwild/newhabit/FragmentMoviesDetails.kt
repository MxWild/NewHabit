package com.gmail.mxwild.newhabit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.gmail.mxwild.newhabit.adapter.ActorAdapter
import com.gmail.mxwild.newhabit.domain.MockActorData

class FragmentMoviesDetails : Fragment() {

    private lateinit var adapter: ActorAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movies_details, container, false)

        view.findViewById<TextView>(R.id.back_narrow).setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler: RecyclerView = view.findViewById(R.id.actor_list)
        adapter = ActorAdapter()
        recycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        loadActors()
    }

    private fun loadActors() {
        adapter.bindActors(MockActorData().getActors())
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = FragmentMoviesDetails()
    }
}