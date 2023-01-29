package com.akinguldere.yktcase.di

import com.akinguldere.yktcase.ui.main.ProductsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object AdapterModule {
    @Singleton
    @Provides
    fun provideCategoriesAdapter(): ProductsAdapter {
        return ProductsAdapter()
    }

}