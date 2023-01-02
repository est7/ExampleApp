package com.application.others

import android.content.Context
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder

class MainAdapter(list: List<Pair<String, Class<out AppCompatActivity>>>) :
    BaseQuickAdapter<Pair<String, Class<out AppCompatActivity>>, QuickViewHolder>(list) {

    override fun onBindViewHolder(
        holder: QuickViewHolder,
        position: Int,
        item: Pair<String, Class<out AppCompatActivity>>?
    ) {
        val button = holder.getView<Button>(R.id.button)
        button.text = item?.first
    }


    override fun onCreateViewHolder(
        context: Context,
        parent: ViewGroup,
        viewType: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.home_item, parent)
    }
}