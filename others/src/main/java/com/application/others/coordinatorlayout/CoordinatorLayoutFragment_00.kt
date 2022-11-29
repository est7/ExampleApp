package com.application.others.coordinatorlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.application.others.R
import com.application.others.databinding.FragmentCoordinatorLayout00Binding
import com.application.others.databinding.FragmentCoordinatorLayout01Binding
import com.example.base.binding


class CoordinatorLayoutFragment_00 : Fragment() {
    private val binding by binding<FragmentCoordinatorLayout00Binding>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        var contentView = super.onCreateView(inflater, container, savedInstanceState)
        if (contentView == null) {
            contentView = binding.root
        }
        return contentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.btn2.setOnClickListener { findNavController().navigate(R.id.action_blankFragment0_to_blankFragment1) }
        binding.btn3.setOnClickListener { findNavController().navigate(R.id.action_blankFragment0_to_blankFragment2) }
        binding.btn4.setOnClickListener { findNavController().navigate(R.id.action_blankFragment0_to_blankFragment3) }
        binding.btn1.setOnClickListener { findNavController().navigate(R.id.action_blankFragment0_to_blankFragment4) }
    }

}