package com.example.mcnearby.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mcnearby.NearbyLocationProvider
import com.example.mcnearby.NearbyLocationProviderImpl
import com.example.mcnearby.database.NearbyDatabase
import com.example.mcnearby.database.dao.VenueDao
import com.example.mcnearby.network.NearbyApi
import com.example.mcnearby.repo.NearbyRepo
import com.example.mcnearby.repo.NearbyRepoImpl
import com.example.mcnearby.usecase.NearbyUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NearbyModule {
    @Provides
    fun providesNearbyApi(retrofit: Retrofit): NearbyApi {
        return retrofit.create(NearbyApi::class.java)
    }

    @Provides
    @Singleton
    fun providesRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.seatgeek.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesNearbyDatabase(
        @ApplicationContext context: Context
    ): NearbyDatabase {
       return Room.databaseBuilder(context, NearbyDatabase::class.java, "nearby_db")
           .build()
    }

    @Provides
    fun providesVenueDao(
        nearbyDatabase: NearbyDatabase
    ): VenueDao {
        return nearbyDatabase.venueDao()
    }

    @Provides
    fun providesNearbyRepo(
        nearbyApi: NearbyApi,
        venueDao: VenueDao,
        @ApplicationContext context: Context
    ): NearbyRepo {
        return NearbyRepoImpl(nearbyApi, venueDao, context)
    }

    @Provides
    @Singleton
    fun providesLocationProvider(
        @ApplicationContext context: Context
    ): NearbyLocationProvider {
        return NearbyLocationProviderImpl(context)
    }

}