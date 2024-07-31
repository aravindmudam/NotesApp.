package uk.ac.tees.mad.w9646370.interfaces

import android.content.Context
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseUser

interface IAuthRepo{
    suspend fun registerUser(
        email: String,
        password: String,
        navController: NavController
    )
    suspend fun loginUser(
        email: String,
        password: String,
        navController: NavController
    )

    suspend fun forgotPass(
        email: String,
        ctx: Context
    )

    suspend fun logout(
        navController: NavController
    )

    fun getCurrentuser() : FirebaseUser?
}