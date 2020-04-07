package com.gmail.velikiydan.mobile.data

import android.content.Context
import android.graphics.Color
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gmail.velikiydan.mobile.data.daos.FlowerDao
import com.gmail.velikiydan.mobile.data.entities.FlowerEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(FlowerEntity::class), version = 1, exportSchema = false)
abstract class FlowerRoomDatabase : RoomDatabase() {

    abstract fun flowerDao(): FlowerDao

    private class FlowerDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    val flowerDao = database.flowerDao()
                    flowerDao.deleteAll()

                    val flower = FlowerEntity(1, "xxx", 23f, 424f, Color.BLACK)
                    flowerDao.insert(flower)
                }
            }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: FlowerRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): FlowerRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FlowerRoomDatabase::class.java,
                    "Flower"
                )
                    .addCallback(FlowerDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}