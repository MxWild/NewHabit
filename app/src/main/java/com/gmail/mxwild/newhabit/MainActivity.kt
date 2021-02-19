package com.gmail.mxwild.newhabit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.moviedetail.FragmentMovieDetails
import com.gmail.mxwild.newhabit.moviedetail.FragmentMovieDetails.Companion.MOVIE_OBJECT
import com.gmail.mxwild.newhabit.movieslist.FragmentMoviesList
import com.gmail.mxwild.newhabit.services.WorkRequest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_movie)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FragmentMoviesList.newInstance())
                .commit()
            initWorker()
            intent?.let(::handleIntent)
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            val movie = intent.getSerializableExtra(MOVIE_OBJECT) as? Movie
            if (movie != null) {
                openMovie(movie)
            }
        }
    }

    private fun openMovie(movie: Movie) {
        supportFragmentManager.popBackStack(
            MOVIE_OBJECT,
            FragmentManager.POP_BACK_STACK_INCLUSIVE
        )
        supportFragmentManager.commit {
            addToBackStack(MOVIE_OBJECT)
            replace(R.id.fragment_container, FragmentMovieDetails.newInstance(movie))
        }
    }

    private fun initWorker() {
        WorkRequest.getInstance(applicationContext).start()
    }
}