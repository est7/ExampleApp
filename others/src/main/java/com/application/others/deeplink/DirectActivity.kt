package com.application.others.deeplink

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.application.others.R
import com.application.others.databinding.ActivityDirectBinding
import com.application.others.databinding.ViewUrlItemBinding

class DirectActivity : AppCompatActivity() {

    private val binding by lazy { ActivityDirectBinding.inflate(layoutInflater) }
    private val vm by lazy { ViewModelProvider(this).get(Vm::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.rvList.adapter = Adapter()
    }

    inner class Adapter : RecyclerView.Adapter<Holder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val inflater = LayoutInflater.from(parent.context)
            val itemView = inflater.inflate(R.layout.view_url_item, parent, false)
            return Holder(itemView)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val name = vm.urlList[position].first
            val url = vm.urlList[position].second
            holder.refresh(name, url)
        }

        override fun getItemCount() = vm.urlList.size

    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ViewUrlItemBinding.bind(itemView)
        private var url: String? = null

        init {
            binding.root.setOnClickListener {
                val url = url ?: return@setOnClickListener

                Intent().apply {
                    data = Uri.parse(url)
                    // 请在新的任务栈打开
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    // 增加ClearTop标识避免内链失效
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(this)
                }

            }
        }

        fun refresh(name: String, url: String) {
            this.url = url

            binding.tvName.text = name
            binding.tvUrl.text = url
        }


    }

    class Vm : ViewModel() {
        val urlList = ItemProvider.itemList
    }

}