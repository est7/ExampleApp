package com.application.others.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.others.R
import com.application.others.bean.betterRank
import com.application.others.databinding.ActivitySimpleRecycleViewBinding

class SimpleRecycleViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySimpleRecycleViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setContentView(R.layout.activity_simple_recycle_view)
        val llManager = LinearLayoutManager(this)
        //默认垂直展示，这里设置为水平显示
        llManager.orientation = LinearLayoutManager.HORIZONTAL
        //设置 RecyclerView 的布局管理器
        binding.rvSampleRecyclerview.setLayoutManager(llManager)

        val banklist = betterRank.ranks


        //设置 RecyclerView 的数据适配器。
        binding.rvSampleRecyclerview.setAdapter(adapter)
        //addItemDecoration
        binding.rvSampleRecyclerview.addItemDecoration(RecyclerView.ItemDecoration(this, 10))

    }

}

class CustomAdapter(private val dataSet: Array<String>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView

        init {
            // Define click listener for the ViewHolder's View.
            textView = view.findViewById(R.id.textView)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}


