package com.anubhav.newsapp.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.anubhav.newsapp.R
import com.anubhav.newsapp.databinding.ActivityBaseBinding

abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {
    lateinit var baseBinding: ActivityBaseBinding;
    lateinit var binding: T

    @get:LayoutRes
    protected abstract val layoutResourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseBinding = DataBindingUtil.setContentView(this, R.layout.activity_base)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        setSupportActionBar(baseBinding.toolbar)
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, baseBinding.layoutContainer, true)
        baseBinding.imageViewBack.setOnClickListener({ onBackPressed() })
        baseBinding.includedErrorLayout.textViewErrorRefresh.setOnClickListener({ errorRefreshClicked() })
    }

    private fun errorRefreshClicked() {}

    fun showToast(msg: String) {
        if (TextUtils.isEmpty(msg)) {
            return
        }
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show()
    }

    fun showProgress() {
        baseBinding.progressBar.setVisibility(View.VISIBLE)
    }

    fun showOverlayProgress() {
        baseBinding.overlayProgressContainer.setVisibility(View.VISIBLE)
    }

    fun hideOverlayProgress() {
        baseBinding.overlayProgressContainer.setVisibility(View.GONE)
    }

    fun hideProgress() {
        baseBinding.progressBar.setVisibility(View.GONE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showData() {
        baseBinding.layoutContainer.setVisibility(View.VISIBLE)
    }

    fun showError() {
        baseBinding.layoutError.setVisibility(View.VISIBLE)
    }

    fun hideError() {
        baseBinding.layoutError.setVisibility(View.GONE)
    }

    fun hideBack() {
        baseBinding.imageViewBack.setVisibility(View.GONE)
    }

    fun getData() {
        baseBinding.layoutContainer.setVisibility(View.VISIBLE)
    }

    fun setToolbarText(toolbarText: String) {
        baseBinding.textViewtToolbarTitle.setText(toolbarText)
    }

    fun setCustomToolbarText(toolbarText: String, size: Float) {
        baseBinding.textViewtToolbarTitle.setText(toolbarText)
        baseBinding.textViewtToolbarTitle.setTextSize(size)
    }

    fun setToolbarVisibility(isVisible: Boolean) {
        if (!isVisible)
            baseBinding.toolbar.setVisibility(View.GONE)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}