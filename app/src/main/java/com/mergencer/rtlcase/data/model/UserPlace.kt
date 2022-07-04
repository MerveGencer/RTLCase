package com.mergencer.rtlcase.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "user_places")
data class UserPlace (
    @PrimaryKey
    val id: String,
    val lat: Double,
    val lng: Double,
    val name: String,
    val address: String
): Parcelable