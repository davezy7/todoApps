package id.my.davezy.todoapps.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import id.my.davezy.todoapps.data.dao.ChecklistDao
import id.my.davezy.todoapps.data.database.AppDatabase
import id.my.davezy.todoapps.data.utils.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

  @Provides
  @Singleton
  fun provideAppDatabase(
    @ApplicationContext context: Context
  ) : AppDatabase {
    return Room.databaseBuilder(
      context,
      AppDatabase::class.java, Constants.DB_NAME
    ).build()
  }

  @Provides
  @Singleton
  fun provideChecklistDao(
    database: AppDatabase
  ) : ChecklistDao {
    return database.checklistDao()
  }

}