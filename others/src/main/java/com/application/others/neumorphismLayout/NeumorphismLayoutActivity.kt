package com.application.others.neumorphismLayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.application.others.R
import com.application.others.databinding.ActivityCoordinatorLayoutBinding
import com.application.others.databinding.ActivityNeumorphismLayoutBinding
import com.example.base.binding


class NeumorphismLayoutActivity : AppCompatActivity() {
    private val binding by binding<ActivityNeumorphismLayoutBinding>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

}