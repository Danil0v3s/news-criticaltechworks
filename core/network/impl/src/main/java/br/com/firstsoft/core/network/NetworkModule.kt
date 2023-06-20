package br.com.firstsoft.core.network

import br.com.firstsoft.core.network.api.qualifiers.RadarrOkHttpClient
import br.com.firstsoft.core.network.api.qualifiers.RadarrRetrofit
import br.com.firstsoft.core.network.api.qualifiers.SonarrOkHttpClient
import br.com.firstsoft.core.network.api.qualifiers.SonarrRetrofit
import br.com.firstsoft.core.network.interceptor.ApiKeyInterceptor
import br.com.firstsoft.core.network.interceptor.HostnameInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @RadarrOkHttpClient
    @Provides
    fun provideRadarrOkHttpClient(
        apiKeyInterceptorFactory: ApiKeyInterceptor.Factory,
        hostnameInterceptorFactory: HostnameInterceptor.Factory,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val apiKeyInterceptor = apiKeyInterceptorFactory.create("radarr-apikey")
        val hostNameInterceptor = hostnameInterceptorFactory.create("radarr-hostname")

        return OkHttpClient.Builder()
            .addInterceptor(hostNameInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @SonarrOkHttpClient
    @Provides
    fun provideSonarrOkHttpClient(
        apiKeyInterceptorFactory: ApiKeyInterceptor.Factory,
        hostnameInterceptorFactory: HostnameInterceptor.Factory,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val apiKeyInterceptor = apiKeyInterceptorFactory.create("sonarr-apikey")
        val hostNameInterceptor = hostnameInterceptorFactory.create("sonarr-hostname")

        return OkHttpClient.Builder()
            .addInterceptor(hostNameInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @RadarrRetrofit
    @Provides
    fun providesRadarrRetrofit(
        @RadarrOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://{host}/api/v3/")
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @SonarrRetrofit
    @Provides
    fun providesSonarrRetrofit(
        @SonarrOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("http://{host}/api/v3/")
            .build()
    }
}
