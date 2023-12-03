package com.kevinschildhorn.fotopresenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.kevinschildhorn.fotopresenter.ui.LoginViewModel
import com.kevinschildhorn.fotopresenter.ui.composables.screens.LoginScreen
import com.kevinschildhorn.fotopresenter.ui.theme.FotoPresenterTheme

class MainActivity : ComponentActivity() {

    val loginViewModel = LoginViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FotoPresenterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(loginViewModel = loginViewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FotoPresenterTheme {
        LoginScreen(loginViewModel = LoginViewModel())
    }
}