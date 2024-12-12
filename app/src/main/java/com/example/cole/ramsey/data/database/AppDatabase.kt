package com.example.cole.ramsey.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cole.ramsey.data.dao.StockDao
import com.example.cole.ramsey.data.entities.StockInfo

@Database(entities = [StockInfo::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao
}