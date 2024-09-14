package barrera.alejandro.swapi.food_swap.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import barrera.alejandro.swapi.R
import barrera.alejandro.swapi.core.presentation.theme.LocalDimensions
import barrera.alejandro.swapi.core.presentation.theme.SwapiTheme
import barrera.alejandro.swapi.core.presentation.util.constant.PREVIEW_BACKGROUND

@Composable
fun DropDownButton(
    text: String,
    options: List<String>,
    onOptionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val typography = MaterialTheme.typography
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier) {
        ActionButton(
            text = text,
            onClick = {
                expanded = true
            }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { label ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = label,
                            style = typography.labelMedium
                        )
                    },
                    onClick = {
                        expanded = false
                        onOptionClick(label)
                    }
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    backgroundColor = PREVIEW_BACKGROUND
)
@Composable
private fun PreviewDropDownButton() {
    val categories = listOf("FRUTAS", "GRASAS Y PROTEÍNAS", "GRASAS", "CARBOHIDRATOS", "LÁCTEOS")

    SwapiTheme {
        val dimensions = LocalDimensions.current

        DropDownButton(
            text = stringResource(id = R.string.categories_screen_button_text),
            options = categories,
            onOptionClick = {  },
            modifier = Modifier.padding(horizontal = dimensions.large, vertical = dimensions.large)
        )
    }
}