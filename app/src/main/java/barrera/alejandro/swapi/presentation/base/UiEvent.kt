package barrera.alejandro.swapi.presentation.base

import barrera.alejandro.swapi.presentation.util.UiText

sealed class UiEvent {
    data class ShowToast(val message: UiText): UiEvent()
}