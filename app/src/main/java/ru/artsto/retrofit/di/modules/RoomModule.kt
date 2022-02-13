package ru.artsto.retrofit.di.modules

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.artsto.retrofit.room.AppDatabase
import ru.artsto.retrofit.room.CaseDao
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideRoomDatabase(application: Application): AppDatabase {
        return Room
            .databaseBuilder(application, AppDatabase::class.java, "room_database")
            //.createFromAsset("database/myapp.db") //предварительно заполнить бд
            //.createFromFile(File("mypath")) //заполнить из предварительно упакованного файла базы данных
            //.addMigrations(MIGRATION_1_2) //остальные через запятую
            .build()
    }

    @Provides
    fun provideCaseDao(appDatabase: AppDatabase): CaseDao {
        return appDatabase.caseDao()
    }
}