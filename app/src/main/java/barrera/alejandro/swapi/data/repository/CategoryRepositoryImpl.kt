package barrera.alejandro.swapi.data.repository

import barrera.alejandro.swapi.data.local.dao.CategoryDao
import barrera.alejandro.swapi.data.mapper.toCategory
import barrera.alejandro.swapi.domain.model.Category
import barrera.alejandro.swapi.domain.repository.CategoryRepository
import barrera.alejandro.swapi.util.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    @param:IoDispatcher
    private val dispatcher: CoroutineDispatcher,
) : CategoryRepository {
    override fun getAllCategories(): Flow<List<Category>> = categoryDao.getAllCategories()
        .map { entities ->
            entities.map { entity ->
                entity.toCategory()
            }
        }
        .flowOn(dispatcher)
}