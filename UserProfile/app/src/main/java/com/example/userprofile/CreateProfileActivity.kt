package com.example.userprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_create_profile.*

const val GALLERY_REQUEST_CODE = 100

class CreateProfileActivity : AppCompatActivity() {
    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        initView()
    }

    private fun initView() {
        btnOpenGallery.setOnClickListener{ onGalleryClick() }
        btnConfirm.setOnClickListener{ onConfirmClick()}
    }

    private fun onGalleryClick(){
        // Create an Intent with action as ACTION_PICK
        val intent = Intent(Intent.ACTION_PICK)

        // Sets the type as image/*. This ensures only components of type image are selected
        intent.type = "image/*"

        // Start the activity using the gallery intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

    private fun onConfirmClick() {
        // Create new profile obj
        val profile = Profile(
            etFirstName.text.toString(),
            etLastName.text.toString(),
            etDescription.text.toString(),
            profileImageUri
        )

        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra(ProfileActivity.PROFILE_EXTRA, profile)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    // Get the profile image uri
                    profileImageUri = data?.data

                    // Set the profile image to the selected image
                    ivProfileImage.setImageURI(profileImageUri)
                }
            }
        }
    }
}
