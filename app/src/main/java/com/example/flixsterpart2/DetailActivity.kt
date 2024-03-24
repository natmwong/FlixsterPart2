package com.example.flixsterpart2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var ratingTextView: TextView
    private lateinit var originCountryTextView: TextView
    private lateinit var airDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        ratingTextView = findViewById(R.id.mediaRating)
        originCountryTextView = findViewById(R.id.mediaOriginCountry)
        airDateTextView = findViewById(R.id.mediaAirDate)

        // TODO: Get the extra from the Intent
        val show = intent.getSerializableExtra(SHOW_EXTRA) as PopularShow

        // TODO: Set the rating, origin country, and air date information from the show
        ratingTextView.text = "Rating: " + show.rating
        originCountryTextView.text = "Origin Country: " + show.origin
        airDateTextView.text = "Air Date: " + show.airDate

        // TODO: Load the show image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + show.showImageUrl)
            .into(mediaImageView)
    }
}