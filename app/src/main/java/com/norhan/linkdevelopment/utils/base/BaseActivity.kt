package com.norhan.linkdevelopment.utils.base

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.android.material.snackbar.Snackbar
import com.norhan.linkdevelopment.R
import com.norhan.linkdevelopment.di.Injector
import com.norhan.linkdevelopment.di.component.ApplicationComponent
import com.norhan.linkdevelopment.utils.locale.LocaleHelper
import com.tbruyelle.rxpermissions2.RxPermissions
import javax.inject.Inject


abstract class BaseActivity<P : BaseIPresenter> : AppCompatActivity(), BaseIView {

    @Inject
    lateinit var presenter: P

    private lateinit var snackbar: com.google.android.material.snackbar.Snackbar
    private var rxPermissions: RxPermissions? = null
    private var pd: Dialog? = null

    @get:LayoutRes
    protected abstract val contentView: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        resolveDaggerDependency(Injector.INSTANCE.appComponent)
        setContentView(contentView)
        rxPermissions = RxPermissions(this)
        createCustomProgressDialog()
        snackbar = Snackbar.make(
            findViewById(android.R.id.content),
            "",
            com.google.android.material.snackbar.Snackbar.LENGTH_LONG
        )
        onViewReady(savedInstanceState, intent)
        if (::presenter.isInitialized)
            presenter.onViewReady()
    }

    override fun showMessage(s: String) {
        try {
            snackbar.setText(s)
            val snackBarView = snackbar.view
            snackBarView.setBackgroundColor(Color.parseColor("#ffed5565"))
            val textView = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setBackgroundColor(Color.parseColor("#ffed5565"))
            textView.setTextColor(Color.WHITE)
            textView.gravity = Gravity.CENTER
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            if (!snackbar.isShown)
                snackbar.show()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        }
    }

    override fun showMessageInGreen(s: String) {
        try {
            snackbar.setText(s)
            val snackBarView = snackbar.view
            snackBarView.setBackgroundColor(Color.parseColor("#54d582"))
            val textView = snackBarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setBackgroundColor(Color.parseColor("#54d582"))
            textView.setTextColor(Color.WHITE)
            textView.gravity = Gravity.CENTER
            textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
            if (!snackbar.isShown)
                snackbar.show()
        } catch (e: IllegalStateException) {
        }
    }

    override fun showErrorMsg(msg: Int) {
        showMessage(getString(msg))
    }

    override fun showErrorMsg(msg: String) {
        showMessage(msg)
    }

    private fun createCustomProgressDialog() {
        pd = Dialog(this, R.style.DialogCustomTheme)
        pd!!.setContentView(R.layout.layout_progress_dialog)
        pd!!.setCancelable(false)
    }

    private fun showDialog() {
        if (pd != null) {
            if (pd!!.isShowing) {
                pd!!.dismiss()
            }
            pd!!.show()
        }
    }

    private fun hideDialog() {
        if (pd != null && pd!!.isShowing)
            pd!!.dismiss()
    }

    override fun showLoading() {
        showDialog()
    }

    override fun hideLoading() {
        hideDialog()
    }

    override fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    abstract fun onViewReady(savedInstanceState: Bundle?, intent: Intent)


    protected abstract fun resolveDaggerDependency(appComponent: ApplicationComponent?)

    override fun getRxPermissions(): RxPermissions {
        return rxPermissions!!
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

    override fun onResume() {
        super.onResume()
        if (::presenter.isInitialized) {
            presenter.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (::presenter.isInitialized) {
            presenter.onPause()
        }
    }

    override fun onStart() {
        super.onStart()
        if (::presenter.isInitialized) {
            presenter.onStart()
        }
    }

    override fun onStop() {
        super.onStop()
        if (::presenter.isInitialized) {
            presenter.onStop()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::presenter.isInitialized)
            presenter.onDestroy()
    }

}