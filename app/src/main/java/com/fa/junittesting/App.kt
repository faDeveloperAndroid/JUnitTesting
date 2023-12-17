package com.fa.junittesting

import android.app.Application
import com.fa.junittesting.data.local.AppDatabase

class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        AppDatabase.getDatabase(this); //--AppDatabase_Impl does not exist
    }
}
