package com.gmail.velikiydan.mobile.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.gmail.velikiydan.mobile.data.FlowerRoomDatabase
import com.gmail.velikiydan.mobile.data.entities.FlowerEntity
import com.gmail.velikiydan.mobile.data.repositories.FlowerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FlowerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: FlowerRepository
    val allFlowers: LiveData<List<FlowerEntity>>

    init {
        val flowersDao = FlowerRoomDatabase.getDatabase(application, viewModelScope).flowerDao()
        repository = FlowerRepository(flowersDao)
        allFlowers = repository.allFlowers
    }

    fun insert(flower: FlowerEntity) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(flower)
    }
}