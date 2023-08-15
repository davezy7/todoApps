package id.my.davezy.todoapps.dialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.icu.util.Calendar
import android.os.Bundle
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.core.widget.doAfterTextChanged
import id.my.davezy.todoapps.databinding.DialogCreateTaskBinding
import id.my.davezy.todoapps.utils.showSnackBar
import id.my.davezy.todoapps.utils.toStringFormatted
import java.util.Date

class CreateTaskDialog(
  private val context: Context,
  private val listener: OnDateTimeSetListener
) : BaseDialog(),
  DatePickerDialog.OnDateSetListener,
  TimePickerDialog.OnTimeSetListener {

  fun interface OnDateTimeSetListener {
    fun onButtonClicked(taskName: String, dueDate: Date)
  }

  private lateinit var binding: DialogCreateTaskBinding
  private lateinit var calendar: Calendar
  private var taskName: String? = null

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    calendar = Calendar.getInstance()
    binding = DialogCreateTaskBinding.inflate(inflater)
    return binding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding.apply {
      textDueDate.setOnClickListener { showDatePickerDialog() }
      editTextTaskName.doAfterTextChanged {
        if (it.toString().isNotBlank()) taskName = it.toString()
      }
      btnDone.setOnClickListener { onButtonDoneClicked() }
    }
  }

  override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
    calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
    calendar[Calendar.MONTH] = month
    calendar[Calendar.YEAR] = year
    showTimePickerDialog()
  }

  override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
    calendar[Calendar.HOUR] = hour
    calendar[Calendar.MINUTE] = minute
    binding.textDueDate.text = calendar.time.toStringFormatted()
  }

  private fun onButtonDoneClicked() {
    binding.apply {
      if (taskName.isNullOrBlank()) {
        root.showSnackBar("Task name cannot be empty!")
        return
      }
      val currentDateTime = Calendar.getInstance().time
      if (calendar.time == currentDateTime ||
        calendar.time.before(currentDateTime)
      ) {
        root.showSnackBar("Invalid date and time")
        return
      }
      taskName?.let { listener.onButtonClicked(it, calendar.time) }
      dismiss()
    }
  }

  private fun showDatePickerDialog() {
    val day = calendar[Calendar.DAY_OF_MONTH]
    val month = calendar[Calendar.MONTH]
    val year = calendar[Calendar.YEAR]
    val datePickerDialog = DatePickerDialog(context, this, year, month, day)
    datePickerDialog.datePicker.minDate = Calendar.getInstance().timeInMillis
    if (!datePickerDialog.isShowing) datePickerDialog.show()
  }

  private fun showTimePickerDialog() {
    val hour = calendar[Calendar.HOUR]
    val minute = calendar[Calendar.MINUTE]
    val timePickerDialog = TimePickerDialog(
      context, this, hour, minute,
      DateFormat.is24HourFormat(context)
    )
    if (!timePickerDialog.isShowing) timePickerDialog.show()
  }
}