package id.my.davezy.todoapps.domain.models

data class ChecklistModel(
  val uId: Int = 0,
  val name: String,
  val dueDate: String,
  val isDone: Boolean = false
)
