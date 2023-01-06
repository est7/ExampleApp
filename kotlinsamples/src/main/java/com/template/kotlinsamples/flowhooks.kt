package com.template.kotlinsamples

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

class MyError : Throwable("My error")

/**
 *
 * @author: est7
 * @date: 2023/1/6
 */


val flow = flow {
    emit("Message1")
    emit("Message2")
//    throw MyError()
}

suspend fun main1() {
    flow.onStart { println("onStart") }
        .onCompletion { println("onCompletion") }
        .onEach {
            throw MyError()
            println("onEach1:$it") }
        .catch { println("Caught $it") }
        .onEach {
//            throw MyError()
            println("onEach2:$it")

        }
        .collect { println("collect") }
}


//使用 catch 并不能防止终端操作中出现异常（因为 catch 不能用最后一个操作之后）。
// 因此，如果 collect 中有一个异常，它将不会捕获，而是将抛出一个错误。
//通常的做法是将逻辑层从 collect 移动到 onEach，并将其放在 catch 之前。当我们怀疑 collect 的逻辑可能会出现异常时，
// 这样做会特别有用。如果我们将逻辑操作从 collect 中挪走，就可以确定 catch 能捕获所有的异常。
suspend fun main(): Unit {
    flow.onStart { println("Before") }
        .onEach {  }
        .catch { println("Caught $it") }
        .collect{}
}






suspend fun present(place: String, message: String) {
    val ctx = coroutineContext
    val name = ctx[CoroutineName]?.name
    println("[$name] $message on $place")
}


/**
 *
 * @description Context的切换collect的收集上下文在外面的withContext(CoroutineName("Name1")) {
 * @param
 * @return
 * @author est7
 * @time 2023/1/6 16:36
 */
suspend fun main6() {
    withContext(CoroutineName("Name1")) {
        flow {
            present("flow builder", "Message")
            emit("Message1")
        }.flowOn(CoroutineName("Name3"))
            .onEach { present("onEach", it) }
            .flowOn(CoroutineName("Name2"))
            .collect { present("collect", it) }
    }
}
// [Name3] Message on flow builder
// [Name2] Message on onEach
// [Name1] Message on collect




/*
fun updateNews() {
    newsFlow()
        .onStart { showProgressBar() }
        .onCompletion { hideProgressBar() }
        .onEach { view.showNews(it) }
        .catch { view.handleError(it) }
        .launchIn(viewModelScope)
}
*/