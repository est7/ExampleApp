@file:Suppress("unused")

package com.xiaoma.flow.func.ktx

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_CLOSE
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN

typealias Owner = FragmentManagerOwner

fun Owner.contains(fragment: Fragment) = fm.fragments.contains(fragment)

fun Owner.find(tag: String) = fm.findFragmentByTag(tag)

fun Owner.find(id: Int) = fm.findFragmentById(id)

fun Owner.add(
    container: Int,
    tag: String,
    fragmentClass: Class<out Fragment>,
    args: Bundle = bundleOf(),
) {
    val fragment = find(tag)
    if (fragment != null) return

    val instance = fragmentClass.newInstance()
    instance.arguments = args

    fm.beginTransaction()
        .add(container, instance, tag)
        .hide(instance)
        .commitNow()
}

fun Owner.remove(fragment: Fragment) {
    if (contains(fragment)) {
        fm.beginTransaction()
            .remove(fragment)
            .commitNow()
    }
}

fun Owner.show(tag: String) {
    val fragment = find(tag) ?: return
    show(fragment)
}

fun Owner.show(fragment: Fragment) {
    if (contains(fragment)) {
        fm.beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_OPEN)
            .show(fragment)
            .commitNow()
    }
}

fun Owner.hide(tag: String) {
    val fragment = find(tag) ?: return
    hide(fragment)
}

fun Owner.hide(fragment: Fragment) {
    if (contains(fragment)) {
        fm.beginTransaction()
            .setTransition(TRANSIT_FRAGMENT_CLOSE)
            .hide(fragment)
            .commitNow()
    }
}

interface FragmentManagerOwner {
    val fm
        get() = when (this) {
            is Fragment -> childFragmentManager
            is FragmentActivity -> supportFragmentManager
            else -> throw IllegalStateException()
        }
}

