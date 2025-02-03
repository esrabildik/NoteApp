package com.esrabildik.noteapp.di

import android.app.Application
import androidx.room.Room
import com.esrabildik.noteapp.data.data_source.NoteDB
import com.esrabildik.noteapp.data.repository.NoteRepositoryImpl
import com.esrabildik.noteapp.domain.repository.NoteRepository
import com.esrabildik.noteapp.usecases.AddNote
import com.esrabildik.noteapp.usecases.DeleteNote
import com.esrabildik.noteapp.usecases.GetNoteById
import com.esrabildik.noteapp.usecases.GetNotes
import com.esrabildik.noteapp.usecases.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun  provideNoteDatabase(app: Application) : NoteDB {
        return Room.databaseBuilder(
            app,
            NoteDB::class.java,
            NoteDB.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(db : NoteDB) : NoteRepository{
        return NoteRepositoryImpl(db.noteDao)
    }


    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository) : NoteUseCases{
        return NoteUseCases(
            getNotes = GetNotes(repository),
            deleteNote = DeleteNote(repository),
            addNote = AddNote(repository),
            getNoteID = GetNoteById(repository)
        )
    }


}