package barrera.alejandro.swapi.food_swap.presentation.category_screen

import androidx.lifecycle.viewModelScope
import barrera.alejandro.swapi.core.presentation.base.BaseViewModel
import barrera.alejandro.swapi.core.util.annotation.GetAllCategoriesUseCase
import barrera.alejandro.swapi.core.presentation.base.UiEvent
import barrera.alejandro.swapi.food_swap.domain.model.Category
import barrera.alejandro.swapi.food_swap.domain.use_case.SuspendUseCaseNoParams
import barrera.alejandro.swapi.food_swap.presentation.mapper.toCategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    @GetAllCategoriesUseCase
    private val getAllCategories: SuspendUseCaseNoParams<List<Category>>
) : BaseViewModel<CategoryScreenState, CategoryScreenEvent>(initialState = CategoryScreenState()) {

    override fun onEvent(event: CategoryScreenEvent) {
        when (event) {
            is CategoryScreenEvent.LoadCategories -> loadCategories()
        }
    }

    private fun loadCategories() {
        state = state.copy(isLoading = true)
        viewModelScope.launch {
            getAllCategories().fold(
                success = { categories ->
                    state = state.copy(
                        categories = categories.map { category ->
                            category.toCategoryUi()
                        },
                        isLoading = false
                    )
                },
                failure = {
                    state = state.copy(isLoading = false)
                    sendUiEvent(UiEvent.ShowErrorPopup)
                }
            )
        }
    }
}