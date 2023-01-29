package com.akinguldere.yktcase.di

import com.akinguldere.yktcase.api.FakeStoreService
import com.akinguldere.yktcase.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideBaseUrl() = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideRetrofit(BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(6, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS).protocols(listOf(Protocol.HTTP_1_1)).build()
        )
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideFakeStoreService(retrofit: Retrofit): FakeStoreService =
        retrofit.create(FakeStoreService::class.java)
}