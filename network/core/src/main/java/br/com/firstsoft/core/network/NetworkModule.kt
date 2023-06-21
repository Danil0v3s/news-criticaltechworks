package br.com.firstsoft.core.network

import br.com.firstsoft.core.network.adapter.InstantJsonAdapter
import br.com.firstsoft.core.network.interceptor.ApiKeyInterceptor
import com.slack.eithernet.ApiResultCallAdapterFactory
import com.slack.eithernet.ApiResultConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Provides
    fun provideOkHttpClient(
        apiKeyInterceptor: ApiKeyInterceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMoshi(
        // adapters: Set<@JvmSuppressWildcards JsonAdapter<*>>,
        // factories: Set<@JvmSuppressWildcards JsonAdapter.Factory>,
        // @MoshiAdapter moshiAdapters: Set<@JvmSuppressWildcards Any>,
    ): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .add(InstantJsonAdapter)
        // .apply { adapters.forEach { add(it) } }
        // .apply { factories.forEach { add(it) } }
        // .apply { moshiAdapters.forEach { add(it) } }
        .build()

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(ApiResultConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(ApiResultCallAdapterFactory)
            .baseUrl("https://newsapi.org/v2/")
            .client(okHttpClient)
            .build()
    }
}
