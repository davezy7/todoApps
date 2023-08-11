package id.my.davezy.todoapps.domain.usecase

import id.my.davezy.todoapps.domain.models.ChecklistModel
import id.my.davezy.todoapps.domain.utils.DataState
import kotlinx.coroutines.flow.Flow

interface ChecklistUseCase {

  fun fetchAllChecklists(): Flow<DataState<List<ChecklistModel>>>

  fun fetchChecklistById(id: Int): Flow<DataState<ChecklistModel>>

  fun fetchChecklistDone(): Flow<DataState<List<ChecklistModel>>>

  fun insertChecklist(checklist: ChecklistModel) : Flow<DataState<Long>>

  fun deleteChecklist(uid: Int) : Flow<DataState<Int>>

  fun updateChecklist(data: ChecklistModel) : Flow<DataState<Int>>

}