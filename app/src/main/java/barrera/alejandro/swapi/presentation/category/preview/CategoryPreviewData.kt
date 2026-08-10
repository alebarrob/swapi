package barrera.alejandro.swapi.presentation.category.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import barrera.alejandro.swapi.presentation.category.CategoryContract.State
import barrera.alejandro.swapi.presentation.model.CategoryUi

internal class CategoryStatePreviewParameterProvider :
    PreviewParameterProvider<State> {

    private val states = listOf(
        State.Loading,
        State.Success(categories = CategoryPreviewData.categories),
        State.Failure,
    )

    override val values: Sequence<State> =
        states.asSequence()

    override fun getDisplayName(index: Int): String? =
        when (states.getOrNull(index)) {
            State.Loading -> "Loading"
            is State.Success -> "Success"
            State.Failure -> "Failure"
            null -> null
        }
}

private object CategoryPreviewData {

    val categories = listOf(
        CategoryUi(
            id = 1,
            name = "FRUTAS",
        ),
        CategoryUi(
            id = 2,
            name = "PROTEÍNAS",
        ),
        CategoryUi(
            id = 3,
            name = "GRASAS",
        ),
        CategoryUi(
            id = 4,
            name = "CARBOHIDRATOS",
        ),
        CategoryUi(
            id = 5,
            name = "LÁCTEOS",
        ),
    )
}