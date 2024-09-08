package barrera.alejandro.swapi.food_swap.presentation.food_result_screen

import androidx.lifecycle.viewModelScope
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.base.BaseViewModel
import barrera.alejandro.swapi.core.presentation.base.UiEvent
import barrera.alejandro.swapi.core.presentation.util.UiText
import barrera.alejandro.swapi.core.util.annotation.GetEquivalentFoodUseCase
import barrera.alejandro.swapi.food_swap.domain.model.Food
import barrera.alejandro.swapi.food_swap.domain.use_case.GetEquivalentFood
import barrera.alejandro.swapi.food_swap.domain.use_case.UseCase
import barrera.alejandro.swapi.food_swap.presentation.mapper.toFood
import barrera.alejandro.swapi.food_swap.presentation.mapper.toFoodUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodResultViewModel @Inject constructor(
    @GetEquivalentFoodUseCase
    private val getEquivalentFoodUseCase:
        UseCase<@JvmSuppressWildcards GetEquivalentFood.Params, @JvmSuppressWildcards List<Food>>
) : BaseViewModel<FoodResultScreenState, FoodResultScreenEvent>(
    initialState = FoodResultScreenState()
) {

    override fun onEvent(event: FoodResultScreenEvent) {
        when (event) {
            is FoodResultScreenEvent.LoadEquivalentFood -> loadEquivalentFood()
        }
    }

    private fun loadEquivalentFood() {
        state.selectedFood?.let { selectedFood ->
            state = state.copy(isLoading = true)
            viewModelScope.launch {
                getEquivalentFoodUseCase(
                    params = GetEquivalentFood.Params(
                        food = selectedFood.toFood(),
                        foodAmount = state.selectedAmount.toDouble()
                    )
                ).fold(
                    success = { equivalentFood ->
                        state = state.copy(
                            equivalentFood = equivalentFood.map { it.toFoodUi() },
                            isLoading = false
                        )
                    },
                    failure = {
                        state = state.copy(isLoading = false)
                        sendUiEvent(
                            UiEvent.ShowPopup(
                                UiText.StringResource(R.string.database_error)
                            )
                        )
                    }
                )
            }
        }
    }
}