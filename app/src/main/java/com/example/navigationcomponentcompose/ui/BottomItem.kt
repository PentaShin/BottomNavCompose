package com.example.navigationcomponentcompose.ui

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomItem(
    val title: String,
    val icon: ImageVector,
    val route: String
) {
    companion object

}
