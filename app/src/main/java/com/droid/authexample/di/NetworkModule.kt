package com.droid.authexample.di

import android.content.Context
import com.droid.authexample.BuildConfig
import com.droid.authexample.data.UserPreferences
import com.droid.authexample.data.interceptors.AuthInterceptor
import com.droid.authexample.data.network.AuthApi
import com.droid.authexample.data.network.UserApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(@Named("auth")authToken: String): OkHttpClient  = when {
        BuildConfig.DEBUG -> {
            val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor)
                .build()
        }
        else -> {
            OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(AuthInterceptor(authToken))
                .build()
        }

    }
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("http://simplifiedcoding.tech/mywebapp/public/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideAuthApiService(retrofit: Retrofit): AuthApi = retrofit.create(AuthApi::class.java)
    @Provides
    fun provideUserApiService(retrofit: Retrofit):UserApi = retrofit.create(UserApi::class.java)

    @Provides
    @Named("auth")
    fun getAuthToken(@ApplicationContext context: Context):String {
        val sharedPref = context.getSharedPreferences("AuthToken",Context.MODE_PRIVATE)
        var authToken = sharedPref.getString("token","")
        Timber.e("User Auth Token ${authToken}")
        /*runBlocking {
            userPreferences.authToken.collect {
                authToken = it!!
            }
        }*/
        return authToken!!
    }
}