package com.example.cole.ramsey

import android.app.Application
import androidx.room.Room
import com.example.cole.ramsey.data.database.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyApplication : Application() {

    companion object {
        lateinit var database: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "stock_database"
        ).build()
        CoroutineScope(Dispatchers.IO).launch {
            database.stockDao().deleteAllStocks()
        }
    }
}