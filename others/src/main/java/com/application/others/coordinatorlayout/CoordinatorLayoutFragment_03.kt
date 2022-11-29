package com.application.others.coordinatorlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.application.others.databinding.FragmentCoordinatorLayout01Binding
import com.example.base.binding
import android.R

import android.widget.TextView
import com.application.others.databinding.FragmentCoordinatorLayout03Binding
import com.google.android.material.tabs.TabLayout


class CoordinatorLayoutFragment_03 : Fragment() {
    private val binding by binding<FragmentCoordinatorLayout03Binding>()

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

        for (i in 0..50) {
            binding.tv.append(
                """
            ${i.toString()  + "\n"}
            """.trimIndent()
            )
        }
    }
}