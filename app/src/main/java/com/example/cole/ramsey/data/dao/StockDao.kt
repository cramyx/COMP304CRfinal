package com.example.cole.ramsey.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import com.example.cole.ramsey.data.entities.StockInfo

@Dao
interface StockDao {

    @Insert
    suspend fun insertStock(stock: StockInfo)

    @Update
    suspend fun updateStock(stock: StockInfo)

    @Delete
    suspend fun deleteStock(stock: StockInfo)

    @Query("DELETE FROM stock_info")
    suspend fun deleteAllStocks()

    @Query("SELECT * FROM stock_info")
    suspend fun getAllStocks(): List<StockInfo>

    @Query("SELECT * FROM stock_info WHERE stockSymbol = :symbol")
    suspend fun getStockBySymbol(symbol: String): StockInfo?
}