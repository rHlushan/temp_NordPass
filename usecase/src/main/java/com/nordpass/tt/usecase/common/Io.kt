package com.nordpass.tt.usecase.common

import javax.inject.Qualifier

//better to use SchedulersManager
@Qualifier
annotation class Io()

@Qualifier
annotation class UI()