package com.application.others.coordinatorlayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.application.others.R
import com.application.others.databinding.ActivityCoordinatorLayoutBinding
import com.example.base.binding


/**
 * https://github.com/imurluck/HeaderLayout 一个可以自定义头部的CoordinatorLayout
 */
class CoordinatorLayoutActivity : AppCompatActivity() {
    private val binding by binding<ActivityCoordinatorLayoutBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}