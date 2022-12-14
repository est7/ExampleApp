package com.application.others.tablayoutext

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.application.others.R

/**
 *
 * @CreateDate:     2020/7/28 11:34
 * @Description:    java类作用描述
 * @Author:         LOPER7
 * @Email:          loper7@163.com
 */
class SimpleFragment : Fragment() {
    private var mRootView: View? = null

    companion object {
        fun newInstance(text: String): SimpleFragment {
            val args = Bundle()
            args.putString("text", text)
            val fragment = SimpleFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        if (mRootView == null) {
            mRootView = View.inflate(context, R.layout.fragment_simple, null)
        }
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mRootView?.findViewById<TextView>(R.id.tv_text)?.text = requireArguments().getString("text")
    }
}