package id.my.davezy.todoapps.screens.adapter

import android.util.TypedValue
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.my.davezy.todoapps.R
import id.my.davezy.todoapps.databinding.ItemTaskListBinding
import id.my.davezy.todoapps.domain.models.ChecklistModel
import id.my.davezy.todoapps.utils.ColorResource.getPrimaryColor


class TaskListAdapter :
  ListAdapter<ChecklistModel, TaskListAdapter.TaskListViewHolder>(COMPARATOR) {

  private var onItemClickListener: ((ChecklistModel) -> Unit)? = null

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

  fun setOnClickListener(listener: (ChecklistModel) -> Unit) {
    onItemClickListener = listener
  }

  fun notifyChanges(uId: Int?) {
    currentList.firstOrNull { it.uId == uId }?.let {
      val dataChanged = currentList.indexOf(it)
      notifyItemChanged(dataChanged)
    } ?: return
  }

  inner class TaskListViewHolder(
    private val binding: ItemTaskListBinding
  ) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ChecklistModel) {
      binding.apply {
        if (item.isDone) root.setCardBackgroundColor(root.context.getPrimaryColor())
        textTitle.text = item.name
        textDate.text = item.dueDate
        cl.setOnClickListener { onItemClickListener?.invoke(item) }
      }
    }
  }

  companion object {
    private val COMPARATOR = object : DiffUtil.ItemCallback<ChecklistModel>() {
      override fun areItemsTheSame(oldItem: ChecklistModel, newItem: ChecklistModel): Boolean {
        return oldItem.uId == newItem.uId && oldItem.isDone == newItem.isDone
      }

      override fun areContentsTheSame(oldItem: ChecklistModel, newItem: ChecklistModel): Boolean {
        return oldItem == newItem
      }
    }
  }
}