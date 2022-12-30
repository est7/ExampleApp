package com.application.others.brvah

import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.os.PersistableBundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.application.others.R
import com.application.others.databinding.ActivityLoadMoreBinding
import com.bumptech.glide.Glide.init
import com.chad.library.adapter.base.QuickAdapterHelper
import com.chad.library.adapter.base.loadState.LoadState
import com.chad.library.adapter.base.loadState.trailing.TrailingLoadStateAdapter

class BRVAHActivity :  BaseViewBindingActivity<ActivityLoadMoreBinding>() {
    override fun initBinding(): ActivityLoadMoreBinding {
        return ActivityLoadMoreBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initData()
    }

    private fun initData() {

    }
}