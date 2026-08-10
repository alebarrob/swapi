package barrera.alejandro.swapi.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import barrera.alejandro.swapi.presentation.category.CategoryScreen
import barrera.alejandro.swapi.presentation.category.CategoryViewModel
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionScreen
import barrera.alejandro.swapi.presentation.food_amount_selection.FoodAmountSelectionViewModel
import barrera.alejandro.swapi.presentation.food_result.FoodResultScreen
import barrera.alejandro.swapi.presentation.food_result.FoodResultViewModel
import barrera.alejandro.swapi.presentation.food_selection.FoodSelectionScreen
import barrera.alejandro.swapi.presentation.food_selection.FoodSelectionViewModel

@Composable
fun SwapiNavigation(
    backStack: NavBackStack<SwapiNavKey>,
    navigator: SwapiNavigator,
    modifier: Modifier = Modifier,
) {
    NavDisplay(
        backStack = backStack,
        onBack = navigator::goBack,
        modifier = modifier,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        entryProvider = entryProvider {
            entry<Category> {
                val viewModel: CategoryViewModel = hiltViewModel()

                CategoryScreen(
                    onNavigateToFoodSelection = { categoryId ->
                        navigator.navigate(destination = FoodSelection(categoryId = categoryId))
                    },
                    viewModel = viewModel,
                )
            }

            entry<FoodSelection> { key ->
                val viewModel = hiltViewModel<FoodSelectionViewModel, FoodSelectionViewModel.Factory>(
                    creationCallback = { factory ->
                        factory.create(key)
                    },
                )

                FoodSelectionScreen(
                    onFoodClick = { foodId ->
                        navigator.navigate(destination = FoodAmountSelection(foodId = foodId))
                    },
                    viewModel = viewModel,
                )
            }

            entry<FoodAmountSelection> { key ->
                val viewModel = hiltViewModel<FoodAmountSelectionViewModel, FoodAmountSelectionViewModel.Factory>(
                    creationCallback = { factory ->
                        factory.create(key)
                    },
                )

                FoodAmountSelectionScreen(
                    onCalculateClick = { foodId, amount ->
                        navigator.navigate(
                            destination = FoodResult(
                                foodId = foodId,
                                amount = amount,
                            ),
                        )
                    },
                    viewModel = viewModel,
                )
            }

            entry<FoodResult> { key ->
                val viewModel = hiltViewModel<FoodResultViewModel, FoodResultViewModel.Factory>(
                    creationCallback = { factory ->
                        factory.create(key)
                    },
                )

                FoodResultScreen(viewModel = viewModel)
            }
        },
    )
}