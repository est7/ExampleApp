package com.template.stateflow_sharedflow_channel.SharedStateFlow

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 *
 * @author: est7
 * @date: 2023/1/5
 */

suspend fun main(): Unit = coroutineScope {
    val channel = Channel<Int>()
    launch {
        repeat(5) { index ->
            delay(1000)
            println("Producing next one")
            channel.send(index * 2)
        }
    }
    launch {
        repeat(5) {
            val received = channel.receive()
            println(received)
        }
    }
}


suspend fun main1(): Unit = coroutineScope {
    val channel = Channel<Int>()
    launch {
        repeat(5) { index ->
            println("Producing next one")
            delay(1000)
            channel.send(index * 2)
        }
        channel.close()
    }
    launch {
        for (element in channel) {
            println(element)
        }
        // 或者
        // channel.consumeEach { element ->
        //    println(element)
        // }
    }
}
