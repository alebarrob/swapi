package barrera.alejandro.swapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable
import kotlinx.serialization.serializer

@Serializable
sealed interface SwapiNavKey : NavKey

@Serializable
data object Category : SwapiNavKey

@Serializable
data class FoodSelection(val categoryId: Int) : SwapiNavKey

@Serializable
data class FoodAmountSelection(val foodId: Int) : SwapiNavKey

@Serializable
data class FoodResult(val foodId: Int, val amount: String) : SwapiNavKey

@Composable
fun rememberSwapiNavBackStack(vararg destinations: SwapiNavKey): NavBackStack<SwapiNavKey> =
    rememberSerializable(serializer = serializer<NavBackStack<SwapiNavKey>>()) {
        NavBackStack(*destinations)
    }