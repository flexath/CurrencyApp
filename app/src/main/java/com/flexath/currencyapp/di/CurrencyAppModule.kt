package com.flexath.currencyapp.di

import android.content.Context
import android.os.Build.VERSION_CODES.BASE
import androidx.room.Room
import com.flexath.currencyapp.data.local.db.CurrencyDb
import com.flexath.currencyapp.data.remote.api.CurrencyApi
import com.flexath.currencyapp.data.remote.api.CurrencyApiConstants.BASE_URL
import com.flexath.currencyapp.data.remote.api.CurrencyDbConstants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyAppModule {

    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(45, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit) : CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }

    @Volatile
    private var INSTANCE: CurrencyDb? = null

    @Provides
    @Singleton
    fun provideCurrencyDbInstance(@ApplicationContext context: Context) = INSTANCE ?: synchronized(this) {
        INSTANCE ?: Room.databaseBuilder(
            context,
            CurrencyDb::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration()
            .build().also {
                INSTANCE = it
            }
    }

    @Provides
    @Singleton
    fun provideCurrencyDao(database: CurrencyDb) = database.currencyDao()
}