package barrera.alejandro.swapi.data.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore

private const val DATA_STORE_NAME = "preferences"

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

object DataStoreKeys {
    val FOOD_EQUIVALENCE_COUNT = intPreferencesKey("food_equivalence_count")
}