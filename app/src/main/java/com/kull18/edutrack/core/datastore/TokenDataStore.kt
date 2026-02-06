package com.kull18.edutrack.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.kull18.edutrack.features.login.domain.entities.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "auth_prefs")

class TokenDataStore(private val context: Context) {

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
        private val USER_ID_KEY = intPreferencesKey("user_id")
        private val USER_NOMBRE_KEY = stringPreferencesKey("user_nombre")
        private val USER_EMAIL_KEY = stringPreferencesKey("user_email")
        private val USER_ROL_KEY = stringPreferencesKey("user_rol")
        private val USER_CREATED_AT_KEY = stringPreferencesKey("user_created_at")
        private val USER_UPDATED_AT_KEY = stringPreferencesKey("user_updated_at")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { prefs ->
            prefs[TOKEN_KEY] = token
        }
    }

    fun getToken(): Flow<String?> {
        return context.dataStore.data.map { prefs ->
            prefs[TOKEN_KEY]
        }
    }

    suspend fun saveUser(user: User) {
        context.dataStore.edit { prefs ->
            prefs[USER_ID_KEY] = user.id
            prefs[USER_NOMBRE_KEY] = user.nombre
            prefs[USER_EMAIL_KEY] = user.email
            prefs[USER_ROL_KEY] = user.rol
            prefs[USER_CREATED_AT_KEY] = user.createdAt
            prefs[USER_UPDATED_AT_KEY] = user.updatedAt
        }
    }

    fun getUser(): Flow<User?> {
        return context.dataStore.data.map { prefs ->
            val id = prefs[USER_ID_KEY]
            val nombre = prefs[USER_NOMBRE_KEY]
            val email = prefs[USER_EMAIL_KEY]
            val rol = prefs[USER_ROL_KEY]
            val createdAt = prefs[USER_CREATED_AT_KEY]
            val updatedAt = prefs[USER_UPDATED_AT_KEY]

            if (id != null && nombre != null && email != null && rol != null && createdAt != null && updatedAt != null) {
                User(id, nombre, email, rol, createdAt, updatedAt)
            } else {
                null
            }
        }
    }

    suspend fun clearToken() {
        context.dataStore.edit { prefs ->
            prefs.remove(TOKEN_KEY)
        }
    }

    suspend fun clearUser() {
        context.dataStore.edit { prefs ->
            prefs.remove(USER_ID_KEY)
            prefs.remove(USER_NOMBRE_KEY)
            prefs.remove(USER_EMAIL_KEY)
            prefs.remove(USER_ROL_KEY)
            prefs.remove(USER_CREATED_AT_KEY)
            prefs.remove(USER_UPDATED_AT_KEY)
        }
    }

    suspend fun clearAll() {
        context.dataStore.edit { prefs ->
            prefs.clear()
        }
    }
}
