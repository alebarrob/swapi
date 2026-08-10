package barrera.alejandro.swapi.data.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import barrera.alejandro.swapi.data.local.dao.FoodDao
import barrera.alejandro.swapi.data.local.data_store.DataStoreKeys
import barrera.alejandro.swapi.data.local.data_store.dataStore
import barrera.alejandro.swapi.data.mapper.toFood
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.repository.FoodRepository
import barrera.alejandro.swapi.util.annotation.IoDispatcher
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    @param:ApplicationContext
    private val context: Context,
    private val foodDao: FoodDao,
    @param:IoDispatcher
    private val dispatcher: CoroutineDispatcher,
) : FoodRepository {
    override fun getFoodsByCategoryId(categoryId: Int): Flow<List<Food>> =
        foodDao.getFoodsByCategoryId(categoryId)
            .map { entities ->
                entities.map { entity ->
                    entity.toFood()
                }
            }
            .flowOn(dispatcher)

    override fun getFoodById(id: Int): Flow<Food> = foodDao.getFoodById(id)
        .map { entity ->
            entity.toFood()
        }
        .flowOn(dispatcher)

    override fun getFoodEquivalenceCount(): Flow<Int> = context.dataStore.data
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