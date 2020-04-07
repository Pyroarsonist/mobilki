package com.gmail.velikiydan.mobile.data.dtos

import android.graphics.Color
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class FlowerDto constructor(
    var minPrice: Float = DEFAULT_MIN_PRICE,
    var maxPrice: Float = DEFAULT_MAX_PRICE,
    var color: Int = Color.RED,
    var name: String = ""
) : Parcelable {

    companion object {
        const val DEFAULT_MIN_PRICE = 0f
        const val DEFAULT_MAX_PRICE = 3000f
    }
}


