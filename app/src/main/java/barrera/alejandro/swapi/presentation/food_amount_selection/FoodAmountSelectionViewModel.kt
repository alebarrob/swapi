package barrera.alejandro.swapi.presentation.food_amount_selection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.swapi.domain.model.Food
import barrera.alejandro.swapi.domain.use_case.GetFoodById
import barrera.alejandro.swapi.domain.use_case.IsValidFoodAmount
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.Action
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.Effect
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.FormState
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionContract.State
import barrera.alejandro.swapi.presentation.mapper.toFoodUi
import barrera.alejandro.swapi.presentation.navigation.FoodAmountSelection
import barrera.alejandro.swapi.util.constant.WHILE_SUBSCRIBED_STOP_TIMEOUT_MILLIS
import barrera.alejandro.swapi.util.extension.normalizeDecimalSeparator
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(
    assistedFactory = FoodAmountSelectionViewModel.Factory::class,
)
class FoodAmountSelectionViewModel @AssistedInject constructor(
    @Assisted route: FoodAmountSelection,
    getFoodById: GetFoodById,
    private val isValidFoodAmount: IsValidFoodAmount,
) : ViewModel() {

    private val foodId = route.foodId

    private val formState = MutableStateFlow(FormState())

    val effect: SharedFlow<Effect>
        field: MutableSharedFlow<Effect> = MutableSharedFlow()

    val state: StateFlow<State> = combine<Food, FormState, State>(
        getFoodById(foodId),
        formState,
    ) { food, formState ->
        State.Success(
            food = food.toFoodUi(),
            form = formState,
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

    fun onAction(action: Action) {
        when (action) {
            is Action.AmountChanged -> onAmountChanged(action.amount)
            Action.CalculateClicked -> onCalculateClicked()
        }
    }

    private fun onAmountChanged(amount: String) {
        formState.update { formState ->
            formState.copy(
                amount = amount,
                amountHasError = false,
            )
        }
    }

    private fun onCalculateClicked() {
        val amount = formState.value.amount

        if (!isValidFoodAmount(amount)) {
            formState.update { formState ->
                formState.copy(amountHasError = true)
            }
            return
        }

        viewModelScope.launch {
            effect.emit(
                Effect.NavigateToEquivalences(
                    foodId = foodId,
                    amount = amount.normalizeDecimalSeparator(),
                )
            )
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(route: FoodAmountSelection): FoodAmountSelectionViewModel
    }
}