package com.example.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

@Suppress(
    "MemberVisibilityCanBePrivate",
    "unused"
)
abstract class BindingFragment<T : ViewBinding> : Fragment() {
    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = onCreateViewBinding(inflater, container).run { _binding = this; root }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract fun onCreateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T
}