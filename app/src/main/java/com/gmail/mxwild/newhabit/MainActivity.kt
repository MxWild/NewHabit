package com.gmail.mxwild.newhabit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.gmail.mxwild.newhabit.model.Statistics

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainTextView = findViewById<TextView>(R.id.main_text_view)

        mainTextView.setOnClickListener { showHelpWindow() }
    }

    private fun showHelpWindow() {
        Toast.makeText(applicationContext, getString(R.string.message_future), Toast.LENGTH_LONG)
                .show()
    }

    fun showStatistics(view: View) {
        val intent = Intent(this, StatisticsActivity::class.java)
        intent.putExtra("Statistics", Statistics("Push Ups", 1))
        startActivity(intent)
    }
}