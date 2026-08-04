package barrera.alejandro.swapi.data.repository

import barrera.alejandro.swapi.domain.Result
import barrera.alejandro.swapi.data.local.dao.CategoryDao
import barrera.alejandro.swapi.data.mapper.toCategory
import barrera.alejandro.swapi.domain.model.Category
import barrera.alejandro.swapi.domain.repository.CategoryRepository
import barrera.alejandro.swapi.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    @param:IoDispatcher
    private val dispatcher: CoroutineDispatcher,
) : CategoryRepository {
    override suspend fun getAllCategories(): Result<List<Category>> = withContext(dispatcher) {
        Result.from {
            categoryDao.getAllCategories()
                .map { entity ->
                    entity.toCategory()
                }
        }
    }
}