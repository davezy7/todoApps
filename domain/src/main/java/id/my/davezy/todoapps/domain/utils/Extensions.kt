package id.my.davezy.todoapps.domain.utils

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <T> sendFlow(
  dispatcher: CoroutineDispatcher,
  crossinline action: suspend () -> T
): Flow<DataState<T>> = flow {
  emit(DataState.Loading)
  try {
    emit(DataState.Success(action.invoke()))
  } catch (t: Throwable) {
    emit(DataState.Error(t))
  }
}.flowOn(dispatcher)