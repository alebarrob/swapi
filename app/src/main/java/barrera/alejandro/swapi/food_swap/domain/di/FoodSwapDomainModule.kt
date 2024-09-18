package barrera.alejandro.swapi.food_swap.domain.di

import barrera.alejandro.swapi.core.util.annotation.GetAllCategoriesUseCase
import barrera.alejandro.swapi.core.util.annotation.GetEquivalentFoodsUseCase
import barrera.alejandro.swapi.core.util.annotation.GetFoodsByCategoryIdUseCase
import barrera.alejandro.swapi.core.util.annotation.GetFoodByIdUseCase
import barrera.alejandro.swapi.core.util.annotation.IsValidFoodAmountUseCase
import barrera.alejandro.swapi.food_swap.domain.model.Category
import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.repository.CategoryRepository
import barrera.alejandro.swapi.food_swap.domain.repository.FoodRepository
import barrera.alejandro.swapi.food_swap.domain.use_case.GetAllCategories
import barrera.alejandro.swapi.food_swap.domain.use_case.GetEquivalentFoods
import barrera.alejandro.swapi.food_swap.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.food_swap.domain.use_case.GetFoodById
import barrera.alejandro.swapi.food_swap.domain.use_case.IsValidFoodAmount
import barrera.alejandro.swapi.food_swap.domain.use_case.SuspendUseCase
import barrera.alejandro.swapi.food_swap.domain.use_case.SuspendUseCaseNoParams
import barrera.alejandro.swapi.food_swap.domain.use_case.UseCase
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
    ): SuspendUseCaseNoParams<List<Category>> = GetAllCategories(repository)

    @Provides
    @ViewModelScoped
    @GetFoodsByCategoryIdUseCase
    fun provideGetFoodByCategoryId(
        repository: FoodRepository
    ): SuspendUseCase<GetFoodsByCategoryId.Params, List<Food>> = GetFoodsByCategoryId(repository)

    @Provides
    @ViewModelScoped
    @GetFoodByIdUseCase
    fun provideGetFoodById(
        repository: FoodRepository
    ): SuspendUseCase<GetFoodById.Params, Food> = GetFoodById(repository)

    @Provides
    @ViewModelScoped
    @IsValidFoodAmountUseCase
    fun provideIsFoodAmountValid(): UseCase<IsValidFoodAmount.Params, Boolean> = IsValidFoodAmount()

    @Provides
    @ViewModelScoped
    @GetEquivalentFoodsUseCase
    fun provideGetEquivalentFood(): UseCase<GetEquivalentFoods.Params, List<Food>> =
        GetEquivalentFoods()
}