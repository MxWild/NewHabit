package com.gmail.mxwild.newhabit.services

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.gmail.mxwild.newhabit.MovieNotification
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NetWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    private val notification = MovieNotification()

    init {
        notification.initialize()
    }

    override suspend fun doWork(): Result {
        return try {
            Log.i(TAG, "Work request trigger")
            val startTime = System.currentTimeMillis()

/*            val movies = movieRepository.getMovies(isReload = true, isBackground = true)
            movieRepository.update(movies)*/

            val differentTime = System.currentTimeMillis() - startTime
            Log.i(TAG, "${differentTime / 1000} secs was wasted for update movies")

//            val movieWithMaxNumberOfRating = database.movieDao().getWithMaxNumberOfRating()
//            notification.showNotification(convertMovieEntityToMovie(movieWithMaxNumberOfRating))

            Result.success()
        } catch (e: Exception) {
            Log.i(TAG, e.toString())
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "NetWorker"
    }
}