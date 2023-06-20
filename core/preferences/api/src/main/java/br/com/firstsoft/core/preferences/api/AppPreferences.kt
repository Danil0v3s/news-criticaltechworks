package br.com.firstsoft.core.preferences.api

import kotlinx.coroutines.flow.Flow

interface AppPreferences {
    fun observeString(key: String): Flow<String?>
    fun observeInt(key: String): Flow<Int?>

    suspend fun setString(key: String, value: String)
    suspend fun setInt(key: String, value: Int)
}
