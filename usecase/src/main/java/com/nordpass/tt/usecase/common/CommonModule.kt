package com.nordpass.tt.usecase.common

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InstallIn(SingletonComponent::class)
@Module
object CommonModule {

    @Provides
    @Io
    fun provideIoScheduler(): Scheduler = Schedulers.io()

    @Provides
    @UI
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}