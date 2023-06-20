package br.com.firstsoft.core.network.interceptor

import br.com.firstsoft.core.preferences.api.AppPreferences
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor @AssistedInject constructor(
    @Assisted private val preferencesKey: String,
    private val appPreferences: AppPreferences
) : Interceptor {

    @AssistedFactory
    interface Factory {
        fun create(preferencesKey: String): ApiKeyInterceptor
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = runBlocking { appPreferences.observeString(preferencesKey).firstOrNull() }
        val url = original.url.newBuilder().addQueryParameter("apikey", token).build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
