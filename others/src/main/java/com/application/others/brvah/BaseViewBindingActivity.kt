package com.application.others.brvah

/**
 *
 * @author: est7
 * @date: 2022/12/27
 */
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.application.others.R

abstract class BaseViewBindingActivity<V : ViewBinding> : AppCompatActivity() {

    private var _viewBinding: V? = null

    protected val viewBinding: V
        get() {
            return _viewBinding ?: throw IllegalStateException(
                "Should be called initBinding()"
            )
        }

    /**
     * 初始化 [viewBinding]
     */
    abstract fun initBinding(): V

    final val contentView: View
        get() {
            return initBinding().apply {
                _viewBinding = this
            }.root
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView)
    }

}