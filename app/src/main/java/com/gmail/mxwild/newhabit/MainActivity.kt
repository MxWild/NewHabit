package com.gmail.mxwild.newhabit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.gmail.mxwild.newhabit.movieslist.FragmentMoviesList
import com.gmail.mxwild.newhabit.services.WorkRequest
import com.gmail.mxwild.newhabit.services.WorkRequest.Companion.NEW_HABIT_NETWORK

class MainActivity : AppCompatActivity() {

    private val workRequest = WorkRequest()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_movie)

        WorkManager.getInstance(App.getContext())
            .enqueueUniquePeriodicWork(
                NEW_HABIT_NETWORK,
                ExistingPeriodicWorkPolicy.REPLACE,
                workRequest.periodicRequest
            )

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, FragmentMoviesList.newInstance())
                .commit()
        }
    }
}