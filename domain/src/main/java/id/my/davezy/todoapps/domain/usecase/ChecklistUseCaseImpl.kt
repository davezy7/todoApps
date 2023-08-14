package id.my.davezy.todoapps.domain.usecase

import id.my.davezy.todoapps.domain.di.IoDispatcher
import id.my.davezy.todoapps.domain.models.ChecklistModel
import id.my.davezy.todoapps.domain.repositories.ChecklistRepository
import id.my.davezy.todoapps.domain.utils.DataState
import id.my.davezy.todoapps.domain.utils.sendFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChecklistUseCaseImpl @Inject constructor(
  @IoDispatcher private val dispatcher: CoroutineDispatcher,
  private val repository: ChecklistRepository
): ChecklistUseCase {

  override fun fetchAllChecklists(): Flow<DataState<List<ChecklistModel>>> {
    return sendFlow(dispatcher) { repository.fetchAllChecklists() }
  }

  override fun fetchChecklistById(id: Int): Flow<DataState<ChecklistModel>> {
    return sendFlow(dispatcher) { repository.fetchChecklistById(id) }
  }

  override fun fetchChecklistDone(): Flow<DataState<List<ChecklistModel>>> {
    return sendFlow(dispatcher) { repository.fetchChecklistDone() }
  }

  override fun insertChecklist(checklist: ChecklistModel): Flow<DataState<Long>> {
    return sendFlow(dispatcher) { repository.insertChecklist(checklist) }
  }

  override fun deleteChecklist(uid: Int): Flow<DataState<Int>> {
    return sendFlow(dispatcher) { repository.deleteChecklist(uid) }
  }

  override fun updateChecklist(data: ChecklistModel): Flow<DataState<Int>> {
    return sendFlow(dispatcher) { repository.updateChecklist(data) }
  }
}