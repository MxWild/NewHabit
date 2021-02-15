package com.gmail.mxwild.newhabit.services

import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import java.util.concurrent.TimeUnit


class WorkRequest {

    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .build()

    val periodicRequest = PeriodicWorkRequest
        .Builder(NetWorker::class.java, REPEAT_TIME, TimeUnit.MINUTES)
        .setConstraints(constraints)
        .setInitialDelay(WORKER_DELAY_TIME, TimeUnit.SECONDS)
        .build()


    companion object {
        const val NEW_HABIT_NETWORK = "NewHabitNetWorker"
        private const val WORKER_DELAY_TIME: Long = 30L
        private const val REPEAT_TIME: Long = 15L
    }
}