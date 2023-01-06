package com.template.kotlinsamples_flowsamples


/**
 *
 * @author: est7
 * @date: 2023/1/6
 */


fun main0() {
    val f: ((str: String) -> Unit) -> Unit = { emit -> // 1
        emit("A")
        emit("B")
        emit("C")
    }
    f { print(it) } // ABC
    f { print(it) } // ABC
}
/**------------------------------------------------------------------------**/
fun interface FlowCollector1 {
    fun emit(value: String)
}


//FlowCollector1 = (str: String) -> Unit

fun main1() {
    val f: (FlowCollector1) -> Unit = {
        it.emit("A")
        it.emit("B")
        it.emit("C")
    }
    f { print(it) } // ABC
    f { print(it) } // ABC
}
/**------------------------------------------------------------------------**/
fun main2() {
    val f: FlowCollector1.() -> Unit = {
        emit("A")
        emit("B")
        emit("C")
    }
    f { print(it) } // ABC
    f { print(it) } // ABC
}
/**------------------------------------------------------------------------**/
interface Flow1 {
    fun collect(collector: FlowCollector1)
}

fun main3() {
    val builder: FlowCollector1.() -> Unit = {
        emit("A")
        emit("B")
        emit("C")
    }
    val flow: Flow1 = object : Flow1 {
        override fun collect(collector: FlowCollector1) {
            collector.builder()
        }
    }
    flow.collect { print(it) } // ABC
    flow.collect { print(it) } // ABC
}
/**------------------------------------------------------------------------**/
fun flow1(builder:FlowCollector1.()->Unit): Flow1 {
    return object : Flow1 {
        override fun collect(collector: FlowCollector1) {
            collector.builder()
        }
    }
}
fun main4(){
    val f :Flow1 = flow1{
        emit("A")
        emit("B")
        emit("C")
    }

    f.collect { print(it) } // ABC
    f.collect { print(it) } // ABC
}
/**------------------------------------------------------------------------**/

fun interface FlowCollector2<T> {
    suspend fun emit(value: T)
}

interface Flow2<T> {
    suspend fun collect(collector: FlowCollector2<T>)
}

fun <T> flow2(builder: suspend FlowCollector2<T>.() -> Unit) = object : Flow2<T> {
    override suspend fun collect(collector: FlowCollector2<T>) {
        collector.builder()
    }
}

suspend fun main5() {
    val f: Flow2<String> = flow2 {
        emit("A")
        emit("B")
        emit("C")
    }
    f.collect { print(it) } // ABC
    f.collect { print(it) } // ABC
}

/**------------------------------------------------------------------------**/
/**------------------------------------------------------------------------**/
/**------------------------------------------------------------------------**/
private fun interface Flow<out T> {
    suspend fun collectElement(collector: FlowCollector<T>)
}

private fun interface FlowCollector<in T> {
    suspend fun emit(value: T)
}

private fun <T> flow(
    block: suspend FlowCollector<T>.() -> Unit
) = Flow { collector -> collector.block() }

private suspend inline fun <T> Flow<T>.collect(
    crossinline action: suspend (value: T) -> Unit
) = collectElement { value -> action(value) }

suspend fun main() {
    flow { // 1
        emit("A")
    }.collect { value -> // 2
        println(value)
    }
}