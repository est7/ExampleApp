package com.application.others.flow_channel_recycleview_diffutil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.application.others.databinding.FragmentEmptyBinding
import com.example.base.BindingFragment
import com.example.utils.ktx.logD
import com.example.utils.ktx.logE
import com.example.utils.ktx.logW
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

class SharedFlowFragment : BindingFragment<FragmentEmptyBinding>() {

    companion object {
        private val TAG = "SharedFlowFragmentTag"
    }

    override fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEmptyBinding.inflate(inflater, container, false)


    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val counter = AtomicInteger(0)
        fun produceData(): String {
            val times = counter.getAndIncrement()
            return "DATA$times"
        }

        lifecycleScope.launch {

            val flow = flow {
                "start produce".logD(TAG, trace = false)
                var data = produceData()
                "$data produced".logD(TAG, trace = false)
                emit(data)
            }.shareIn(
                scope = this,
                started = SharingStarted.WhileSubscribed(
                    stopTimeoutMillis = 0,
                    replayExpirationMillis = 150
                ),
                replay = 1
            )

            launch {
                "coroutine1 start collect".logE(TAG, trace = false)
                flow.collect {
                    "coroutine1 canceled".logE(TAG, trace = false)
                    cancel()
                }
            }

            delay(100)

            launch {
                "coroutine2 start collect".logW(TAG, trace = false)
                flow.collect { data ->
                    "$data received".logW(TAG, trace = false)
                }
            }

        }

    }

}