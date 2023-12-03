package com.kevinschildhorn

import MainView
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.kevinschildhorn.fotopresenter.startKoin
import com.kevinschildhorn.fotopresenter.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class MainActivity : AppCompatActivity(), KoinComponent {

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin(this)

        setContent {
            MainView(viewModel)
        }
    }
}