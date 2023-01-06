package com.template.kotlinsamples_sup_cor_scope

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 *
 * @author: est7
 * @date: 2023/1/6
 */
val handler = CoroutineExceptionHandler { _, exception ->
    println("CoroutineExceptionHandler got $exception")
}

private suspend fun main() {
    val launch = GlobalScope.launch(handler) {
        launch {
            delay(2000L)
            println("CancelJobActivity job1 finished")
            throw Error()
        }
        launch {
            delay(3000L)
            println("CancelJobActivity job2 finished")
        }
    }
    launch.join()

}


private suspend fun main1() {
    supervisorScope {
        launch innerlaunch@{
            delay(2000L)
            println("CancelJobActivity job1 finished")
            throw Error()
        }
        launch {
            delay(3000L)
            println("CancelJobActivity job2 finished")
        }
    }
}


suspend fun main2() {
    coroutineScope {
        launch innerlaunch@{
            delay(2000L)
            println("CancelJobActivity job1 finished")
            throw Error()
        }
        launch {
            delay(3000L)
            println("CancelJobActivity job2 finished")
        }
    }
}
