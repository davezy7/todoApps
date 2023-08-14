package id.my.davezy.todoapps.data.utils

fun String?.replaceIfNull(replacementValue: String = "") : String {
  return this ?: replacementValue
}

fun Int?.replaceIfNull(replacementValue: Int = 0): Int {
  return this ?: replacementValue
}