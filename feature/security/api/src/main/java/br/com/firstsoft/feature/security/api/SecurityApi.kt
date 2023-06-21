package br.com.firstsoft.feature.security.api

interface SecurityApi {
    suspend fun requestAuthentication(): SecurityResult
}

sealed class SecurityResult {
    object Success : SecurityResult()
    object Error : SecurityResult()
    object Failure : SecurityResult()
    object NotAvailable : SecurityResult()
}
