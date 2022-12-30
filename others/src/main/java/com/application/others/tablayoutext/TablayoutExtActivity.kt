package com.application.others.tablayoutext

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.application.others.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.loper7.tab_expand.ext.buildIndicator
import com.loper7.tab_expand.ext.buildText
import com.loper7.tab_expand.ext.toPx
import com.loper7.tab_expand.indicator.CustomIndicator
import com.loper7.tab_expand.text.BaseText

class TablayoutExtActivity : AppCompatActivity() {
    private lateinit var adapter: CustomFragmentAdapter
    private lateinit var viewpager: ViewPager2
    private lateinit var tablayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tablayout_ext)
        viewpager = findViewById<ViewPager2>(R.id.viewPager)
        tablayout = findViewById<TabLayout>(R.id.tabLayout)
    }

    override fun onStart() {
        super.onStart()
        adapter = CustomFragmentAdapter(this)
        viewpager.adapter = adapter

        TabLayoutMediator(tablayout, viewpager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
            viewpager.setCurrentItem(tab.position, true)
        }.attach()

        // custom1
        tablayout.buildIndicator<CustomIndicator>()
            .setDrawable(ContextCompat.getDrawable(this, R.mipmap.ic_indicator_index)!!)
            .setWidth(15.toPx())
            .setHeight(15.toPx())
            .bind()
        tablayout.buildText<BaseText>()
            .setNormalTextBold(false)
            .setSelectTextBold(true)
            .setNormalTextSize(10f)
            .setSelectTextSize(20f)
            .bind()
//        // custom2
//        tabLayout1.buildIndicator<CustomIndicator>()
//            .setDrawable(ContextCompat.getDrawable(context!!, R.mipmap.ic_indicator_t)!!)
//            .setWidth(BaseIndicator.MATCH)
//            .setHeight(BaseIndicator.MATCH)
//            .bind()
//        tabLayout1.buildText<BaseText>()
//            .setNormalTextBold(false)
//            .setSelectTextBold(true)
//            .setNormalTextSize(16f)
//            .setSelectTextSize(18f)
//            .bind()
//        // custom3
//        tabLayout2.buildIndicator<CustomIndicator>()
//            .setDrawable(ContextCompat.getDrawable(context!!, R.mipmap.ic_indicator_finger)!!)
//            .setWidth(15.toPx())
//            .setHeight(15.toPx())
//            .bind()
//        tabLayout2.buildText<BaseText>()
//            .setNormalTextBold(false)
//            .setSelectTextBold(true)
//            .setNormalTextSize(16f)
//            .setSelectTextSize(18f)
//            .bind()
//        // custom4
//        tabLayout3.buildIndicator<CustomIndicator>()
//            .setDrawable(ContextCompat.getDrawable(context!!, R.mipmap.ic_indicator_index)!!)
//            .setHeight(8.toPx())
//            .setWidth(50.toPx())
//            .bind()
//        tabLayout3.buildText<BaseText>()
//            .setNormalTextBold(false)
//            .setSelectTextBold(true)
//            .setNormalTextSize(16f)
//            .setSelectTextSize(18f)
//            .bind()
//        //custom5
//        tabLayout4.buildIndicator<CustomIndicator>()
//            .setDrawable(ColorDrawable(ContextCompat.getColor(context!!, R.color.colorAccent)))
//            .setHeight(BaseIndicator.MATCH)
//            .setWidth(BaseIndicator.MATCH)
//            .bind()
//        tabLayout4.buildText<BaseText>()
//            .setNormalTextBold(false)
//            .setSelectTextBold(true)
//            .setNormalTextSize(16f)
//            .setSelectTextSize(18f)
//            .bind()
    }
}