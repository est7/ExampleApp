package com.application.others.flow_channel_recycleview_diffutil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.application.others.R
import com.application.others.databinding.FragmentFlowChannelMainBinding
import com.application.others.databinding.FragmentFlowChannelMainItemBinding
import com.example.base.BindingFragment
import com.example.base.DiffProcess
import com.example.utils.ktx.setOnCanClickListener

class MainFragment : BindingFragment<FragmentFlowChannelMainBinding>() {
    private val adapter by lazy { Adapter() }
    private val diffProcess = DiffProcess(Item::text)
    private val navi by lazy { findNavController() }

    private val itemList = listOf(
        Item("Channel") { navi.navigate(R.id.toChannel) },
        Item("Flow") { navi.navigate(R.id.toFlow) },
        Item("SharedFlow") { navi.navigate(R.id.toSharedFlow) },
        Item("StateFlow") { navi.navigate(R.id.toStateFlow) },
        Item("StructuredConcurrency") { navi.navigate(R.id.toStructuredConcurrency) },
        Item("Background") { navi.navigate(R.id.toBackgroundFragment) }
    )

    override fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentFlowChannelMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvList.adapter = adapter
        adapter.setData(itemList)
    }

    inner class Adapter : RecyclerView.Adapter<Holder>() {
        private val dataList = mutableListOf<Item>()

        fun setData(newDataList: List<Item>) {
            diffProcess.calculateDiff(
                dataList,
                newDataList,
                false
            ).dispatchUpdatesTo(this)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_flow_channel_main_item, parent, false)
            return Holder(itemView)
        }

        override fun onBindViewHolder(holder: Holder, position: Int, payloads: MutableList<Any>) {
            super.onBindViewHolder(holder, position, payloads)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            val item = dataList[position]
            holder.refresh(item)
        }

        override fun getItemCount() = dataList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = FragmentFlowChannelMainItemBinding.bind(itemView)

        fun refresh(item: Item) {
            binding.btn.text = item.text
            binding.btn.setOnCanClickListener(listener = item.action)
        }
    }

    data class Item(
        val text: String,
        val action: (View) -> Unit
    )

}