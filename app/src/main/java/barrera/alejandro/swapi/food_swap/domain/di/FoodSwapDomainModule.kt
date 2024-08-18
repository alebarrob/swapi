package barrera.alejandro.swapi.food_swap.domain.di

import barrera.alejandro.swapi.core.util.annotation.GetAllCategoriesUseCase
import barrera.alejandro.swapi.food_swap.domain.model.Category
import barrera.alejandro.swapi.food_swap.domain.repository.CategoryRepository
import barrera.alejandro.swapi.food_swap.domain.use_case.GetAllCategories
import barrera.alejandro.swapi.food_swap.domain.use_case.UseCaseNoParams
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object FoodSwapDomainModule {
    @Provides
    @ViewModelScoped
    @GetAllCategoriesUseCase
    fun provideGetAllCategories(
        repository: CategoryRepository
    ): UseCaseNoParams<List<Category>> = GetAllCategories(repository)
}