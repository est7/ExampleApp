package com.application.others.brvah

import android.os.Bundle
import android.os.PersistableBundle
import com.application.others.databinding.ActivityLoadMoreBinding

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