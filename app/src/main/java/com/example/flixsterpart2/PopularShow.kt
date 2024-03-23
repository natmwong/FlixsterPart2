package com.example.flixsterpart2

import com.google.gson.annotations.SerializedName

/**
 * The Model for storing a single movie from the Movie Database API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */
class PopularShow {

    @JvmField
    @SerializedName("name")
    var title: String? = null


    @SerializedName("poster_path")
    var showImageUrl: String? = null


    @SerializedName("overview")
    var description: String? = null

}