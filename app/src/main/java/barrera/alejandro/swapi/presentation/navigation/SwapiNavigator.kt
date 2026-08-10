package barrera.alejandro.swapi.presentation.navigation

class SwapiNavigator(private val backStack: MutableList<SwapiNavKey>) {

    fun navigate(destination: SwapiNavKey) {
        backStack.add(destination)
    }

    fun goBack() {
        if (backStack.size > 1) backStack.removeAt(backStack.lastIndex)
    }

    fun resetToRoot() {
        if (backStack.size > 1) {
            backStack
                .subList(fromIndex = 1, toIndex = backStack.size)
                .clear()
        }
    }
}