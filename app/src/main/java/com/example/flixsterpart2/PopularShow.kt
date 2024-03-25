package com.example.flixsterpart2

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single show from the Movie Database API
 *
 * SerialName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

class PopularShow{
    @SerializedName("name")
    val title: String? = null

    @SerializedName("poster_path")
    val showImageUrl: String? = null

    @SerializedName("overview")
    val description: String? = null

    @SerializedName("first_air_date")
    val airDate: String? = null

    @SerializedName("origin_country")
    val origin: List<String>? = null
}