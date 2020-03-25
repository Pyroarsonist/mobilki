package com.gmail.velikiydan.mobile.fragments

import android.graphics.Color


class FlowerData {
    companion object {
        const val DEFAULT_MIN_PRICE = 0f
        const val DEFAULT_MAX_PRICE = 3000f
    }


    var minPrice: Float
    var maxPrice: Float
    var color: Int
    var flowerText: String

    constructor() {
        this.minPrice = DEFAULT_MIN_PRICE
        this.maxPrice = DEFAULT_MAX_PRICE
        this.color = Color.RED
        this.flowerText = ""
    }

    constructor(minPrice: Float, maxPrice: Float, color: Int, flowerText: String) {
        this.minPrice = minPrice
        this.maxPrice = maxPrice
        this.color = color
        this.flowerText = flowerText
    }

    fun print() {
        println("flower data")
        println(this.minPrice)
        println(this.maxPrice)
        println(this.color)
        println(this.flowerText)
    }


}

