package com.example.jetpackcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.jetpackcompose.viewmodel.WeatherViewModel
import com.example.jetpackcompose.ui.WeatherApp
import com.example.jetpackcompose.viewmodel.PopupServiceManager

class MainActivity : ComponentActivity() {

    private val popupServiceManager = PopupServiceManager(this)
    /**
     * The entry point of the application. Handles the setup and initialization of the app.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handlePopupService()

        setContent {
            val viewModel: WeatherViewModel = viewModel()
            WeatherApp(viewModel)
        }
    }
    /**
     * Handles starting the popup service with proper permissions.
     */
    private fun handlePopupService() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            popupServiceManager.requestPermission()
        } else {
            popupServiceManager.startPopupService()
        }
    }
}