package com.nordpass.tt.usecase.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers

@InstallIn(SingletonComponent::class)
@Module
object CommonModule {

    @Provides @Io
    fun provideIoScheduler() = Schedulers.io()
}