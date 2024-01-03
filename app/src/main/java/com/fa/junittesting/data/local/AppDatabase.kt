package com.fa.junittesting.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Product::class], exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun ProductDao(): ProductDao
}