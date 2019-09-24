package com.example.studentportal

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import com.example.studentportal.adapters.SiteAdapter
import com.example.studentportal.models.Site

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

const val NUMBER_OF_COLUMNS = 2
const val SITE_REQUEST_CODE = 100

class MainActivity : AppCompatActivity() {
    private val sites = arrayListOf<Site>()
    private val siteAdapter = SiteAdapter(sites)

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

    private fun initViews(){
        rvSites.layoutManager = GridLayoutManager(this, NUMBER_OF_COLUMNS)
        rvSites.adapter = siteAdapter
        rvSites.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
    }
}
