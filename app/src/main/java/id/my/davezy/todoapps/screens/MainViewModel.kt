package id.my.davezy.todoapps.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.my.davezy.todoapps.domain.di.IoDispatcher
import id.my.davezy.todoapps.domain.models.ChecklistModel
import id.my.davezy.todoapps.domain.usecase.ChecklistUseCase
import id.my.davezy.todoapps.domain.utils.DataState
import id.my.davezy.todoapps.utils.DataResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  private val useCase: ChecklistUseCase,
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

  val taskListState =
    DataResource<DataState<List<ChecklistModel>?>>(DataState.Success(listOf()))

  val createTaskState =
    DataResource<DataState<Long?>>(DataState.Success(null))

  fun getTasksList() {
    viewModelScope.launch(ioDispatcher) {
      useCase.fetchAllChecklists()
        .onEach { taskListState.setValue(it) }
        .launchIn(this)
    }
  }

  fun createTask(name: String, date: String) {
    val task = ChecklistModel(name = name, dueDate = date)
    viewModelScope.launch(ioDispatcher) {
      useCase.insertChecklist(task)
        .onEach { createTaskState.setValue(it) }
        .launchIn(this)
    }
  }

}