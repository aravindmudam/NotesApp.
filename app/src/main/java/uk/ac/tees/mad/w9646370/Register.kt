package uk.ac.tees.mad.w9646370

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@SuppressLint("UnrememberedMutableState")
@Composable
fun GetRegister(navController: NavHostController,
                authViewModel: AuthViewModel = hiltViewModel()) {
    var firstName by remember {
        mutableStateOf("")
    }
    var lastName by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }

    var number by remember {
        mutableStateOf("")
    }
    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    val isFormValid by derivedStateOf {
        email.isNotBlank() && password.length >= 7 && number.isNotBlank()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart){
            BackButton({navController.navigateUp()})
        }
        Spacer(modifier = Modifier.weight(0.2f))
        Text(
            text = "Create Account", fontWeight = FontWeight.Bold,
            fontSize = 32.sp, textAlign = TextAlign.Start, modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
            ),
            value = firstName,
            onValueChange = { firstName = it },
            shape = RoundedCornerShape(4.dp),
            label = { Text(text = "First Name") },
            singleLine = true,
            trailingIcon = {
                if (firstName.isNotBlank())
                    IconButton(onClick = { firstName = "" }) {
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
            value = lastName,
            onValueChange = { lastName = it },
            shape = RoundedCornerShape(4.dp),
            label = { Text(text = "Last Name") },
            singleLine = true,
            trailingIcon = {
                if (lastName.isNotBlank())
                    IconButton(onClick = { lastName = "" }) {
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
            value = number,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
            ),
            onValueChange = { number = it },
            maxLines = 1,
            shape = RoundedCornerShape(4.dp),
            label = { Text(text = "Phone Number") },
            singleLine = true,
            trailingIcon = {
                if (number.isNotBlank())
                    IconButton(onClick = { number = "" }) {
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
            value = email,
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
            ),
            onValueChange = { email = it },
            shape = RoundedCornerShape(4.dp),
            label = { Text(text = "Email Address") },
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
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.LightGray,
            ),
            shape = RoundedCornerShape(4.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        painter =
                        if (isPasswordVisible)
                            painterResource(id = R.drawable.visibility)
                        else
                            painterResource(id = R.drawable.invisibility),
                        contentDescription = "Password Toggle"
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authViewModel.registerUser(email, password, navController)
                      },
            enabled = isFormValid,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Continue")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Forgot Password ?")
            TextButton(onClick = { navController.navigate(Route.forgotPassword.name)}) {
                Text(text = "Reset",
                    fontWeight = FontWeight.Bold,
                    color = Color.Black)
            }
        }
        Spacer(modifier = Modifier.weight(1f))

    }
}