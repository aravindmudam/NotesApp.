package uk.ac.tees.mad.w9646370

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnrememberedMutableState")
@Composable
fun ForgotPassword(navHostController: NavHostController, authViewModel: AuthViewModel = hiltViewModel()) {
    var email by remember {
        mutableStateOf("")
    }
    val isFormValid by derivedStateOf {
        email.isNotBlank()
    }
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
            BackButton({navHostController.navigateUp()})
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Forgot Password", fontWeight = FontWeight.Bold,
            fontSize = 32.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(48.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
            ),
            value = email,
            onValueChange = { email = it },
            shape = RoundedCornerShape(4.dp),
            label = { Text(text = "Email") },
            singleLine = true,
            trailingIcon = {
                if (email.isNotBlank())
                    IconButton(onClick = { email = "" }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = ""
                        )
                    }
            }
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            onClick = {authViewModel.forgotPass(email, ctx)},
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Continue")
        }
    }
}