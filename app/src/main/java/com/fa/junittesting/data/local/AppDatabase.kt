package com.fa.junittesting.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Product::class], exportSchema = true)
abstract class AppDatabase: RoomDatabase() {

    abstract fun ProductDao(): ProductDao

    /*companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            //if INSTANCE is not null, return it
            //else, create the database
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "task_database")
                    .createFromAsset("database/task_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }*/
}