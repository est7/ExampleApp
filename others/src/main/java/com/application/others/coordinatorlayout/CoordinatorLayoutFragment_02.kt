package com.application.others.coordinatorlayout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.application.others.R
import com.application.others.databinding.ActivityCoordinatorLayoutBinding
import com.application.others.databinding.FragmentCoordinatorLayout02Binding
import com.example.base.binding


class CoordinatorLayoutFragment_02 :Fragment() {
    private val binding by binding<FragmentCoordinatorLayout02Binding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var contentView = super.onCreateView(inflater, container, savedInstanceState)
        if (contentView == null) {
            contentView = binding.root
        }
        return contentView
    }

}