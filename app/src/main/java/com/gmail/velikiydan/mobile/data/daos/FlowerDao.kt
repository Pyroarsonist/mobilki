package com.gmail.velikiydan.mobile.data.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.velikiydan.mobile.data.entities.FlowerEntity

@Dao
interface FlowerDao {

    @Query("SELECT * from Flower ORDER BY name ASC")
    fun getAlphabetizedFlowers(): LiveData<List<FlowerEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(flower: FlowerEntity)

    @Query("DELETE FROM Flower")
    suspend fun deleteAll()
}