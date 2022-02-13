package ru.artsto.retrofit

import android.app.Application
import ru.artsto.retrofit.di.AppComponent
import ru.artsto.retrofit.di.DaggerAppComponent

class App:Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        //appDatabase = AppDatabase.getDatabase(this)
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}