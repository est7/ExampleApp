package com.application.example.squareavatarview

/**
 *
 * @author: est7
 * @date: 2022/12/28
 */
enum class Direction {
    NONE,
    LEFT,
    TOP,
    RIGHT,
    BOTTOM;

    companion object {

        fun get(ordinal: Int): Direction = values().find { it.ordinal == ordinal } ?: NONE
    }
}