package com.example.cole.ramsey.repository

import com.example.cole.ramsey.data.dao.StockDao
import com.example.cole.ramsey.data.entities.StockInfo

class StockRepository(private val stockDao: StockDao) {

    suspend fun insertStock(stock: StockInfo) {
        stockDao.insertStock(stock)
    }

    suspend fun updateStock(stock: StockInfo) {
        stockDao.updateStock(stock)
    }

    suspend fun deleteStock(stock: StockInfo) {
        stockDao.deleteStock(stock)
    }

    suspend fun getAllStocks(): List<StockInfo> {
        return stockDao.getAllStocks()
    }

    suspend fun getStockBySymbol(symbol: String): StockInfo? {
        return stockDao.getStockBySymbol(symbol)
    }
}