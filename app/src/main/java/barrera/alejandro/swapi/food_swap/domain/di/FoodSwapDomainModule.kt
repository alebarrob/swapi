package barrera.alejandro.swapi.food_swap.domain.di

import barrera.alejandro.swapi.core.util.annotation.GetAllCategoriesUseCase
import barrera.alejandro.swapi.core.util.annotation.GetEquivalentFoodUseCase
import barrera.alejandro.swapi.core.util.annotation.GetFoodByCategoryIdUseCase
import barrera.alejandro.swapi.core.util.annotation.GetFoodByIdUseCase
import barrera.alejandro.swapi.core.util.annotation.IsFoodAmountValidUseCase
import barrera.alejandro.swapi.food_swap.domain.model.Category
import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.repository.CategoryRepository
import barrera.alejandro.swapi.food_swap.domain.repository.FoodRepository
import barrera.alejandro.swapi.food_swap.domain.use_case.GetAllCategories
import barrera.alejandro.swapi.food_swap.domain.use_case.GetEquivalentFood
import barrera.alejandro.swapi.food_swap.domain.use_case.GetFoodByCategoryId
import barrera.alejandro.swapi.food_swap.domain.use_case.GetFoodById
import barrera.alejandro.swapi.food_swap.domain.use_case.IsFoodAmountValid
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
    @GetFoodByCategoryIdUseCase
    fun provideGetFoodByCategoryId(
        repository: FoodRepository
    ): SuspendUseCase<GetFoodByCategoryId.Params, List<Food>> = GetFoodByCategoryId(repository)

    @Provides
    @ViewModelScoped
    @GetFoodByIdUseCase
    fun provideGetFoodById(
        repository: FoodRepository
    ): SuspendUseCase<GetFoodById.Params, Food> = GetFoodById(repository)

    @Provides
    @ViewModelScoped
    @IsFoodAmountValidUseCase
    fun provideIsFoodAmountValid(): UseCase<IsFoodAmountValid.Params, Boolean> = IsFoodAmountValid()

    @Provides
    @ViewModelScoped
    @GetEquivalentFoodUseCase
    fun provideGetEquivalentFood(): UseCase<GetEquivalentFood.Params, List<Food>> =
        GetEquivalentFood()
}