package id.my.davezy.todoapps.screens.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.my.davezy.todoapps.databinding.ItemTaskListBinding
import id.my.davezy.todoapps.domain.models.ChecklistModel

class TaskListAdapter : ListAdapter<ChecklistModel, TaskListAdapter.TaskListViewHolder>(COMPARATOR) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskListViewHolder {
    return TaskListViewHolder(
      ItemTaskListBinding.inflate(
        LayoutInflater.from(parent.context),
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
    getItem(position)?.let { holder.bind(it) }
  }

  inner class TaskListViewHolder(
    private val binding: ItemTaskListBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ChecklistModel) {
      binding.apply {
        textTitle.text = item.name
        textDate.text = item.dueDate
      }
    }
  }

  companion object {
    private val COMPARATOR = object: DiffUtil.ItemCallback<ChecklistModel>() {
      override fun areItemsTheSame(oldItem: ChecklistModel, newItem: ChecklistModel): Boolean {
        return oldItem.uId == newItem.uId
      }

      override fun areContentsTheSame(oldItem: ChecklistModel, newItem: ChecklistModel): Boolean {
        return oldItem == newItem
      }
    }
  }
}