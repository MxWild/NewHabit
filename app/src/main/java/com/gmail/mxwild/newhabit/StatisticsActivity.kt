package com.gmail.mxwild.newhabit

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.gmail.mxwild.newhabit.model.Statistics

class StatisticsActivity : AppCompatActivity() {

    companion object {
        const val STATISTICS = "Statistics"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_statistics)

        val statisticsFromParcelable: Statistics = intent.getParcelableExtra(STATISTICS) ?: Statistics("Empty", 0)

        val name = statisticsFromParcelable.name
        val count = statisticsFromParcelable.count

        val findViewById = findViewById<TextView>(R.id.statistics_text_view)

        findViewById.text = "$name: $count"
    }

}