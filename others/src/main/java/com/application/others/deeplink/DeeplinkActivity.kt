package com.application.others.deeplink

import android.content.ComponentName
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.application.others.databinding.ActivityDeeplinkBinding

class DeeplinkActivity : AppCompatActivity() {
    private val binding by lazy { ActivityDeeplinkBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 直接在应用里跳转
        binding.btnDirect.setOnClickListener {
            Intent().apply {
                component = ComponentName(
                    packageName,
                    DirectActivity::class.java.name
                )
                startActivity(this)
            }
        }

        // 如果为H5页面
        binding.btnWeb.setOnClickListener {
            Intent().apply {
                component = ComponentName(
                    packageName,
                    WebLinkActivity::class.java.name
                )
                startActivity(this)
            }
        }
    }
}