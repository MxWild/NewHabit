package com.gmail.mxwild.newhabit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gmail.mxwild.newhabit.movieslist.FragmentMoviesList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_movie)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FragmentMoviesList.newInstance())
                .commit()
        }
    }
}