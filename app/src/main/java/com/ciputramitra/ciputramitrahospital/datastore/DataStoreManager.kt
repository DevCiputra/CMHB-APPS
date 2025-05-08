package com.ciputramitra.ciputramitrahospital.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ciputramitra.ciputramitrahospital.response.auth.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

    companion object {
        val USER_TOKEN = stringPreferencesKey("USER_TOKEN")
        val USER_NAME = stringPreferencesKey("USER_NAME")
        val USER_EMAIL = stringPreferencesKey("USER_EMAIL")
        val USER_WHATSAPP = stringPreferencesKey("USER_WHATSAPP")
        val USER_KOTA = stringPreferencesKey("USER_KOTA")
        val USER_PROVINSI = stringPreferencesKey("USER_PROVINSI")
        val USER_ROLE = stringPreferencesKey("USER_ROLE")
        val USER_AVATAR = stringPreferencesKey("USER_AVATAR")
        val USER_ID = intPreferencesKey("USER_ID")
        val USER_STATUS = stringPreferencesKey("USER_STATUS")


    }


    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN] = token
        }
    }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[USER_NAME] = user.name
            mutablePreferences[USER_EMAIL] = user.email
            mutablePreferences[USER_WHATSAPP] = user.whatsaap
            mutablePreferences[USER_KOTA] = user.kota
            mutablePreferences[USER_PROVINSI] = user.provinsi
            mutablePreferences[USER_ROLE] = user.role
            mutablePreferences[USER_ID] = user.id
            mutablePreferences[USER_AVATAR] = user.avatar
            mutablePreferences[USER_STATUS] = user.statusAktif

        }
    }


    val tokenFlow: Flow<String?> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            preferences[USER_TOKEN]
        }

    val userFlow: Flow<User?> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            User(
                avatar = preferences[USER_AVATAR] ?: "",
                createdAt = "",
                email = preferences[USER_EMAIL] ?: "",
                id = preferences[USER_ID] ?: 0,
                kota = preferences[USER_KOTA] ?: "",
                name = preferences[USER_NAME] ?: "",
                provinsi = preferences[USER_PROVINSI] ?: "",
                role = preferences[USER_ROLE] ?: "",
                statusAktif = preferences[USER_STATUS] ?: "",
                updatedAt = "",
                whatsaap = preferences[USER_WHATSAPP] ?: ""
            )
        }

    suspend fun clearDataStore() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}