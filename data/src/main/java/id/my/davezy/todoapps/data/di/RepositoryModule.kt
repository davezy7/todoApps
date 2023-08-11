package id.my.davezy.todoapps.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.my.davezy.todoapps.data.repositories.ChecklistRepositoryImpl
import id.my.davezy.todoapps.domain.repositories.ChecklistRepository

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

  @Binds
  @ViewModelScoped
  abstract fun bindsChecklistRepository(impl: ChecklistRepositoryImpl) : ChecklistRepository

}