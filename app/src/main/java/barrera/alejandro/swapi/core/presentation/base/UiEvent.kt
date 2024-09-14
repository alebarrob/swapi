package barrera.alejandro.swapi.core.presentation.base

import barrera.alejandro.swapi.core.presentation.util.UiText

sealed class UiEvent {
    data object ShowErrorPopup: UiEvent()
    data class ShowToast(val message: UiText): UiEvent()
}