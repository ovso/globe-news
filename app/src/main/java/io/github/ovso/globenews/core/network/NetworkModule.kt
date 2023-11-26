package io.github.ovso.globenews.core.network

import android.content.Context
import coil.ImageLoader
import coil.disk.DiskCache
import coil.util.DebugLogger
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.ovso.globenews.BuildConfig
import kotlinx.serialization.json.Json
import okhttp3.Call
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton

private const val BASE_URL = BuildConfig.BACKEND_URL
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun okHttpCallFactory(): Call.Factory = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor()
                .apply {
                    if (BuildConfig.DEBUG) {
                        setLevel(HttpLoggingInterceptor.Level.BODY)
                    }
                },
        )
        .build()


    @Provides
    fun providesTopHeadlinesService(retrofit: Retrofit): TopHeadlinesApi =
        retrofit.create(TopHeadlinesApi::class.java)
    @Provides
    @Singleton
    fun providesRetrofit(
        networkJson: Json,
        okhttpCallFactory: Call.Factory,
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(
            networkJson.asConverterFactory("application/json".toMediaType()),
        )
        .callFactory(okhttpCallFactory)
        .build()

    @Provides
    @Singleton
    fun imageLoader(
        okHttpCallFactory: Call.Factory,
        @ApplicationContext context: Context,
    ): ImageLoader = ImageLoader.Builder(context)
        .callFactory(okHttpCallFactory)
        .respectCacheHeaders(false)
        .diskCache {
            DiskCache.Builder()
                .directory(context.cacheDir.resolve("image_cache"))
                .maxSizePercent(0.02)
                .build()
        }
        .apply {
            if (BuildConfig.DEBUG) {
                logger(DebugLogger())
            }
        }
        .build()
}