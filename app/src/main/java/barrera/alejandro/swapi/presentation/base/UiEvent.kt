package barrera.alejandro.swapi.presentation.base

sealed class UiEvent {
    data class ShowToast(val message: UiText): UiEvent()
    data class ShowAd(val onAdDismissed: () -> Unit): UiEvent()
}