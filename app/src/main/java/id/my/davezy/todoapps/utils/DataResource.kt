package id.my.davezy.todoapps.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DataResource<T>(data: T) {

  private val mutableStateFlow: MutableStateFlow<T> = MutableStateFlow(data)
  private val stateFlow: StateFlow<T> = mutableStateFlow.asStateFlow()

  val value = stateFlow.value

  fun setValue(value: T) {
    mutableStateFlow.value = value
  }

  fun collect(
    lifecycleOwner: LifecycleOwner,
    minState: Lifecycle.State = Lifecycle.State.CREATED,
    action: (data: T) -> Unit
  ) {
    lifecycleOwner.lifecycleScope.launch {
      stateFlow
        .flowWithLifecycle(lifecycleOwner.lifecycle, minState)
        .collectLatest { action.invoke(it) }
    }
  }

}