package com.example.studentportal.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Site (
    val title: String,
    val url: String
) : Parcelable