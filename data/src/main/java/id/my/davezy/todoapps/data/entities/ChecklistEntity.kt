package id.my.davezy.todoapps.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import id.my.davezy.todoapps.data.utils.replaceIfNull
import id.my.davezy.todoapps.domain.models.ChecklistModel

@Entity(tableName = "checklists")
data class ChecklistEntity(
  @PrimaryKey(autoGenerate = true) val uId: Int,
  @ColumnInfo(name = "checklist_name") val name: String?,
  @ColumnInfo(name = "due_date") val dueDate: String?,
  @ColumnInfo(name = "is_done") val isDone: Boolean
) {
  companion object {
    fun toModel(entity: ChecklistEntity?): ChecklistModel {
      return ChecklistModel(
        uId = entity?.uId.replaceIfNull(),
        name = entity?.name.replaceIfNull(),
        dueDate = entity?.dueDate.replaceIfNull(),
        isDone = entity?.isDone ?: false
      )
    }

    fun fromModel(model: ChecklistModel): ChecklistEntity {
      return ChecklistEntity(
        uId = model.uId,
        name = model.name,
        dueDate = model.dueDate,
        isDone = model.isDone
      )
    }
  }
}
