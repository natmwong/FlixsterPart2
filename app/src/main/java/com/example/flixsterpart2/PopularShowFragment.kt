package com.example.flixsterpart2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers
import org.json.JSONArray


// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

/*
 * The class for the only fragment in the app, which contains the progress bar,
 * recyclerView, and performs the network calls to the Movie Database API.
 */
class PopularShowFragment : Fragment(), OnListInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_popular_shows_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = API_KEY

        // Using the client, perform the HTTP request
        client[
                "https://api.themoviedb.org/3/tv/popular",
                params,
                object : JsonHttpResponseHandler()

                {
                    /*
                     * The onSuccess function gets called when
                     * HTTP response status is "200 OK"
                     */
                    override fun onSuccess(
                        statusCode: Int,
                        headers: Headers,
                        json: JsonHttpResponseHandler.JSON
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        //Parse JSON into Models
                        val resultsJSON : JSONArray = json.jsonObject.getJSONArray("results")
                        val gson = Gson()
                        val showListType = object : TypeToken<List<PopularShow>>() {}.type


                        val shows : List<PopularShow> = gson.fromJson(resultsJSON.toString(), showListType)
                        Log.d("PopularShows", shows.toString())
                        recyclerView.adapter = PopularShowsRecyclerViewAdapter(shows, this@PopularShowFragment)

                        // Look for this in Logcat:
                        Log.d("PopularShowFragment", "response successful")
                    }

                    /*
                     * The onFailure function gets called when
                     * HTTP response status is "4XX" (eg. 401, 403, 404)
                     */
                    override fun onFailure(
                        statusCode: Int,
                        headers: Headers?,
                        errorResponse: String,
                        t: Throwable?
                    ) {
                        // The wait for a response is over
                        progressBar.hide()

                        // If the error is not null, log it!
                        t?.message?.let {
                            Log.e("PopularShowFragment", errorResponse)
                        }
                    }
                }]
    }

    override fun onItemClick(item: PopularShow) {
        Log.d("PopularShowFragment", "originCountry: ${item.origin?.toString()}")

        // Navigate to Details screen and pass extra show data
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("description", item.description?.toString())
        intent.putExtra("origin", item.origin?.toString())
        intent.putExtra("airDate", item.airDate)
        intent.putExtra("showImageUrl", item.showImageUrl)
        context?.startActivity(intent)
    }
}