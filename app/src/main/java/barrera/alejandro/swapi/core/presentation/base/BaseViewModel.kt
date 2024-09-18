package barrera.alejandro.swapi.core.presentation.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State, Event>(initialState: State) : ViewModel() {
    var state by mutableStateOf<State>(initialState)
        protected set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    abstract fun onEvent(event: Event)

    protected suspend fun sendUiEvent(event: UiEvent) {
        _uiEvent.send(event)
    }
}