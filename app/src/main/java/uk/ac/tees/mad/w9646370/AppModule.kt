package uk.ac.tees.mad.w9646370

import android.content.Context
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uk.ac.tees.mad.w9646370.dao.IDao
import uk.ac.tees.mad.w9646370.interfaces.IAuthRepo
import uk.ac.tees.mad.w9646370.interfaces.INoteRepo
import uk.ac.tees.mad.w9646370.network.DbContext
import uk.ac.tees.mad.w9646370.repository.AuthRepo
import uk.ac.tees.mad.w9646370.repository.NoteRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideLifeLinkDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        DbContext::class.java,
        "Notes.db"
    ).allowMainThreadQueries().build()

    @Provides
    fun provideUserDao(
        ctx : DbContext
    ) = ctx.dao

    @Provides
    fun provideNoteRepository(
        dao : IDao
    ): INoteRepo = NoteRepository(
        dao
    )

    @Provides
    fun provideFirebase(
        @ApplicationContext
        context: Context
    ) = FirebaseApp.initializeApp(context)

    @Provides
    fun provideFirebaseAuthInstance() =
        FirebaseAuth
            .getInstance()

    @Provides
    fun provideUserRepo(
        context: FirebaseAuth
    ): IAuthRepo = AuthRepo(context)

}