package com.gmail.mxwild.newhabit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.gmail.mxwild.newhabit.model.Statistics

class StatisticsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val statisticsFromParcelable: Statistics? = intent.getParcelableExtra("Statistics")

        val name = statisticsFromParcelable?.name
        val count = statisticsFromParcelable?.count

        val findViewById = findViewById<TextView>(R.id.statistics_text_view)

        findViewById.text = name.toString() + " " + count
    }
}