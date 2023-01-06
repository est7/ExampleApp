package com.application.others.flow_channel_recycleview_diffutil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.application.others.databinding.FragmentEmptyBinding
import com.example.base.BindingFragment
import com.example.utils.ktx.Level
import com.example.utils.ktx.log
import com.example.utils.ktx.logD
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ChannelFragment : BindingFragment<FragmentEmptyBinding>() {

    companion object {
        private val TAG = "ChannelFragmentTag"
    }

    override fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEmptyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            val channel = Channel<String>()

            launch {
                for (i in 1..2) {
                    delay(10) // 模拟高速生产
                    val data = "DATA$i"
                    "$data produced".logD(TAG, trace = false)
                    channel.send(data)
                    "$data sent".logD(TAG, trace = false)
                }
            }

            repeat(2) { ci ->
                val level = if (ci % 2 == 0) Level.Warn else Level.Error
                launch {
                    for (data in channel) {
                        "c$ci: $data received".log(TAG, trace = false, level = level)
                        delay(200) // 模拟缓慢消费
                        "c$ci: $data consumed".log(TAG, trace = false, level = level)
                    }
                }
            }

        }
    }

}