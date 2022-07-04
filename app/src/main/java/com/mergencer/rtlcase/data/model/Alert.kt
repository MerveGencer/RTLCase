package com.mergencer.rtlcase.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Alert(
    @Json(name = "sender_name")
    val senderName: String,
    val event: String,
    val start: Long,
    val end: Long,
    val description: String
): Parcelable
