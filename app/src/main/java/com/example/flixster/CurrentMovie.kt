package com.example.flixster

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie from the Movie Database API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class CurrentMovie {

    @JvmField
    @SerializedName("title")
    var title: String? = null


    @SerializedName("movie_image")
    var movieImageUrl: String? = null


    @SerializedName("description")
    var description: String? = null

}