package com.lablabla.blablawatering.di

import android.app.Application
import androidx.room.Room
import com.lablabla.blablawatering.communication.MqttApi
import com.lablabla.blablawatering.communication.PahoMQTT
import com.lablabla.blablawatering.data.local.WateringDatabase
import com.lablabla.blablawatering.data.remote.StationsApi
import com.lablabla.blablawatering.data.remote.StationsApiImpl
import com.lablabla.blablawatering.data.repository.DeviceRepository
import com.lablabla.blablawatering.data.repository.DeviceRepositoryImpl
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
    ): WateringDatabase {
        return Room.databaseBuilder(
            app, WateringDatabase::class.java, "stations_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providesStationsApi(
        mqttApi: MqttApi
    ): StationsApi {
        return StationsApiImpl(mqttApi)
    }

    @Provides
    @Singleton
    fun provideStationsRepository(
        api: StationsApi,
        db: WateringDatabase
    ): StationsRepository {
        return StationsRepositoryImpl(
            api, db.dao
        )
    }

    @Provides
    @Singleton
    fun providesDeviceRepository(
        db: WateringDatabase
    ): DeviceRepository {
        return DeviceRepositoryImpl(
            db.dao
        )
    }

    @Provides
    @Singleton
    fun providesMqtt(
    ): MqttApi {
        return PahoMQTT()
    }

}