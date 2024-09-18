package barrera.alejandro.swapi.food_swap.data.repository

import barrera.alejandro.swapi.core.domain.Result
import barrera.alejandro.swapi.food_swap.data.dao.FoodDao
import barrera.alejandro.swapi.food_swap.data.mapper.toFood
import barrera.alejandro.swapi.food_swap.domain.repository.FoodRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class FoodRepositoryImpl(
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
}