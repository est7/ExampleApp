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


class CoordinatorLayoutFragment_04 : Fragment() {
    private val binding by binding<FragmentCoordinatorLayout01Binding>()

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

        for (i in 0..49) {
            binding.tv.append(
                """
            ${i.toString()  + "\n"}
            """.trimIndent()
            )
        }
    }
}