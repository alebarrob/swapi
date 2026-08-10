package barrera.alejandro.swapi.presentation.food_selection

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.use_case.GetFoodsByCategoryId
import barrera.alejandro.swapi.presentation.food_selection.FoodSelectionContract.State
import barrera.alejandro.swapi.presentation.mapper.toFoodUi
import barrera.alejandro.swapi.presentation.navigation.FoodSelection
import barrera.alejandro.swapi.util.constant.WHILE_SUBSCRIBED_STOP_TIMEOUT_MILLIS
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@HiltViewModel
class FoodSelectionViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getFoodsByCategoryId: GetFoodsByCategoryId,
) : ViewModel() {

    private val categoryId = savedStateHandle.toRoute<FoodSelection>().categoryId

    val state: StateFlow<State> = getFoodsByCategoryId(categoryId)
        .map<List<Food>, State> { foods ->
            State.Success(
                foods = foods.map { food ->
                    food.toFoodUi()
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