package br.com.firstsoft.core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import br.com.firstsoft.core.preferences.api.AppPreferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AppPreferencesImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    override fun observeString(key: String): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(key)]
        }
    }

    override fun observeInt(key: String): Flow<Int?> {
        return dataStore.data.map { preferences ->
            preferences[intPreferencesKey(key)]
        }
    }

    override suspend fun setString(key: String, value: String) {
        dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    override suspend fun setInt(key: String, value: Int) {
        dataStore.edit {
            it[intPreferencesKey(key)] = value
        }
    }
}

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AppPreferencesModule {

    @Binds
    abstract fun bind(impl: AppPreferencesImpl): AppPreferences
}
