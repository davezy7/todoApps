package id.my.davezy.todoapps.data.repositories

import id.my.davezy.todoapps.data.dao.ChecklistDao
import id.my.davezy.todoapps.data.entities.ChecklistEntity
import id.my.davezy.todoapps.domain.di.IoDispatcher
import id.my.davezy.todoapps.domain.models.ChecklistModel
import id.my.davezy.todoapps.domain.repositories.ChecklistRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChecklistRepositoryImpl @Inject constructor(
  private val dao: ChecklistDao,
  @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ChecklistRepository {

  override suspend fun fetchAllChecklists(): List<ChecklistModel> {
    return withContext(ioDispatcher) {
      try {
        val data = dao.fetchAllChecklists()
        return@withContext data.map { ChecklistEntity.toModel(it) }
      } catch (t: Throwable) {
        throw t
      }
    }
  }

  override suspend fun fetchChecklistById(id: Int): ChecklistModel {
    return withContext(ioDispatcher) {
      try {
        val data = dao.fetchChecklistById(id)
        return@withContext ChecklistEntity.toModel(data)
      } catch (t: Throwable) {
        throw t
      }
    }
  }

  override suspend fun fetchChecklistDone(): List<ChecklistModel> {
    return withContext(ioDispatcher) {
      try {
        val data = dao.fetchChecklistDone()
        return@withContext data.map { ChecklistEntity.toModel(it) }
      } catch (t: Throwable) {
        throw t
      }
    }
  }

  override suspend fun insertChecklist(checklist: ChecklistModel): Long {
    return withContext(ioDispatcher) {
      try {
        val data = ChecklistEntity.fromModel(checklist)
        return@withContext dao.insertChecklist(data)
      } catch (t: Throwable) {
        throw t
      }
    }
  }

  override suspend fun deleteChecklist(uid: Int): Int {
    return withContext(ioDispatcher) {
      try {
        return@withContext dao.deleteChecklist(uid)
      } catch (t: Throwable) {
        throw t
      }
    }
  }

  override suspend fun updateChecklist(uId: Int): Int {
    return withContext(ioDispatcher) {
      try {
        return@withContext dao.setChecklistDone(uId)
      } catch (t: Throwable) {
        throw t
      }
    }
  }
}