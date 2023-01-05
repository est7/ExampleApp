package com.template.stateflow_sharedflow_channel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.template.stateflow_sharedflow_channel.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lateInIt()
        button()
        observe()
    }

    private fun lateInIt() {
        viewmodel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    private fun button() {
        onLiveData()
        onStateFlow()
        onFlow()
        onSharedFlow()
        onChannel()
    }

    private fun observe() {
        binding.apply {
            viewmodel.liveData.observe(this@MainActivity) { data ->
                liveDataT.text = data
            }

            lifecycleScope.launchWhenStarted {
                viewmodel.stateFlow.collectLatest { data ->
                    stateFlowT.text = data
                    Snackbar.make(root, data, Snackbar.LENGTH_SHORT).show()
                }
            }

            lifecycleScope.launchWhenStarted {
                viewmodel.sharedFlow.collectLatest { data ->
                    sharedFlowT.text = data
                    Snackbar.make(root, data, Snackbar.LENGTH_SHORT).show()
                }
            }




            lifecycleScope.launchWhenStarted {
                viewmodel.channel.collectLatest {
                    binding.channelT.text =binding.channelT.text.toString() + it
                }
            }

        }
    }

    private fun onLiveData() {
        binding.liveDataB.setOnClickListener {
            viewmodel.updateLiveData()
        }
    }

    private fun onStateFlow() {
        binding.stateFlowB.setOnClickListener {
            viewmodel.updateStateFlow()
        }
    }

    private fun onFlow() {
        binding.apply {
            flowB.setOnClickListener {
                lifecycleScope.launchWhenStarted {
                    viewmodel.updateFlow().collectLatest { data ->
                        flowT.text = data
                    }
                }
            }
        }
    }

    private fun onSharedFlow() {
        binding.sharedFlowB.setOnClickListener {
            viewmodel.updateSharedFlow()
        }
    }



    private fun onChannel() {
        binding.channelB.setOnClickListener {
            viewmodel.updateChannel()
        }
    }

}