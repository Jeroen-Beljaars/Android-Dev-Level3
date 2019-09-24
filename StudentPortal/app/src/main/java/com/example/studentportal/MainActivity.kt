package com.example.studentportal

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.studentportal.adapters.SiteAdapter
import com.example.studentportal.models.Site

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val NUMBER_OF_COLUMNS = 2
const val SITE_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {
    private val sites = arrayListOf<Site>()
    private val siteAdapter = SiteAdapter(sites) { site: Site -> siteItemClicked(site)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        initViews()

        fab.setOnClickListener { view ->
            startAddSiteActivity()
        }

    }
    private fun startAddSiteActivity(){
        // Create an intent for the start activity (Abstract description of the operation)
        val intent = Intent(this, AddSite::class.java)
        // Start the AddReminder activity
        startActivityForResult(intent, SITE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // Check if the result code is OK
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                SITE_REQUEST_CODE -> {
                    // Get the reminder
                    val site = data!!.getParcelableExtra<Site>(AddSite.EXTRA_SITE)
                    // Add the reminder to the list
                    sites.add(site)
                    // Notify the adapter that we added a reminder
                    siteAdapter.notifyDataSetChanged()
                }
            }
        }
    }

    /**
     * Create a touch helper to recognize when a user swipes an item from a recycler view.
     * An ItemTouchHelper enables touch behavior (like swipe and move) on each ViewHolder,
     * and uses callbacks to signal when a user is performing these actions.
     */
    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                sites.removeAt(position)
                siteAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun siteItemClicked(site: Site) {
        // Create the custom chrome tab
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()

        // Set the color of the toolbar to the app color
        builder.setToolbarColor(Integer.parseInt("009688", 16))
        customTabsIntent.launchUrl(this, Uri.parse(site.url))

    }

    private fun initViews(){
        rvSites.layoutManager = GridLayoutManager(this, NUMBER_OF_COLUMNS)
        rvSites.adapter = siteAdapter
        createItemTouchHelper().attachToRecyclerView(rvSites)
    }
}
