package barrera.alejandro.swapi.data.di

import barrera.alejandro.swapi.data.repository.CategoryRepositoryImpl
import barrera.alejandro.swapi.data.repository.FoodRepositoryImpl
import barrera.alejandro.swapi.domain.repository.CategoryRepository
import barrera.alejandro.swapi.domain.repository.FoodRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        implementation: CategoryRepositoryImpl,
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindFoodRepository(
        implementation: FoodRepositoryImpl,
    ): FoodRepository
}