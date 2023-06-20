package br.com.firstsoft.core.network.api

import br.com.firstsoft.core.network.api.dto.NewsResponseDto
import com.slack.eithernet.ApiResult
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

interface NewsApi {

    @GET("top-headlines/")
    suspend fun fetchHeadlines(
        @Query("q") query: String? = null,
        @Query("pageSize") pageSize: Int? = null,
        @Query("page") page: Int? = null
    ): ApiResult<NewsResponseDto, Throwable>
}

@Module
@InstallIn(SingletonComponent::class)
internal object MyAccountApiModule {
    @Provides
    @Singleton
    fun provide(retrofit: Retrofit): NewsApi = retrofit.create()
}
