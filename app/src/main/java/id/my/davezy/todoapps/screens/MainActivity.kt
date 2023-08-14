package id.my.davezy.todoapps.screens

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.my.davezy.todoapps.databinding.ActivityMainBinding
import id.my.davezy.todoapps.dialog.CreateTaskDialog
import id.my.davezy.todoapps.domain.models.ChecklistModel
import id.my.davezy.todoapps.domain.utils.DataState
import id.my.davezy.todoapps.domain.utils.DataState.Loading.error
import id.my.davezy.todoapps.domain.utils.DataState.Loading.success
import id.my.davezy.todoapps.screens.adapter.TaskListAdapter
import id.my.davezy.todoapps.utils.showSnackBar
import id.my.davezy.todoapps.utils.toStringFormatted

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  private val viewModel: MainViewModel by viewModels()
  private val taskListAdapter = TaskListAdapter()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setupSubscribers()
    setupButtonListener()
    viewModel.getTasksList()
    binding.rvTaskList.adapter = taskListAdapter
  }

  private fun setupSubscribers() {
    viewModel.taskListState.collect(this) { state ->
      when (state) {
        is DataState.Loading -> showLoading()
        is DataState.Error -> state.error { showErrorMessage(it.message) }
        is DataState.Success -> state.success { showSuccessView(it) }
      }
    }
    viewModel.createTaskState.collect(this) { state ->
      when (state) {
        is DataState.Loading -> showLoading()
        is DataState.Error -> state.error { showErrorMessage(it.message) }
        is DataState.Success -> state.success {
          viewModel.getTasksList()
          showSuccessMessage("Task Added")
        }
      }
    }
  }

  private fun setupButtonListener() {
    binding.fabAddTask.setOnClickListener {
      val dialog = CreateTaskDialog(this, onCreateTaskDone())
      dialog.show(supportFragmentManager, CreateTaskDialog::class.simpleName)
    }
  }

  private fun onCreateTaskDone() = CreateTaskDialog.OnDateTimeSetListener { taskName, dueDate ->
    viewModel.createTask(taskName, dueDate.toStringFormatted())
  }

  private fun showSuccessView(data: List<ChecklistModel>?) {
    if (data.isNullOrEmpty()) {
      showEmptyView()
      return
    }
    taskListAdapter.submitList(data)
    binding.animator.displayedChild = 0
  }

  private fun showEmptyView() {
    binding.animator.displayedChild = 1
  }

  private fun showErrorMessage(message: String?) {
    binding.root.showSnackBar(
      message = "Error: $message",
      anchorView = binding.fabAddTask
    )
  }

  private fun showSuccessMessage(message: String) {
    binding.root.showSnackBar(
      message = message,
      anchorView = binding.fabAddTask
    )
  }

  private fun showLoading() {
    binding.animator.displayedChild = 3
  }
}