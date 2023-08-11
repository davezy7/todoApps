package id.my.davezy.todoapps.domain.repositories

import id.my.davezy.todoapps.domain.models.ChecklistModel

interface ChecklistRepository {

  suspend fun fetchAllChecklists(): List<ChecklistModel>

  suspend fun fetchChecklistById(id: Int): ChecklistModel

  suspend fun fetchChecklistDone(): List<ChecklistModel>

  suspend fun insertChecklist(checklist: ChecklistModel) : Long

  suspend fun deleteChecklist(uid: Int) : Int

  suspend fun updateChecklist(data: ChecklistModel) : Int

}