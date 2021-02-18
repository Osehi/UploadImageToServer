package com.polish.uploadimagetodarotserver.di

import com.google.gson.Gson
import com.polish.uploadimagetodarotserver.Constants
import com.polish.uploadimagetodarotserver.api.APIUploadImage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * this is an object generator for network request operation
 */
@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    /**
     * httpLogging interceptor is a debugging tool
     * this creates an instance of it
     */
    @Provides
    @Singleton
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
    }
    /**
     * this converter helps for serialization of objects(java object)
     * from object to btye stream and back to object
     */
    @Provides
    @Singleton
    fun providesConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }
    /**
     * OkHttp is an http client used for making Http request
     */
    @Provides
    @Singleton
    fun provideClient(logger:HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .build()
    }
    /**
     * an instance of the retrofit object
     * this is an httpt client used to make the network call
     */
    @Provides
    @Singleton
    fun provideService(client: OkHttpClient, converterFactory:GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(client)
            .addConverterFactory(converterFactory)
            .build()

    }
    /**
     * create an instance of the APIUploadImage
     */
    @Provides
    @Singleton
    fun provideAPIUploadImageService(imageUploadRetrofit: Retrofit):APIUploadImage{
        return imageUploadRetrofit.create(APIUploadImage::class.java)
    }


}