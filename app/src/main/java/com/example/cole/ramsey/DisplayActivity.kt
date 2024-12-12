package com.example.cole.ramsey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cole.ramsey.data.entities.StockInfo
import com.example.cole.ramsey.viewmodel.StockViewModel
import com.example.cole.ramsey.viewmodel.ViewModelFactory

class DisplayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val stockSymbol = intent.getStringExtra("stockSymbol") ?: return

        setContent {
            val viewModel: StockViewModel = viewModel(factory = ViewModelFactory())
            var stockInfo by remember { mutableStateOf<StockInfo?>(null) }

            LaunchedEffect(stockSymbol) {
                viewModel.getStockBySymbol(stockSymbol) { stock ->
                    stockInfo = stock
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                stockInfo?.let {
                    Text(text = "Symbol: ${it.stockSymbol}")
                    Text(text = "Company: ${it.companyName}")
                    Text(text = "Quote: ${it.stockQuote}")
                }

                Button(
                    onClick = { finish() },
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally)
                ) {
                    Text("Back")
                }
            }
        }
    }
}