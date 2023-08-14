package id.my.davezy.todoapps.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import id.my.davezy.todoapps.domain.usecase.ChecklistUseCase
import id.my.davezy.todoapps.domain.usecase.ChecklistUseCaseImpl

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

  @ViewModelScoped
  @Binds
  abstract fun bindsChecklistUseCase(impl: ChecklistUseCaseImpl): ChecklistUseCase

}