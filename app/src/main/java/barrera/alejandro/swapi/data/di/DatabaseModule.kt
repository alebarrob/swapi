package barrera.alejandro.swapi.data.di

import android.content.Context
import androidx.room.Room
import barrera.alejandro.swapi.data.local.database.SwapiDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideSwapiDatabase(@ApplicationContext context: Context): SwapiDatabase =
        Room.databaseBuilder(
            context = context,
            klass = SwapiDatabase::class.java,
            name = SwapiDatabase.DATABASE_NAME
        ).createFromAsset(databaseFilePath = SwapiDatabase.FILE_PATH)
            .build()
}