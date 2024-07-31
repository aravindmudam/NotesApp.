package uk.ac.tees.mad.w9646370.DAL

import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.flow.Flow
import uk.ac.tees.mad.w9646370.Resource

interface AuthRepository {
    fun loginUser(email: String, password: String): Flow<Resource<AuthResult>>
    fun registerUser(email: String, password: String): Flow<Resource<AuthResult>>

}