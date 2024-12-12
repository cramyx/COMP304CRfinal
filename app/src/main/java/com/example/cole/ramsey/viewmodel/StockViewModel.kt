package com.example.cole.ramsey.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cole.ramsey.data.entities.StockInfo
import com.example.cole.ramsey.repository.StockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {

    private val _stocks = MutableStateFlow<List<StockInfo>>(emptyList())
    val stocks: StateFlow<List<StockInfo>> = _stocks

    fun insertStock(stock: StockInfo) {
        viewModelScope.launch {
            repository.insertStock(stock)
            refreshStocks()
        }
    }

    fun refreshStocks() {
        viewModelScope.launch {
            _stocks.value = repository.getAllStocks()
        }
    }

    fun getStockBySymbol(symbol: String, onResult: (StockInfo?) -> Unit) {
        viewModelScope.launch {
            val stock = repository.getStockBySymbol(symbol)
            onResult(stock)
        }
    }
}