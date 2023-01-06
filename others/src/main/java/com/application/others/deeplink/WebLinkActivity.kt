package com.application.others.deeplink

import android.content.Intent
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.application.others.databinding.ActivityWebLinkBinding
import org.intellij.lang.annotations.Language

class WebLinkActivity : AppCompatActivity() {
    private val binding by lazy { ActivityWebLinkBinding.inflate(layoutInflater) }
    private val vm by lazy { ViewModelProvider(this).get(Vm::class.java) }

    private val wvClient = object : WebViewClient() {

        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            request?.url?.let { uri ->
                if (uri.scheme == "xiaoma") {
                    Intent().apply {
                        data = uri
                        // 请在新的任务栈打开
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        // 增加ClearTop标识避免内链失效
                        addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(this)
                    }
                    return true
                }
            }
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.wvContent.loadData(vm.html, "text/html;", "utf-8")
        binding.wvContent.webViewClient = wvClient
    }


    class Vm : ViewModel() {

        @Language("HTML")
        private val itemTemplate = "<li><a href=\"#href#\">#title#</a></li>"

        @Language("HTML")
        private val htmlTemplate =
            "<!DOCTYPE html>\n<html lang=\"zh-CN\">\n<head>\n    <meta charset=\"utf-8\">\n    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n    <title>CCS 5.0 Deeplink</title></head>\n<body>\n<ul>#replace#</ul>\n</body>\n</html>\n"


        val html: String
            get() {
                val replace = ItemProvider.itemList.joinToString("\n") { (title, href) ->
                    itemTemplate
                        .replace("#title#", title)
                        .replace("#href#", href)
                }
                return htmlTemplate.replace("#replace#", replace)
            }
    }

}