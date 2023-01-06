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
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StateFlowFragment : BindingFragment<FragmentEmptyBinding>() {

    companion object {
        private val TAG = "${StateFlowFragment::class.java.name}Tag"
    }

    override fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEmptyBinding.inflate(inflater, container, false)

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            val flow = flow {
                repeat(3) { pi ->
                    val data = "DATA$pi"
                    "$data produced".logD(TAG, trace = false)
                    emit(data)
                }
            }.stateIn(this)

            delay(100)

            launch {
                flow.collect { data ->
                    "$data received".logW(TAG, trace = false)
                }
            }

        }

    }

}