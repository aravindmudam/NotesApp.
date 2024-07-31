package uk.ac.tees.mad.w9646370

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BackButton(onClick: ()-> Unit = {}, @SuppressLint("ModifierParameter") modifier: Modifier = Modifier){
    IconButton(onClick = onClick, modifier = modifier) {
        Icon(
            Icons.Default.ArrowBack,
            contentDescription = "back")
    }
}