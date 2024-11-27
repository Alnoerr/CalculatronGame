package com.xren.calculatorgame.data

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException

class CalculatronGameRepository(
    private val dataStore: DataStore<Preferences>
) {
    private companion object {
        val LEVEL = intPreferencesKey("level")
        const val TAG = "CalculatronGameRepository"
    }

    val level: Flow<Int> = dataStore.data
        .catch {
            if (it is IOException) {
                Log.e(TAG, "Error reading level data.", it)
                emit(emptyPreferences())
            } else {
                throw it
            }
        }
        .map { preferences ->
            preferences[LEVEL] ?: 1
        }
    suspend fun clearProgress() {
        dataStore.edit { preferences ->
            preferences[LEVEL] = 1
        }
    }
    suspend fun incrementLevel() {
        dataStore.edit { preferences ->
            preferences[LEVEL] = level.first() + 1
        }
    }
}