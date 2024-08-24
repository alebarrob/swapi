package barrera.alejandro.swapi.food_swap.data.repository

import barrera.alejandro.swapi.core.domain.Result
import barrera.alejandro.swapi.food_swap.data.dao.CategoryDao
import barrera.alejandro.swapi.food_swap.data.mapper.toCategory
import barrera.alejandro.swapi.food_swap.domain.repository.CategoryRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class CategoryRepositoryImpl(
    private val categoryDao: CategoryDao,
    private val dispatcher: CoroutineDispatcher
) : CategoryRepository {
    override suspend fun getAllCategories() = withContext(dispatcher) {
        Result.from {
            categoryDao.getAllCategories()
                .map { entity ->
                    entity.toCategory()
                }
        }
    }
}