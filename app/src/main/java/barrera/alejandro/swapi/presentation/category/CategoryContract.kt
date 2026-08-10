package barrera.alejandro.swapi.presentation.category

import barrera.alejandro.swapi.presentation.model.CategoryUi

object CategoryContract {
    sealed interface State {
        data object Loading : State
        data class Success(val categories: List<CategoryUi>) : State
        data object Failure : State
    }
}