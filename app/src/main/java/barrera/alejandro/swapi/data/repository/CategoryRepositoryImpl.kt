package barrera.alejandro.swapi.data.repository

import barrera.alejandro.swapi.domain.Result
import barrera.alejandro.swapi.data.local.dao.CategoryDao
import barrera.alejandro.swapi.data.mapper.toCategory
import barrera.alejandro.swapi.domain.repository.CategoryRepository
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