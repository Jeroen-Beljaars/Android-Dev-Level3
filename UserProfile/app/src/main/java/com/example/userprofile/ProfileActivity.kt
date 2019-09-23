package com.example.userprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {
    companion object{
        const val PROFILE_EXTRA = "PROFILE_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initViews()
    }

    private fun initViews() {
        // Add back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Change title
        supportActionBar?.title = "This is your profile!"

        // Create profile Obj
        val profile = intent.getParcelableExtra<Profile>(PROFILE_EXTRA)

        // Set the right values
        if (profile != null){
            tvName.text = getString(R.string.full_name, profile.firstName, profile.lastName)
            tvDescription.text = profile.description
            if (profile.imageUri != null) ivProfileImage.setImageURI(profile.imageUri)

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
