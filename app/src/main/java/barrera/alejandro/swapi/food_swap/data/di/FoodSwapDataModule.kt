package barrera.alejandro.swapi.food_swap.data.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.swapi.core.util.annotation.IoDispatcher
import barrera.alejandro.swapi.food_swap.data.dao.CategoryDao
import barrera.alejandro.swapi.food_swap.data.repository.CategoryRepositoryImpl
import barrera.alejandro.swapi.food_swap.data.database.SwapiDatabase
import barrera.alejandro.swapi.food_swap.domain.repository.CategoryRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FoodSwapDataModule {
    @Singleton
    @Provides
    fun provideSwapiDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context = context,
        klass = SwapiDatabase::class.java,
        name = "swapi_database"
    ).createFromAsset(databaseFilePath = "database/swapi_database.db")
        .build()

    @Singleton
    @Provides
    fun provideCategoryDao(database: SwapiDatabase) = database.categoryDao()

    @Provides
    @IoDispatcher
    fun provideIoDispatcher() = Dispatchers.IO

    @Singleton
    @Provides
    fun provideCategoryRepository(
        dao: CategoryDao,
        @IoDispatcher dispatcher: CoroutineDispatcher
    ): CategoryRepository = CategoryRepositoryImpl(dao, dispatcher)
}