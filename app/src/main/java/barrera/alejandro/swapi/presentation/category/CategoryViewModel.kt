package barrera.alejandro.swapi.presentation.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.swapi.domain.model.Category
import barrera.alejandro.swapi.domain.use_case.GetAllCategories
import barrera.alejandro.swapi.presentation.category.CategoryContract.State
import barrera.alejandro.swapi.presentation.mapper.toCategoryUi
import barrera.alejandro.swapi.util.constant.WHILE_SUBSCRIBED_STOP_TIMEOUT_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class CategoryViewModel @Inject constructor(
    getAllCategories: GetAllCategories,
) : ViewModel() {

    val state: StateFlow<State> = getAllCategories()
        .map<List<Category>, State> { categories ->
            State.Success(
                categories = categories.map { category ->
                    category.toCategoryUi()
                },
            )
        }
        .catch {
            emit(State.Failure)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(
                stopTimeoutMillis = WHILE_SUBSCRIBED_STOP_TIMEOUT_MILLIS,
            ),
            initialValue = State.Loading,
        )
}