package com.example.flixsterpart2

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The Model for storing a single show from the Movie Database API
 *
 * SerializedName tags MUST match the JSON response for the
 * object to correctly parse with the gson library.
 */

@Keep
@Serializable
data class PopularShow(

    @SerialName("name")
    val title: String? = null,

    @SerialName("poster_path")
    val showImageUrl: String? = null,

    @SerialName("overview")
    val description: String? = null,

    @SerialName("first_air_date")
    val airDate: String? = null,

    @SerialName("origin_country")
    val origin: String? = null,

    @SerialName("vote_average")
    val rating: String? = null,

): java.io.Serializable