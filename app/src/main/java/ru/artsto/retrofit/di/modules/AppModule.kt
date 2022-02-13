package ru.artsto.retrofit.di.modules

import android.content.Context
import dagger.Binds
import dagger.Module
import ru.artsto.retrofit.App

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: App): Context
}
