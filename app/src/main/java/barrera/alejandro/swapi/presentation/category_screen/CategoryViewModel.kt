package barrera.alejandro.swapi.presentation.category_screen

import androidx.lifecycle.viewModelScope
import barrera.alejandro.swapi.domain.use_case.GetAllCategories
import barrera.alejandro.swapi.presentation.base.BaseViewModel
import barrera.alejandro.swapi.presentation.mapper.toCategoryUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getAllCategories: GetAllCategories
) : BaseViewModel<CategoryScreenState, CategoryScreenEvent>(
    initialState = CategoryScreenState.Loading
) {

    init {
        onEvent(CategoryScreenEvent.LoadCategories)
    }

    override fun onEvent(event: CategoryScreenEvent) {
        when (event) {
            is CategoryScreenEvent.LoadCategories -> loadCategories()
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getAllCategories().fold(
                success = { categories ->
                    state = CategoryScreenState.Success(
                        categories = categories.map { category ->
                            category.toCategoryUi()
                        }
                    )
                },
                failure = {
                    state = CategoryScreenState.Failure
                }
            )
        }
    }
}