package com.example.flixsterpart2

import android.content.Intent
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

class PopularShowsRecyclerViewAdapter (private val shows: List<PopularShow>, private val listener: OnListInteractionListener?)
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
    inner class ShowViewHolder(val sView: View) : RecyclerView.ViewHolder(sView) {
        var sItem: PopularShow? = null
        val sTitle: TextView = sView.findViewById<View>(id.show_title) as TextView
        val sDescription: TextView = sView.findViewById<View>(id.show_description) as TextView
        val sImage: ImageView = sView.findViewById<View>(id.show_image) as ImageView

        override fun toString(): String {
            return sTitle.toString()
        }
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: ShowViewHolder, position: Int) {
        val show = shows[position]

        holder.sItem = show
        holder.sTitle.text = show.title

        // Check if the description is empty
        if (show.description.isNullOrEmpty()) {
            holder.sDescription.text = "This show does not have an overview translated in English."
        } else {
            holder.sDescription.text = show.description
        }

        val fullImageUrl = "https://image.tmdb.org/t/p/w500/" + show.showImageUrl

        Glide.with(holder.sView)
            .load(fullImageUrl)
            .centerInside()
            .into(holder.sImage)

        holder.itemView.setOnClickListener {
            holder.sItem?.let { show -> listener?.onItemClick(show) }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return shows.size
    }

}