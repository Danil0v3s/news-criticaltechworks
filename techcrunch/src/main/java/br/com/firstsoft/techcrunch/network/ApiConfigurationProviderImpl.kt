package br.com.firstsoft.techcrunch.network

import android.content.Context
import br.com.firstsoft.controlarr.R
import br.com.firstsoft.core.network.ApiConfigurationProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

internal class ApiConfigurationProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ApiConfigurationProvider {
    override fun provideApiKey(): String = context.resources.getString(R.string.apikey)

    override fun provideNewsSource(): String = context.resources.getString(R.string.source)
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ApiConfigurationProviderModule {
    @Binds
    abstract fun bind(impl: ApiConfigurationProviderImpl): ApiConfigurationProvider
}
