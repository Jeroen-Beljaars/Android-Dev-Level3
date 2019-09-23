package com.example.reminder

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// The reminder class which can be parsed from one activity to another
@Parcelize
data class Reminder(
    var reminder: String
) : Parcelable