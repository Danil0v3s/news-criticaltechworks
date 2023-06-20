package br.com.firstsoft.core.network.interceptor

import br.com.firstsoft.core.preferences.api.AppPreferences
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

internal class HostnameInterceptor @AssistedInject constructor(
    @Assisted private val preferencesKey: String,
    private val appPreferences: AppPreferences
) : Interceptor {

    @AssistedFactory
    interface Factory {
        fun create(preferencesKey: String): HostnameInterceptor
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val hostAndPort =
            runBlocking {
                appPreferences.observeString(preferencesKey).firstOrNull().orEmpty()
            }.split(":")

        if (hostAndPort.size == 1) {
            return chain.proceed(original)
        }

        val host = hostAndPort.first()
        val port = hostAndPort.last().toInt()

        val url = original.url.newBuilder().apply {
            host(host)
            port(port)
        }.build()

        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }
}
