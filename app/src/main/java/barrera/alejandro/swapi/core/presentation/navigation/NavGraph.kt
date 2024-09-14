package barrera.alejandro.swapi.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import barrera.alejandro.swapi.food_swap.presentation.category_screen.CategoryScreen
import barrera.alejandro.swapi.food_swap.presentation.category_screen.CategoryViewModel
import barrera.alejandro.swapi.food_swap.presentation.food_amount_selection_screen.FoodAmountSelectionScreen
import barrera.alejandro.swapi.food_swap.presentation.food_amount_selection_screen.FoodAmountSelectionViewModel
import barrera.alejandro.swapi.food_swap.presentation.food_result_screen.FoodResultScreen
import barrera.alejandro.swapi.food_swap.presentation.food_result_screen.FoodResultViewModel
import barrera.alejandro.swapi.food_swap.presentation.food_selection_screen.FoodSelectionScreen
import barrera.alejandro.swapi.food_swap.presentation.food_selection_screen.FoodSelectionViewModel

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Category
    ) {
        composable<Category> {
            CategoryScreen(
                onCategoryClick = { categoryId ->
                    navController.navigate(route = FoodSelection(categoryId))
                },
                viewModel = hiltViewModel<CategoryViewModel>()
            )
        }
        composable<FoodSelection> {
            FoodSelectionScreen(
                onFoodClick = { foodId ->
                    navController.navigate(
                        route = FoodAmountSelection(foodId)
                    )
                },
                viewModel = hiltViewModel<FoodSelectionViewModel>()
            )
        }
        composable<FoodAmountSelection> {
            FoodAmountSelectionScreen(
                onCalculateClick = { foodId, amount ->
                    navController.navigate(route = FoodResult(foodId, amount))
                },
                viewModel = hiltViewModel<FoodAmountSelectionViewModel>()
            )
        }
        composable<FoodResult> {
            FoodResultScreen(
                viewModel = hiltViewModel<FoodResultViewModel>()
            )
        }
    }
}