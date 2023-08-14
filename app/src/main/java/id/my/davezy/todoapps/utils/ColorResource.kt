package id.my.davezy.todoapps.utils

import android.content.Context
import android.util.TypedValue

object ColorResource {

  fun Context.getPrimaryColor(): Int {
    val typedValue = TypedValue()
    this.theme.resolveAttribute(
      com.google.android.material.R.attr.colorPrimary,
      typedValue,
      true
    )
    return typedValue.data
  }
}