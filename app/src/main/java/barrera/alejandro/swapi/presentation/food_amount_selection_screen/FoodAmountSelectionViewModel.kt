package barrera.alejandro.swapi.presentation.food_amount_selection_screen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.domain.use_case.GetFoodById
import barrera.alejandro.swapi.domain.use_case.IsValidFoodAmount
import barrera.alejandro.swapi.presentation.base.BaseViewModel
import barrera.alejandro.swapi.presentation.base.UiEvent
import barrera.alejandro.swapi.presentation.base.UiText
import barrera.alejandro.swapi.presentation.mapper.toFoodUi
import barrera.alejandro.swapi.presentation.navigation.FoodAmountSelection
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FoodAmountSelectionViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getFoodById: GetFoodById,
    private val isValidFoodAmount: IsValidFoodAmount,
) : BaseViewModel<FoodAmountSelectionScreenState, FoodAmountSelectionScreenEvent>(
    initialState = FoodAmountSelectionScreenState.Loading
) {

    init {
        onEvent(FoodAmountSelectionScreenEvent.LoadFood)
    }

    override fun onEvent(event: FoodAmountSelectionScreenEvent) {
        when (event) {
            is FoodAmountSelectionScreenEvent.LoadFood -> loadFood()
            is FoodAmountSelectionScreenEvent.InvalidFoodAmount -> onInvalidFoodAmount()
        }
    }

    private fun loadFood() {
        viewModelScope.launch {
            getFoodById(
                id = savedStateHandle.toRoute<FoodAmountSelection>().foodId
            ).fold(
                success = { food ->
                    state = FoodAmountSelectionScreenState.Success(
                        food = food.toFoodUi()
                    )
                },
                failure = {
                    state = FoodAmountSelectionScreenState.Failure
                }
            )
        }
    }

    private fun onInvalidFoodAmount() {
        viewModelScope.launch {
            sendUiEvent(
                UiEvent.ShowToast(
                    UiText.StringResource(R.string.invalid_food_amount_error)
                )
            )
        }
    }

    fun isValidFoodAmount(amount: String) = isValidFoodAmount.invoke(amount)
}