package br.com.firstsoft.core.network.interceptor

import br.com.firstsoft.core.network.ApiConfigurationProvider
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class ApiKeyInterceptor @Inject constructor(
    private val apiConfigurationProvider: ApiConfigurationProvider
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = apiConfigurationProvider.provideApiKey()
        val newsSource = apiConfigurationProvider.provideNewsSource()
        val url = original.url.newBuilder()
            .addQueryParameter("apiKey", token)
            .addQueryParameter("sources", newsSource)
            .build()

        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
