package br.com.firstsoft.core.network

interface ApiConfigurationProvider {
    fun provideApiKey(): String
    fun provideNewsSource(): String
}
