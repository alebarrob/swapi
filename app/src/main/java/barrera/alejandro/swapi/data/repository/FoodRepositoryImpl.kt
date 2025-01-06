package barrera.alejandro.swapi.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import barrera.alejandro.swapi.domain.Result
import barrera.alejandro.swapi.data.local.dao.FoodDao
import barrera.alejandro.swapi.data.local.data_store.DataStoreKeys
import barrera.alejandro.swapi.data.local.data_store.dataStore
import barrera.alejandro.swapi.data.mapper.toFood
import barrera.alejandro.swapi.domain.repository.FoodRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class FoodRepositoryImpl(
    private val context: Context,
    private val foodDao: FoodDao,
    private val dispatcher: CoroutineDispatcher
) : FoodRepository {
    override suspend fun getFoodsByCategoryId(categoryId: Int) = withContext(dispatcher) {
        Result.from {
            foodDao.getFoodsByCategoryId(categoryId)
                .map { foodWithCategoryAndUnit ->
                    foodWithCategoryAndUnit.toFood()
                }
        }
    }

    override suspend fun getFoodById(id: Int) = withContext(dispatcher) {
        Result.from {
            foodDao.getFoodById(id).toFood()
        }
    }

    override fun getFoodEquivalenceCount() = context.dataStore.data
        .map { preferences ->
            preferences[DataStoreKeys.FOOD_EQUIVALENCE_COUNT] ?: DEFAULT_FOOD_EQUIVALENCE_COUNT
        }
        .flowOn(dispatcher)

    override suspend fun incrementFoodEquivalenceCount() {
        withContext(dispatcher) {
            context.dataStore.edit { preferences ->
                val currentCount = preferences[DataStoreKeys.FOOD_EQUIVALENCE_COUNT]
                    ?: DEFAULT_FOOD_EQUIVALENCE_COUNT

                preferences[DataStoreKeys.FOOD_EQUIVALENCE_COUNT] = currentCount + INCREMENT
            }
        }
    }

    override suspend fun resetFoodEquivalenceCount() {
        withContext(dispatcher) {
            context.dataStore.edit { preferences ->
                preferences[DataStoreKeys.FOOD_EQUIVALENCE_COUNT] = DEFAULT_FOOD_EQUIVALENCE_COUNT
            }
        }
    }

    companion object {
        private const val DEFAULT_FOOD_EQUIVALENCE_COUNT = 0
        private const val INCREMENT = 1
    }
}