package com.gmail.mxwild.newhabit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.mxwild.newhabit.domain.MoviesDataSource

class FragmentMoviesList : Fragment() {

    private lateinit var adapter: MoviesAdaptor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recycler: RecyclerView = view.findViewById(R.id.movie_list)
        adapter = MoviesAdaptor()
        recycler.layoutManager = GridLayoutManager(context, 2)
        recycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        loadMovies()
    }

    private fun loadMovies() {
        adapter.bindMovies(MoviesDataSource().getMovies())
        adapter.notifyDataSetChanged()
    }

    companion object {
        fun newInstance() = FragmentMoviesList()
    }
}