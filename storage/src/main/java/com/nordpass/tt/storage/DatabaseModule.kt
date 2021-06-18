package com.nordpass.tt.storage

import android.content.Context
import com.nordpass.tt.storage.todo.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): TodoDatabase =
        TodoDatabase.create(context)

    @Provides
    fun provideTodoDao(db: TodoDatabase): TodoDao = db.todoDao()
}