package barrera.alejandro.swapi.domain.di

import barrera.alejandro.swapi.domain.repository.CategoryRepository
import barrera.alejandro.swapi.domain.repository.FoodRepository
import barrera.alejandro.swapi.domain.use_case.GetAllCategories
import barrera.alejandro.swapi.domain.use_case.GetEquivalentFoods
import barrera.alejandro.swapi.domain.use_case.GetFoodById
import barrera.alejandro.swapi.domain.use_case.GetFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.domain.use_case.IncrementFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.IsValidFoodAmount
import barrera.alejandro.swapi.domain.use_case.ResetFoodEquivalenceCount
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    @ViewModelScoped
    fun provideGetAllCategories(
        repository: CategoryRepository
    ): GetAllCategories = GetAllCategories(repository)

    @Provides
    @ViewModelScoped
    fun provideGetFoodByCategoryId(
        repository: FoodRepository
    ): GetFoodsByCategoryId = GetFoodsByCategoryId(repository)

    @Provides
    @ViewModelScoped
    fun provideGetFoodById(
        repository: FoodRepository
    ): GetFoodById = GetFoodById(repository)

    @Provides
    @ViewModelScoped
    fun provideIsFoodAmountValid(): IsValidFoodAmount = IsValidFoodAmount()

    @Provides
    @ViewModelScoped
    fun provideGetEquivalentFood(): GetEquivalentFoods = GetEquivalentFoods()

    @Provides
    @ViewModelScoped
    fun provideGetFoodEquivalenceCount(
        repository: FoodRepository
    ): GetFoodEquivalenceCount = GetFoodEquivalenceCount(repository)

    @Provides
    @ViewModelScoped
    fun provideIncrementFoodEquivalenceCount(
        repository: FoodRepository
    ): IncrementFoodEquivalenceCount = IncrementFoodEquivalenceCount(repository)

    @Provides
    @ViewModelScoped
    fun provideResetFoodEquivalenceCount(
        repository: FoodRepository
    ): ResetFoodEquivalenceCount = ResetFoodEquivalenceCount(repository)
}