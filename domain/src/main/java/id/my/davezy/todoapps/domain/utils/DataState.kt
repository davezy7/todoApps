package id.my.davezy.todoapps.domain.utils

sealed class DataState<out R> {
  data class Success<out T>(val data: T): DataState<T>()
  data class Error(val throwable: Throwable): DataState<Nothing>()
  object Loading : DataState<Nothing>()

  val DataState<*>?.isSucceeded get() = this != null && this is Success && data != null

  val DataState<*>?.isError get() = this != null && this is Error

  val DataState<*>?.isLoading get() = this != null && this is Loading

  inline infix fun <T> DataState<T>.success(predicate: (data: T) -> Unit): DataState<T> {
    if (this is Success && this.data != null) {
      predicate.invoke(this.data)
    }
    return this
  }

  inline infix fun <T> DataState<T>.error(predicate: (data: Throwable) -> Unit) {
    if (this is Error) {
      predicate.invoke(this.throwable)
    }
  }
}