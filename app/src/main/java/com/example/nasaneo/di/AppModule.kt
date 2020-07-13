package com.example.nasaneo.di

import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import com.example.nasaneo.BuildConfig
import com.example.nasaneo.data.NasaNeoApi
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
object AppModule {

    const val IO_SCHEDULER = "IO_SCHEDULER"
    const val UI_SCHEDULER = "UI_SCHEDULER"

    @Provides
    @Singleton
    @JvmStatic
    internal fun provideHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.NASA_NEO_API_URL)
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    @JvmStatic
    internal fun provideNasaNeoApi(retrofit: Retrofit) =
        retrofit.create(NasaNeoApi::class.java)


    @Provides
    @JvmStatic
    @Named(UI_SCHEDULER)
    internal fun provideUiScheduler() =
        AndroidSchedulers.mainThread()

    @Provides
    @JvmStatic
    @Named(IO_SCHEDULER)
    internal fun provideIoScheduler() =
        Schedulers.io()


}
