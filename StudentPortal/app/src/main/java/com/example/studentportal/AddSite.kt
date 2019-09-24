package com.example.studentportal

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.example.studentportal.models.Site
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_add_site.*

class AddSite : AppCompatActivity() {
    companion object{
        const val EXTRA_SITE = "EXTRA_SITE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_site)
        initViews()

        btnSave.setOnClickListener { view ->
            onSaveClick()
        }
    }

    private fun initViews() {
        // Add back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Change title
        supportActionBar?.title = "Create a Portal"
    }

    private fun onSaveClick(){
        val title = tvTitle.text.toString()
        val url = tvUrl.text.toString()
        if(title.isNotBlank() && url.isNotBlank()) {
            // Switch activity
            val site = Site(title, url)
            val intent = Intent()
            intent.putExtra(EXTRA_SITE, site)
            setResult(Activity.RESULT_OK, intent)
            finish()
        } else {
            Toast.makeText(this, getString(R.string.error_empty_field), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        // go back to the previous screen
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            } else -> return super.onOptionsItemSelected(item)
        }
    }
}
