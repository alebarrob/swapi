package barrera.alejandro.swapi.core.presentation.base

import barrera.alejandro.swapi.core.presentation.util.UiText

sealed class UiEvent {
    data class ShowPopup(val message: UiText): UiEvent()
}