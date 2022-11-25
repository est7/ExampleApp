package com.application.others.recyclerview

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.others.R
import com.application.others.bean.Rank
import com.application.others.bean.betterRank
import com.application.others.databinding.ActivitySimpleRecycleViewBinding
import com.application.others.recyclerview.ItemDecoration.GridDividerItemDecoration
import com.bumptech.glide.Glide
import com.example.base.binding
import com.google.android.material.imageview.ShapeableImageView

class SimpleRecycleViewActivity : AppCompatActivity() {
    private val binding by binding<ActivitySimpleRecycleViewBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val llManager = LinearLayoutManager(this)
        //默认垂直展示，这里设置为水平显示
        llManager.orientation = LinearLayoutManager.VERTICAL
        //设置 RecyclerView 的布局管理器
        binding.rvSampleRecyclerview.setLayoutManager(llManager)

        val banklist = betterRank.ranks

        val customAdapter = CustomAdapter(this,banklist)
        //设置 RecyclerView 的数据适配器。
        binding.rvSampleRecyclerview.setAdapter(customAdapter)
        //addItemDecoration
        binding.rvSampleRecyclerview.addItemDecoration(GridDividerItemDecoration())
    }

}

class CustomAdapter(private val context: Context, private val dataSet: List<Rank>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.text_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textView.text = dataSet[position].name
        Glide.with(context).load(dataSet[position].avatarUrl)
            .into(viewHolder.imageView)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView by lazy { view.findViewById(R.id.text_row_item_tv) }
        val imageView: ShapeableImageView by lazy { view.findViewById(R.id.text_row_item_iv) }

        init {
            // Define click listener for the ViewHolder's View.
        }

    }

}
