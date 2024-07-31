package uk.ac.tees.mad.w9646370

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FabIconButton(ctx: Context, onClick :()-> Unit, title: String) {
    Box( modifier = Modifier.fillMaxSize().padding(16.dp),contentAlignment = Alignment.BottomEnd){
        ExtendedFloatingActionButton(
            // on below line we are setting text for our fab
            text = { Text(text = title, color = Color.White) },
            // on below line we are adding click listener.
            onClick = onClick,
            contentColor = Color.White,
            containerColor = Color.DarkGray,
            icon = { Icon(Icons.Filled.Add, "") }
        )
    }
}