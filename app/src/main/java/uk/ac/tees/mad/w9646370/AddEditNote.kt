package uk.ac.tees.mad.w9646370

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import uk.ac.tees.mad.w9646370.ui.theme.gradient

@SuppressLint("UnrememberedMutableState")
@Composable
fun AddEditNote(vm:NoteViewModel, note: Note = Note(title =  ""), header: String = "Add Note") {
    var title by remember {
        mutableStateOf(note.title)
    }
    var description by remember {
        mutableStateOf(note.description)
    }

    val isFormValid by derivedStateOf {
        title.isNotBlank() && description.isNotBlank()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Header(title = header, rightlogo = R.drawable.close_white)
            {vm.addNoteDialogBoxToggle.value = false
             vm.editNoteDialogBoxToggle.value = false}

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
            ),
            value = title,
            onValueChange = { title = it },
            shape = RoundedCornerShape(4.dp),
            label = { Text(text = "Note title", color = Color.Black) },
            singleLine = true,
            trailingIcon = {
                if (title.isNotBlank())
                    IconButton(onClick = { title = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = ""
                        )
                    }
            }
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
            ),
            value = description,
            onValueChange = { description = it },
            shape = RoundedCornerShape(4.dp),
            label = { Text(text = "Note description", color = Color.Black) },
            singleLine = false,
            maxLines = 5,
            minLines = 2,
            trailingIcon = {
                if (description.isNotBlank())
                    IconButton(onClick = { description = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = ""
                        )
                    }
            }
        )
        Spacer(modifier = Modifier.height(12.dp))

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),contentAlignment = Alignment.BottomCenter){
            Button(
                onClick = {
                    if(note.id != 0)
                    {
                        note.title = title
                        note.description = description
                        vm.editNote(note)
                    }
                    else
                        vm.addNote(Note(title = title, description = description))

                    vm.addNoteDialogBoxToggle.value = false
                    vm.editNoteDialogBoxToggle.value = false
                },
                enabled = isFormValid,
                colors = ButtonDefaults.buttonColors(disabledContainerColor = Color.LightGray, containerColor = Color.Gray),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Submit")
            }
        }
    }
}