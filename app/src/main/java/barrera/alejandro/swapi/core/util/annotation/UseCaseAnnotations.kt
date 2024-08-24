package barrera.alejandro.swapi.core.util.annotation

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetAllCategoriesUseCase

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GetFoodByCategoryIdUseCase