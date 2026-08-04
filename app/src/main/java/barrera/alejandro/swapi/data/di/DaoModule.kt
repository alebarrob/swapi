package barrera.alejandro.swapi.data.di

import barrera.alejandro.swapi.data.local.dao.CategoryDao
import barrera.alejandro.swapi.data.local.dao.FoodDao
import barrera.alejandro.swapi.data.local.database.SwapiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Singleton
    @Provides
    fun provideCategoryDao(database: SwapiDatabase): CategoryDao = database.categoryDao()

    @Singleton
    @Provides
    fun provideFoodDao(database: SwapiDatabase): FoodDao = database.foodDao()
}