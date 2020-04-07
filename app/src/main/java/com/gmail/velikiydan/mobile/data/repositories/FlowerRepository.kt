package com.gmail.velikiydan.mobile.data.repositories

import androidx.lifecycle.LiveData
import com.gmail.velikiydan.mobile.data.daos.FlowerDao
import com.gmail.velikiydan.mobile.data.entities.FlowerEntity

class FlowerRepository(private val flowerDao: FlowerDao) {

    val allFlowers: LiveData<List<FlowerEntity>> = flowerDao.getAlphabetizedFlowers()

    suspend fun insert(flower: FlowerEntity) {
        flowerDao.insert(flower)
    }
}