package id.my.davezy.todoapps.dialogs

import android.app.Dialog
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import id.my.davezy.todoapps.R

open class BaseDialog : DialogFragment() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setStyle(STYLE_NORMAL, R.style.BaseDialog)
  }

  override fun onStart() {
    super.onStart()
    val dialog: Dialog? = dialog
    if (dialog != null) {
      val width = ViewGroup.LayoutParams.MATCH_PARENT
      val height = ViewGroup.LayoutParams.WRAP_CONTENT
      dialog.window?.setLayout(width, height)
    }
  }

  override fun show(fm: FragmentManager, tag: String?) {
    try {
      super.show(fm, tag)
    } catch (ex: Exception) {
      showAllowingStateLoss(fm, tag)
    }
  }

  private fun showAllowingStateLoss(fm: FragmentManager, tag: String?) {
    try {
      fm.beginTransaction()
        .add(this, tag)
        .commitAllowingStateLoss()
    } catch (ex: Exception) {
      ex.printStackTrace()
    }
  }
}