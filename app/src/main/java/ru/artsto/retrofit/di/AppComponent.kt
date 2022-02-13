package ru.artsto.retrofit.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import ru.artsto.retrofit.MainActivity
import ru.artsto.retrofit.di.modules.AppModule
import ru.artsto.retrofit.di.modules.RetrofitModule
import ru.artsto.retrofit.di.modules.RoomModule
import ru.artsto.retrofit.di.modules.ViewModelFactoryModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ViewModelFactoryModule::class,
    RoomModule::class,
    RetrofitModule::class
])
interface AppComponent {

    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}