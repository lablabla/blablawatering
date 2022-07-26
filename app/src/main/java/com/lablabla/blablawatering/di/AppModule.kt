package com.lablabla.blablawatering.di

import android.app.Application
import androidx.room.Room
import com.lablabla.blablawatering.data.local.StationsDatabase
import com.lablabla.blablawatering.data.remote.StationsApi
import com.lablabla.blablawatering.data.remote.StationsApiImpl
import com.lablabla.blablawatering.data.repository.StationsRepository
import com.lablabla.blablawatering.data.repository.StationsRepositoryImpl
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
    fun providesStationsDatabase(
        app: Application
    ): StationsDatabase {
        return Room.databaseBuilder(
            app, StationsDatabase::class.java, "stations_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providesStationsApi(
    ): StationsApi {
        return StationsApiImpl()
    }

    @Provides
    @Singleton
    fun provideStationsRepository(
        api: StationsApi,
        db: StationsDatabase
    ): StationsRepository {
        return StationsRepositoryImpl(
            api, db.dao
        )
    }

//    @Provides
//    @Singleton
//    fun providesViewModelProviderFactor(
//        repository: StationsRepository
//    ): StatusViewModelProviderFactory {
//        return StatusViewModelProviderFactory(repository)
//    }
//
//    @Provides
//    @Singleton
//    fun providesStatusViewModel(
//        statusViewModelProviderFactory: StatusViewModelProviderFactory,
//        @ActivityContext context: Context
//    ) : StatusViewModel {
//        return ViewModelProvider(context, statusViewModelProviderFactory).get(StatusViewModel::class.java)
//    }
}