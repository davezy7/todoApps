package id.my.davezy.todoapps.utils

import android.view.View
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Date.toStringFormatted(format: String = "dd MMM yyyy - HH:mm"): String {
  val sdf = SimpleDateFormat(format, Locale.getDefault())
  return sdf.format(this)
}

fun View.showSnackBar(
  message: String,
  anchorView: View? = null,
  animation: Int = Snackbar.ANIMATION_MODE_SLIDE
) {
  Snackbar.make(this, message, Snackbar.LENGTH_SHORT)
    .setAnchorView(anchorView)
    .setAnimationMode(animation)
    .show()
}