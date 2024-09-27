package com.flexath.currencyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.flexath.currencyapp.presentation.navigation.SetUpNavGraph
import com.flexath.currencyapp.presentation.viewmodels.CurrencyViewModel
import com.flexath.currencyapp.ui.theme.CurrencyAppTheme
import com.flexath.currencyapp.ui.theme.currencyColorScheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyAppTheme {
                val navController = rememberNavController()
                val currencyViewModel = hiltViewModel<CurrencyViewModel>()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.currencyColorScheme.colorBackground
                ) { innerPadding ->
                    SetUpNavGraph(
                        modifier = Modifier.padding(innerPadding),
                        currencyViewModel = currencyViewModel,
                        navController = navController
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CurrencyAppTheme {
        Greeting("Android")
    }
}