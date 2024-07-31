package uk.ac.tees.mad.w9646370

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun NoteUI(note: Note,
           onDeletePressed :() -> Unit,
           vm : NoteViewModel
) {
    val stroke = Stroke(
        width = 4f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )
    var isLiked by remember {
        mutableStateOf(note.isBookMarked)
    }
    val icon = if(isLiked) Icons.Default.Favorite
                         else Icons.Default.FavoriteBorder

    if(vm.editNoteDialogBoxToggle.value)
    {
        DialogBox(showDialog = vm.editNoteDialogBoxToggle.value,
            onDismissRequest = { vm.editNoteDialogBoxToggle.value = false }  ) {
            AddEditNote(vm = vm, note, "Edit Note")
        }
    }
    var color = if(note.isBookMarked) Color.Red else Color.Yellow
    Card(shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(Color.Unspecified),
        onClick = {
            vm.editNoteDialogBoxToggle.value = true
        },
        modifier = Modifier
            .height(180.dp)
            .padding(6.dp)
            .drawBehind {
                drawRoundRect(
                    color = color,
                    style = stroke,
                    cornerRadius = CornerRadius(12.dp.toPx())
                )
            }

    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(2.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {

            Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                IconButton(onClick = onDeletePressed) {
                    Icon(Icons.Outlined.Delete, contentDescription = "delete",
                        Modifier.size(14.dp), tint = Color.LightGray)
                }
                IconButton(onClick = {
                    isLiked = !isLiked
                    vm.bookmarkNode(note)
                }) {
                    Icon(icon, contentDescription = "bookmark",
                        Modifier.size(14.dp), tint = Color.LightGray)
                }
            }
            Text(text = note.title, fontWeight = FontWeight.Bold,
                color = Color.White, fontSize = 18.sp)

            Spacer(modifier = Modifier.height(8.dp))

            Text(text = note.description,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(2.dp),
                style = TextStyle.Default,
                overflow = TextOverflow.Ellipsis,
                color = Color.White, fontSize = 12.sp)
        }

    }
}