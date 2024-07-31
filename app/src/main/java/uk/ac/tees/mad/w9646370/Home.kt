package uk.ac.tees.mad.w9646370

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import uk.ac.tees.mad.w9646370.ui.theme.gradient

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalCoroutinesApi::class)
@Composable
@Preview
fun Home(@SuppressLint("UnrememberedMutableState") vm: NoteViewModel = hiltViewModel()) {
    val ctx = LocalContext.current
    val data = vm.note.collectAsState().value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Header()
        CircularProgressBar(vm.noteLoading.value)
        data.let {
            if(it.isEmpty())
            {
                Icon(painterResource(R.drawable.empty), contentDescription = "empty",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f), tint = Color.LightGray)
                Text(text = "Hmm.. it's time to add some notes.", color = Color.White,
                    modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center,
                    fontFamily = FontFamily.SansSerif)
            }
            LazyVerticalGrid(
                contentPadding = PaddingValues(horizontal = 8.dp),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                items(items = it) {
                    NoteUI(note = it, onDeletePressed = {vm.deleteNode(it)},
                        vm = vm)

                }
            }
        }
        FabIconButton(ctx = ctx, onClick = { vm.addNoteDialogBoxToggle.value = true }, title = "Add Notes")
        DialogBox(showDialog = vm.addNoteDialogBoxToggle.value,
            onDismissRequest = { vm.addNoteDialogBoxToggle.value = false }  ) {
            AddEditNote(vm = vm)
        }
    }
}