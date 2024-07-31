package uk.ac.tees.mad.w9646370

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uk.ac.tees.mad.w9646370.interfaces.IAuthRepo
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: IAuthRepo) : ViewModel() {

    val currentUser = repository.getCurrentuser()

    val userData: MutableState<User?> = mutableStateOf(
        User()
    )

    val isAdmin: MutableState<Boolean> = mutableStateOf(false)

    init {
        currentUser
        //viewModelScope.launch { userData.value = repository.getCurrentuserDetails() }
        isAdmin.value = currentUser != null && currentUser.email.equals("admin@admin.com")
    }
    fun registerUser(email: String, password: String, navController: NavController) =
        viewModelScope.launch { repository.registerUser(email, password, navController) }


    fun loginUser(email: String, password: String, navController: NavController) = viewModelScope.launch {
        repository.loginUser(email, password, navController)
    }

    fun logout(navController: NavController) = viewModelScope.launch {
        repository.logout(navController)
    }

    fun forgotPass(email: String, ctx : Context) = viewModelScope.launch {
        repository.forgotPass(email, ctx)
    }
}