package com.flexath.currencyapp.di

import com.flexath.currencyapp.data.repository.CurrencyRepository
import com.flexath.currencyapp.domain.repository.CurrencyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencyAbstractModule {
    @Binds
    abstract fun provideCurrencyRepository(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyRepository
}