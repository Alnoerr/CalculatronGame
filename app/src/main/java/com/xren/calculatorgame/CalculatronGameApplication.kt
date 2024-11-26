package com.xren.calculatorgame

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.xren.calculatorgame.data.CalculatronGameRepository

private const val SAVE = "Save"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = SAVE
)

class CalculatronGameApplication : Application() {
    lateinit var repository: CalculatronGameRepository
    override fun onCreate() {
        super.onCreate()
        repository = CalculatronGameRepository(this.dataStore)
    }
}