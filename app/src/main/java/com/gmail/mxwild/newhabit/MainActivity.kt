package com.gmail.mxwild.newhabit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_movie)

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, FragmentMoviesList())
            .commit()

/*        val mainTextView = findViewById<TextView>(R.id.main_text_view)
        val mainStatisticButton = findViewById<Button>(R.id.show_statistics_btn)

        mainTextView.setOnClickListener { showHelpWindow() }
        mainStatisticButton.setOnClickListener { showStatistics() }*/
    }

/*    private fun showHelpWindow() {
        Toast.makeText(applicationContext, getString(R.string.message_future), Toast.LENGTH_LONG)
                .show()
    }*/

/*    private fun showStatistics() {
        val intent = Intent(this, StatisticsActivity::class.java)
        val imageView = findViewById<ImageView>(R.id.imageView)

        val options = ActivityOptions.makeSceneTransitionAnimation(
            this,
            imageView,
            ViewCompat.getTransitionName(imageView)
        )

        intent.putExtra(StatisticsActivity.STATISTICS, Statistics("Push Ups", 1))
        startActivity(intent, options.toBundle())
    }*/
}