package com.example.cole.ramsey

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.cole.ramsey.data.entities.StockInfo
import com.example.cole.ramsey.viewmodel.StockViewModel
import com.example.cole.ramsey.viewmodel.ViewModelFactory

//COLE RAMSEY 301333287

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: StockViewModel = viewModel(factory = ViewModelFactory())
            var stockSymbol by remember { mutableStateOf("") }
            var companyName by remember { mutableStateOf("") }
            var stockQuote by remember { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Insert Stocks",
                )

                TextField(
                    value = stockSymbol,
                    onValueChange = { stockSymbol = it },
                    label = { Text("Stock Symbol") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = companyName,
                    onValueChange = { companyName = it },
                    label = { Text("Company Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = stockQuote,
                    onValueChange = { stockQuote = it },
                    label = { Text("Stock Quote") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = {
                        viewModel.insertStock(
                            StockInfo(
                                stockSymbol,
                                companyName,
                                stockQuote.toDoubleOrNull() ?: 0.0
                            )
                        )
                        stockSymbol = ""
                        companyName = ""
                        stockQuote = ""
                    },
                    modifier = Modifier.align(androidx.compose.ui.Alignment.CenterHorizontally)
                ) {
                    Text("Insert Stocks")
                }

                Text(
                    text = "Display Stock Info",
                )

                val stocks by viewModel.stocks.collectAsState()
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(stocks) { stock ->
                        Text(
                            text = stock.stockSymbol,
                            modifier = Modifier
                                .clickable {
                                    val intent = Intent(this@MainActivity, DisplayActivity::class.java)
                                    intent.putExtra("stockSymbol", stock.stockSymbol)
                                    startActivity(intent)
                                }
                                .padding(8.dp)
                        )
                    }
                }
                Text(
                    text = "CLICK A STOCK SYMBOL FROM THE LIST TO DISPLAY STOCK INFO",
                )
            }
        }
    }
}