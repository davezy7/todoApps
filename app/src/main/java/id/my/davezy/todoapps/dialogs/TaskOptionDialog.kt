package id.my.davezy.todoapps.dialogs

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import id.my.davezy.todoapps.databinding.DialogTaskOptionBinding
import id.my.davezy.todoapps.domain.models.ChecklistModel

class TaskOptionDialog(
  private val task: ChecklistModel,
  private val listener: TaskOptionDialogButtonListener
): BaseDialog() {

  interface TaskOptionDialogButtonListener {
    fun onDeleteButtonClicked(task: ChecklistModel)
    fun onDoneButtonClicked(task: ChecklistModel)
  }

  private lateinit var binding: DialogTaskOptionBinding

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = DialogTaskOptionBinding.inflate(inflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.apply {
      btnDelete.setOnClickListener {
        listener.onDeleteButtonClicked(task)
        dismiss()
      }
      btnDone.apply {
        if (task.isDone) this.visibility = View.GONE
        setOnClickListener {
          listener.onDoneButtonClicked(task)
          dismiss()
        }
      }
    }
  }
}