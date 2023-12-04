package com.kevinschildhorn.fotopresenter.ui.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun DirectoryScreen() {
    Column {
        Text("You are Connected")
        OutlinedButton({
        }) {
            Text("Logout")
        }
    }
}
