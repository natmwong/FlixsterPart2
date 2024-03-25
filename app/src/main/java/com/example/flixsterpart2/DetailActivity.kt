package com.example.flixsterpart2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var descriptionTextView: TextView
    private lateinit var originCountryTextView: TextView
    private lateinit var airDateTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView = findViewById(R.id.mediaImage)
        descriptionTextView = findViewById(R.id.mediaDescription)
        originCountryTextView = findViewById(R.id.mediaOriginCountry)
        airDateTextView = findViewById(R.id.mediaAirDate)

        // TODO: Get the extra from the Intent
        val origin = intent.getStringExtra("origin")
        // Check if the description is empty
        val description = intent.getStringExtra("description")
        val airDate = intent.getStringExtra("airDate")
        val showImageUrl = intent.getStringExtra("showImageUrl")

        // TODO: Set the air date, origin country, and description information
        airDateTextView.text = "AirDate: " + airDate
        originCountryTextView.text = "Origin Country: " + origin
        if (description.isNullOrEmpty()) {
            descriptionTextView.text = "This show does not have an overview translated in English."
        } else {
            descriptionTextView.text = description
        }

        // TODO: Load the show image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500/" + showImageUrl)
            .into(mediaImageView)
    }
}