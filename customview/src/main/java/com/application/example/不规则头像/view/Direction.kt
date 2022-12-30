package com.application.example.不规则头像.view

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