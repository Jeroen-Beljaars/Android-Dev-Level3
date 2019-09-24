package com.example.studentportal.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.studentportal.R
import com.example.studentportal.models.Site
import kotlinx.android.synthetic.main.item_site.view.*

public class SiteAdapter(private val sites: List<Site>, val clickListener: (Site) -> Unit):
    RecyclerView.Adapter<SiteAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            context = parent.context
            return ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.item_site, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return sites.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(sites[position], clickListener)
        }

        lateinit var context: Context

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(site: Site, clickListener: (Site) -> Unit) {
                // Set the image
                itemView.tvTitle.text = site.title
                // Set the text
                itemView.tvUrl.text = site.url
                itemView.setOnClickListener { clickListener(site) }
            }
        }
    }
