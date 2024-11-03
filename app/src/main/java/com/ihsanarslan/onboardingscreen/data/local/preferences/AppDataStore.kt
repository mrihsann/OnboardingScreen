package com.ihsanarslan.onboardingscreen.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppDataStore(private var context: Context) {
    private val Context.ds : DataStore<Preferences> by preferencesDataStore(name = "datastore")

    companion object {
        val ONBOARDING_KEY = booleanPreferencesKey("onboarding")
    }

    fun isOnboarding(): Flow<Boolean> {
        return context.ds.data.map {
            it[ONBOARDING_KEY] ?: false
        }
    }

    suspend fun setOnboarding(isOnboarding: Boolean) {
        context.ds.edit {
            it[ONBOARDING_KEY] = isOnboarding
        }
    }
}