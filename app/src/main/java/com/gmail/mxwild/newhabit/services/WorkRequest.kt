package com.gmail.mxwild.newhabit.services

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class WorkRequest @Inject constructor(
    private val context: Context
) {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .build()

    private val periodicRequest = PeriodicWorkRequest
        .Builder(NetWorker::class.java, REPEAT_TIME, TimeUnit.MINUTES)
        .setConstraints(constraints)
        .setInitialDelay(WORKER_DELAY_TIME, TimeUnit.SECONDS)
        .build()


    fun start() {
        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            NEW_HABIT_NETWORK,
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicRequest
        )
    }

    companion object {
        const val NEW_HABIT_NETWORK = "NewHabitNetWorker"
        private const val WORKER_DELAY_TIME: Long = 30L
        private const val REPEAT_TIME: Long = 15L

        fun getInstance(appContext: Context) = WorkRequest(appContext)
    }
}