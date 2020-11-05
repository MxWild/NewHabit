package com.gmail.mxwild.newhabit

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.gmail.mxwild.newhabit.model.Statistics

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainTextView = findViewById<TextView>(R.id.main_text_view)
        val mainStatisticButton = findViewById<Button>(R.id.show_statistics_btn)

        mainTextView.setOnClickListener { showHelpWindow() }
        mainStatisticButton.setOnClickListener { showStatistics() }
    }

    private fun showHelpWindow() {
        Toast.makeText(applicationContext, getString(R.string.message_future), Toast.LENGTH_LONG)
                .show()
    }

    private fun showStatistics() {
        val intent = Intent(this, StatisticsActivity::class.java)
        val imageView = findViewById<ImageView>(R.id.imageView)

        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            imageView,
            ViewCompat.getTransitionName(imageView)
        )

        intent.putExtra(StatisticsActivity.statistics, Statistics("Push Ups", 1))
        startActivity(intent, options.toBundle())
    }
}