package com.mergencer.rtlcase.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Temperature(
    val day: Double,
    val min: Double?,
    val max: Double?,
    val night: Double,
    val eve: Double,
    val morn: Double
): Parcelable
