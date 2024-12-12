package com.example.cole.ramsey.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cole.ramsey.MyApplication
import com.example.cole.ramsey.repository.StockRepository

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val dao = MyApplication.database.stockDao()
        val repository = StockRepository(dao)
        return StockViewModel(repository) as T
    }
}