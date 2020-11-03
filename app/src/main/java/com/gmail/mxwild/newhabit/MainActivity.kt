package com.gmail.mxwild.newhabit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainTextView = findViewById<TextView>(R.id.main_text_view)

        mainTextView.setOnClickListener { showHelpWindow() }
    }

    private fun showHelpWindow() {
        Toast.makeText(applicationContext, getString(R.string.message_future), Toast.LENGTH_LONG )
            .show()
    }
}