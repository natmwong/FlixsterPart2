package com.example.flixsterpart2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixsterpart2.R.id

/**
 * [RecyclerView.Adapter] that can display a [PopularShow]
 */
class PopularShowsRecyclerViewAdapter (private val shows: List<PopularShow>)
    : RecyclerView.Adapter<PopularShowsRecyclerViewAdapter.ShowViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_popular_show, parent, false)
        return ShowViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class ShowViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: PopularShow? = null
        val mShowTitle: TextView = mView.findViewById<View>(id.show_title) as TextView
        val mShowDescription: TextView = mView.findViewById<View>(id.show_description) as TextView
        val mShowImage: ImageView = mView.findViewById<View>(id.show_image) as ImageView

        override fun toString(): String {
            return mShowTitle.toString()
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position]

        holder.mItem = show
        holder.mShowTitle.text = show.title

        // Check if the description is empty
        if (show.description.isNullOrEmpty()) {
            holder.mShowDescription.text = "This show does not have an overview translated in English."
        } else {
            holder.mShowDescription.text = show.description
        }

        val fullImageUrl = "https://image.tmdb.org/t/p/w500/" + show.showImageUrl

        Glide.with(holder.mView)
            .load(fullImageUrl)
            .centerInside()
            .into(holder.mShowImage)
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return shows.size
    }
}