package com.template.stateflow_sharedflow_channel.FlowTest

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *
 * @author: est7
 * @date: 2023/1/5
 */
// 注意，该函数不会挂起，而且不需要任何的 CoroutineScope
fun usersFlow(): Flow<String> = flow {
    repeat(3) {

        delay(1000)
        val ctx = currentCoroutineContext()
        val name = ctx[CoroutineName]?.name
        emit("User$it in $name")
    }
}

suspend fun main1() {
    withContext(CoroutineName("Name")) {
        val job = launch {
            // collect 是挂起的
            usersFlow().collect { println(it) }
        }
        launch {
            delay(2100)
            println("I got enough")
            job.cancel()
        }
    }
}




















