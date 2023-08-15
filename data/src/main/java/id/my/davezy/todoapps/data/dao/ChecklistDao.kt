package id.my.davezy.todoapps.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.my.davezy.todoapps.data.entities.ChecklistEntity

@Dao
interface ChecklistDao {

  @Query("SELECT * FROM checklists")
  suspend fun fetchAllChecklists(): List<ChecklistEntity>

  @Query("SELECT * FROM checklists WHERE uId = :id LIMIT 1")
  suspend fun fetchChecklistById(id: Int): ChecklistEntity

  @Query("SELECT * FROM checklists WHERE is_done = 1")
  suspend fun fetchChecklistDone(): List<ChecklistEntity>

  @Insert(onConflict = OnConflictStrategy.ABORT)
  suspend fun insertChecklist(checklist: ChecklistEntity) : Long

  @Query("DELETE FROM checklists WHERE uId = :uid")
  suspend fun deleteChecklist(uid: Int) : Int

  @Query("UPDATE checklists SET is_done = 1 WHERE uId = :uId")
  suspend fun setChecklistDone(uId: Int) : Int
}