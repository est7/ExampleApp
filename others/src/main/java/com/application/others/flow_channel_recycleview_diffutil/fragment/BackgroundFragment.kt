package com.application.others.flow_channel_recycleview_diffutil.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.application.others.databinding.FragmentEmptyBinding
import com.example.base.BindingFragment
import com.example.utils.ktx.logW
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger

class BackgroundFragment : BindingFragment<FragmentEmptyBinding>() {

    companion object {
        private val TAG = "${BackgroundFragment::class.java.name}Tag"
    }

    private val vm by viewModels<Vm>()

    override fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEmptyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            vm.loadData()

            // vm.dataFlow.collect { data ->
            //     "$data received".logW(TAG, trace = false)
            // }

            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                vm.dataFlow.collect { data ->
                    "$data received".logW(TAG, trace = false)
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()
        "onResume".logW(TAG, trace = false)
    }

    override fun onPause() {
        super.onPause()
        "onPause".logW(TAG, trace = false)
    }

    class Vm : ViewModel() {
        private val counter = AtomicInteger(0)
        private val _dataFlow = MutableStateFlow("")
        val dataFlow: StateFlow<String> = _dataFlow


        fun loadData() {
            viewModelScope.launch {
                while (true) {
                    delay(1000)
                    val data = "DATA ${counter.getAndIncrement()}"
                    _dataFlow.value = data
                }
            }
        }
    }
}