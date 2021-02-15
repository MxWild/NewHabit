package com.gmail.mxwild.newhabit

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.NotificationManagerCompat.IMPORTANCE_HIGH
import com.gmail.mxwild.newhabit.model.data.Movie
import com.gmail.mxwild.newhabit.moviedetail.FragmentMovieDetails.Companion.MOVIE_OBJECT

interface Notifications {
    fun initialize()
    fun showNotification(movie: Movie)
    fun dismissNotification(movieId: Int)
}

class MovieNotification : Notifications {

    private lateinit var context: Context

    companion object {
        private const val CHANNEL_NEW_MESSAGES = "movie"
        private const val NOTIFY_CHANNEL_ID = "NOTIFY_CHANNEL"
        private const val REQUEST_CONTENT = 1
        private const val TAG = "Notification"
    }

    private val notificationManagerCompat: NotificationManagerCompat =
        NotificationManagerCompat.from(App.getContext())

    override fun initialize() {
        context = App.getContext()

        if (notificationManagerCompat.getNotificationChannel(CHANNEL_NEW_MESSAGES) == null) {
            notificationManagerCompat.createNotificationChannel(
                NotificationChannelCompat.Builder(NOTIFY_CHANNEL_ID, IMPORTANCE_HIGH)
                    .setName(context.getString(R.string.new_top_movie))
                    .setDescription(context.getString(R.string.new_top_movie_description))
                    .build()
            )
        }
    }

    override fun showNotification(movie: Movie) {
        Log.i(TAG, "$movie")

        val intent = Intent(App.getContext(), MainActivity::class.java)
            .setAction(Intent.ACTION_VIEW)
            .putExtra(MOVIE_OBJECT, movie)

        val pendingIntent: PendingIntent = PendingIntent
            .getActivity(context, REQUEST_CONTENT, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val notification = NotificationCompat.Builder(context, NOTIFY_CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentTitle(movie.title)
            .setContentText(movie.overview)
            .setContentIntent(pendingIntent)
            .build()

        notificationManagerCompat.notify(TAG, movie.id, notification)
    }

    override fun dismissNotification(movieId: Int) {
        notificationManagerCompat.cancel(TAG, movieId)
    }
}