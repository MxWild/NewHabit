package com.gmail.mxwild.newhabit

import android.app.Application
import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.atomic.AtomicLong
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), Configuration.Provider {

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    override fun getWorkManagerConfiguration() = Configuration.Builder()
        .setWorkerFactory(workerFactory)
        .build()

    companion object {
        private var context: Context? = null
        fun getContext(): Context = context ?: throw IllegalStateException()

        val pageCounter = AtomicLong(1)
    }
}