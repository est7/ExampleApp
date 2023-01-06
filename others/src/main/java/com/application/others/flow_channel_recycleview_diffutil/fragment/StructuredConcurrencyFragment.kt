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
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StructuredConcurrencyFragment : BindingFragment<FragmentEmptyBinding>() {

    companion object {
        private val TAG = "${StructuredConcurrencyFragment::class.java.name}Tag"
    }

    override fun onCreateViewBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentEmptyBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {

            val outerJob = launch {
                val start = System.currentTimeMillis()

                val scope = MainScope()
                repeat(3) { ci ->
                    val innerJob = scope.launch {
                        delay(1000)
                    }

                    innerJob.invokeOnCompletion {
                        val end = System.currentTimeMillis()
                        "inner$ci job is completed, spend time: ${end - start}"
                            .logD(TAG, trace = false)
                    }
                }
            }

            delay(500)
            "outer job is canceled".logW(TAG, trace = false)
            outerJob.cancel()

        }

    }

}