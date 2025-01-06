package barrera.alejandro.swapi.data.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

object DataStoreKeys {
    val FOOD_EQUIVALENCE_COUNT = intPreferencesKey("food_equivalence_count")
}