package barrera.alejandro.swapi.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import barrera.alejandro.swapi.food_swap.presentation.category_screen.CategoryScreen
import barrera.alejandro.swapi.food_swap.presentation.food_amount_selection_screen.FoodAmountSelectionScreen
import barrera.alejandro.swapi.food_swap.presentation.food_result_screen.FoodResultScreen
import barrera.alejandro.swapi.food_swap.presentation.food_selection_screen.FoodSelectionScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
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
                modifier = modifier
            )
        }
        composable<FoodSelection> {
            FoodSelectionScreen(
                onFoodClick = { foodId ->
                    navController.navigate(route = FoodAmountSelection(foodId))
                },
                modifier = modifier
            )
        }
        composable<FoodAmountSelection> {
            FoodAmountSelectionScreen(
                onCalculateClick = { foodId, amount ->
                    navController.navigate(route = FoodResult(foodId, amount))
                },
                modifier = modifier
            )
        }
        composable<FoodResult> {
            FoodResultScreen(modifier = modifier)
        }
    }
}