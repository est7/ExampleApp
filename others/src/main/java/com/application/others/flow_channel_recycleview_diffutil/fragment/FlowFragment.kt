package com.application.others.flow_channel_recycleview_diffutil.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.application.others.databinding.FragmentEmptyBinding
import com.example.base.BindingFragment
import com.example.utils.ktx.logD
import com.example.utils.ktx.logW
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.onClosed
import kotlinx.coroutines.channels.onFailure
import kotlinx.coroutines.channels.onSuccess
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread
import kotlin.reflect.jvm.jvmName

class FlowFragment : BindingFragment<FragmentEmptyBinding>() {

    companion object {
        private const val TAG = "FlowFragmentTag"
    }

    override fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEmptyBinding.inflate(inflater, container, false)


    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            val flow = callbackFlow {
                val manager = LocationManager()

                manager.setOnLocationChangedListener { data ->
                    val result = trySend(data)
                    result.onSuccess {
                        "$data onSuccess".logD(TAG, trace = false)
                    }.onFailure {
                        "$data onFailure".logW(TAG, trace = false)
                    }.onClosed {
                        "$data onClosed".logW(TAG, trace = false)
                    }
                }

                awaitClose {
                    "collector is closed".logW(TAG, trace = false)
                    manager.setOnLocationChangedListener(null)
                }
            }

            launch {
                var count = AtomicInteger(0)
                flow.collect { data ->
                    "$data consumed".logW(TAG, trace = false)

                    val times = count.getAndIncrement()
                    if (times == 2) cancel()
                }
            }

        }
    }

    class LocationManager {
        private var callback: ((String) -> Unit)? = null

        init {
            thread {
                var times = 0
                while (true) {
                    val data = "LOCATION:${times++}"
                    Thread.sleep(500)
                    callback?.invoke(data)
                    if (times > 10) break
                }
            }

        }

        fun setOnLocationChangedListener(callback: ((String) -> Unit)?) {
            this.callback = callback
        }
    }
}