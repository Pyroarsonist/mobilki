package com.gmail.velikiydan.mobile.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "Flower")
data class FlowerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val name: String,
    val minPrice: Float,
    val maxPrice: Float,
    val color: Int
) : Parcelable