package barrera.alejandro.swapi.domain.di

import barrera.alejandro.swapi.util.annotation.GetAllCategoriesUseCase
import barrera.alejandro.swapi.util.annotation.GetEquivalentFoodsUseCase
import barrera.alejandro.swapi.util.annotation.GetFoodsByCategoryIdUseCase
import barrera.alejandro.swapi.util.annotation.GetFoodByIdUseCase
import barrera.alejandro.swapi.util.annotation.IsValidFoodAmountUseCase
import barrera.alejandro.swapi.domain.model.Category
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.repository.CategoryRepository
import barrera.alejandro.swapi.domain.repository.FoodRepository
import barrera.alejandro.swapi.domain.use_case.FlowUseCaseNoParams
import barrera.alejandro.swapi.domain.use_case.GetAllCategories
import barrera.alejandro.swapi.domain.use_case.GetEquivalentFoods
import barrera.alejandro.swapi.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.domain.use_case.GetFoodById
import barrera.alejandro.swapi.domain.use_case.GetFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.IncrementFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.IsValidFoodAmount
import barrera.alejandro.swapi.domain.use_case.ResetFoodEquivalenceCount
import barrera.alejandro.swapi.domain.use_case.SuspendUseCase
import barrera.alejandro.swapi.domain.use_case.SuspendUseCaseNoParams
import barrera.alejandro.swapi.domain.use_case.SuspendUseCaseNoParamsNoResponse
import barrera.alejandro.swapi.domain.use_case.UseCase
import barrera.alejandro.swapi.util.annotation.GetFoodEquivalenceCountUseCase
import barrera.alejandro.swapi.util.annotation.IncrementFoodEquivalenceCountUseCase
import barrera.alejandro.swapi.util.annotation.ResetFoodEquivalenceCountUseCase
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

    @Provides
    @ViewModelScoped
    @GetFoodEquivalenceCountUseCase
    fun provideGetFoodEquivalenceCount(
        repository: FoodRepository
    ): FlowUseCaseNoParams<Int> = GetFoodEquivalenceCount(repository)

    @Provides
    @ViewModelScoped
    @IncrementFoodEquivalenceCountUseCase
    fun provideIncrementFoodEquivalenceCount(
        repository: FoodRepository
    ): SuspendUseCaseNoParamsNoResponse = IncrementFoodEquivalenceCount(repository)

    @Provides
    @ViewModelScoped
    @ResetFoodEquivalenceCountUseCase
    fun provideResetFoodEquivalenceCount(
        repository: FoodRepository
    ): SuspendUseCaseNoParamsNoResponse = ResetFoodEquivalenceCount(repository)
}