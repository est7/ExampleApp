package com.template.stateflow_sharedflow_channel.ChannelTest

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.FileOutputStream

/**
 *
 * @author: est7
 * @date: 2023/1/5
 */
private fun CoroutineScope.makeChannel() = produce {
    println("Channel started")
    for (i in 1..3) {
        delay(1000)
        send(i)
    }
}

/**
 *
 * @description 测试多个接收者只能接收到一次,测试迭代器实现revevice
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 16:36
 */
suspend fun main1() = coroutineScope {
    val channel = makeChannel()
    delay(1000)
    println("Calling channel...")
    for (value in channel) {
        println(value)
    }
    println("Consuming again...")
    for (value in channel) {
        println("again:" + value)
    }
}
// Channel started
// (1 sec)
// Calling channel...
// 1
// (1 sec)
// 2
// (1 sec)
// 3
// Consuming again...


/**
 *
 * @description 测试send和receive
 * main2方法中的channel是没有关闭的，所以在消费者消费完之后，
 * 生产者还会继续生产，所以消费者会一直阻塞在receive方法上，所以我们需要关闭channel
 * 会停在index =5 也就是第六次 发送上，继续接收，需要再次调用receive方法
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 16:36
 */
suspend fun main2(): Unit = coroutineScope {
    val channel = Channel<Int>()
    launch {
        repeat(10) { index ->
            delay(1000)
            println("Producing next one,index=" + index)
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


/**
 *
 * @description 解决上面的不close导致阻塞revecive的问题
 * 使用这种方式发送元素的常见问题是：很容易忘记关闭 channel，特别是在异常情况下。
 * 如果一个协程因为异常而停止生产，另一个协程将会永远的等待元素。
 * 使用 produce 函数要方便的多，它是一个返回 ReceiveChannel 的协程构建器。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 16:36
 */
suspend fun main3(): Unit = coroutineScope {
    val channel = Channel<Int>()
    launch {
        repeat(5) { index ->
            delay(1000)
            println("Producing next one")
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

// 这个函数将会创建一个 channel，并且一直在
// 上面生产正整数，直至输入的最大值
fun CoroutineScope.produceNumbers(
    max: Int
): ReceiveChannel<Int> = produce {
    var x = 0
    while (x < max) send(x++)
}

/**
 *
 * @description 当协程以任何方式结束（完成、停止、取消）时， produce 函数都会关闭通道。
 * 多亏了这一点，我们永远不会忘记调用 close。
 * produce 构建器是一个非常受欢迎创建 channel 的方式，
 * 理由很充分：它提供了很多安全保障，并且方便。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 16:46
 */
suspend fun main4(): Unit = coroutineScope {
    val channel = produce {
        repeat(5) { index ->
            println("Producing next one")
            delay(1000)
            send(index * 2)
        }
    }
    for (element in channel) {
        println(element)
    }
}


/**
 *
 * @description Channel.UNLIMITED 无限制的缓冲区
 * 我们将生产者快起来，接收者慢起来。在无限容量的情况下，channel 应该可以容纳所有的元素，然后让它们一个个的被接收处理。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 16:52
 */
suspend fun main5(): Unit = coroutineScope {
    val channel = produce(capacity = Channel.UNLIMITED) {
        repeat(5) { index ->
            send(index * 2)
            delay(100)
            println("发送")
        }
    }
    delay(1000)
    for (element in channel) {
        println(element)
        delay(1000)
    }
}


/**
 *
 * @descriptiona capacity = 3 通道缓冲区的容量 =3
 * 生产到缓冲区满了，之后生产者将开始等待接收
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 16:58
 */
suspend fun main6(): Unit = coroutineScope {
    val channel = produce(capacity = 3) {
        repeat(5) { index ->
            send(index * 2)
            delay(100)
            println("发送")
        }
    }
    delay(1000)
    for (element in channel) {
        println(element)
        delay(1000)
    }
}

/**
 *
 * @description Channel.RENDEZVOUS 默认就是这个,
 * 这意味着只有在发送者和接收者相遇时，才会发生数据通信。（所以它像一个图书交换点，而不是一个书柜）
 * 默认容量（Channel.RENDEZVOUS）的 channel，生产者将始终等待接收者。
 * 观看demo可以看出来是这样一个一个蹦的
 * 0 发送
 * 2 发送
 * 4 发送
 * 6 发送
 * 8 发送
 *
 * 也就是说，生产者和消费者之间的通信是同步的。先执行消费者消费代码，然后生产者生产。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 16:57
 */
suspend fun main7(): Unit = coroutineScope {
    val channel = produce {
        // 或者 produce(capacity = Channel.RENDEZVOUS) {
        repeat(5) { index ->
            send(index * 2)
            delay(100)
            println("发送")
        }
    }
    delay(1000)
    for (element in channel) {
        println(element)
        delay(1000)
    }
}


/**
 *
 * @description 在使用 Channel.CONFLATED 时，我们不会存储过去的元素。
 * 新元素将会替换之前的元素，因此我们将只能接收最后一个元素，丢失之前发送的元素。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 17:03
 */
suspend fun main8(): Unit = coroutineScope {
    val channel = produce(capacity = Channel.CONFLATED) {
        repeat(5) { index ->
            send(index * 2)
            delay(100)
            println("发送")
        }
    }
    delay(1000)
    for (element in channel) {
        println(element)
        delay(1000)
    }
}


/**
 *
 * @description BufferOverflow.SUSPEND 通道缓冲区的容量 =2,当缓冲区满了就等消费，消费了一个之后就再生产一个
 * 发送
发送
0
发送
2
发送
4
发送
6
8
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 17:10
 */
suspend fun main9(): Unit = coroutineScope {
    val channel = Channel<Int>(
        capacity = 2, onBufferOverflow = BufferOverflow.SUSPEND
    )
    launch {
        repeat(5) { index ->
            channel.send(index * 2)
            delay(100)
            println("发送")
        }
        channel.close()
    }
    delay(1000)
    for (element in channel) {
        println(element)
        delay(1000)
    }
}


/**
 *
 * @description onBufferOverflow的 BufferOverflow.DROP_OLDEST 我们设置一个缓冲区大小为 2，
 * 当缓冲区满了之后，我们将丢弃最旧的元素。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 17:09
 */
suspend fun main10(): Unit = coroutineScope {
    val channel = Channel<Int>(
        capacity = 2, onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    launch {
        repeat(5) { index ->
            channel.send(index * 2)
            delay(100)
            println("发送")
        }
        channel.close()
    }
    delay(1000)
    for (element in channel) {
        println(element)
        delay(1000)
    }
}

/**
 *
 * @description onBufferOverflow的 BufferOverflow.DROP_LATEST 我们设置一个缓冲区大小为 2，
 * 当缓冲区满了之后，我们将丢弃最新的元素。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 17:09
 */
suspend fun main11(): Unit = coroutineScope {
    val channel = Channel<Int>(
        capacity = 2, onBufferOverflow = BufferOverflow.DROP_LATEST
    )
    launch {
        repeat(5) { index ->
            channel.send(index * 2)
            delay(100)
            println("发送")
        }
        channel.close()
    }
    delay(1000)
    for (element in channel) {
        println(element)
        delay(1000)
    }
}


/**
 *
 * @description onUndeleliveredElement 出了问题还要记得关流， 这里就是用来做这个
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 17:32
 */
suspend fun main12(): Unit = coroutineScope {
    val channel = Channel<FileOutputStream>() { resource ->
        //closeFileOutputStream
        resource.close()
    }

    fun openResource(): FileOutputStream {
        return FileOutputStream("test.txt")
    }

// 生产者代码
    val resourceToSend = openResource()

    channel.send(resourceToSend)

// 消费者代码
    val resourceReceived = channel.receive()

    try {
        // 接收工作
        resourceReceived.flush()
    } finally {
        //FileOutputStream.close
        resourceReceived.close()
    }
}

fun CoroutineScope.produceNumbers() = produce {
    repeat(10) {
        delay(100)
        send(it)
    }
}


fun CoroutineScope.launchProcessorUseForeach(
    id: String, channel: ReceiveChannel<Int>
) = launch {
    for (msg in channel) {
        println("received : id = $id,  msg= $msg")
    }
}


//生成多个消费者时，多个协程使用consumeEach不安全
fun CoroutineScope.launchProcessorUseConsumeEach(
    id: String, channel: ReceiveChannel<Int>
) = launch {
    //consumeEach is not safe to use from multiple coroutines
    //多个协程使用consumeEach不安全
    channel.consumeEach { msg ->
        println("received : id = $id,  msg= $msg")
    }

}


/**
 *
 * @description 使用Foreach 可以顺序的获得元素均匀分布。
 * channel 有一个 FIFO（先进先出）的协程队列等待一个元素。这就是为什么在上面的例子中，可以看到每个元素被下一个协程接收（0，1，2，0，1，2…）。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 19:13
 */
suspend fun main13() {
    coroutineScope {
        val channel = produceNumbers()
        repeat(3) { id ->
            delay(10)
            println("coroutineScope1:$id")
            launchProcessorUseForeach("coroutineScope1:$id", channel)
            launchProcessorUseForeach("coroutineScope2:$id", channel)
        }
    }
}

/**
 *
 * @description 测试ConsumeEach看看是否真的会导致消费者乱序
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 19:12
 */
suspend fun main14() {
    coroutineScope {
        val channel = produceNumbers()
        repeat(3) { id ->
            delay(10)
            println("coroutineScope1:$id")
            launchProcessorUseConsumeEach("coroutineScope1:$id", channel)
            launchProcessorUseConsumeEach("coroutineScope2:$id", channel)
        }
    }
}


suspend fun sendString(
    channel: SendChannel<String>, text: String, time: Long
) {
    while (true) {
        delay(time)
        channel.send(text)
    }
}

/**
 *
 * @description
 * Fan-in
 * 多个协程可以发送到同一个 channel。在下面的例子中，你可以看到两个协程将元素发送到同一个 channel。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 19:29
 */
fun main15() = runBlocking {
    val channel = Channel<String>()
    launch { sendString(channel, "foo", 200L) }
    launch { sendString(channel, "BAR!", 500L) }
    repeat(10) {
        println(channel.receive())
    }

    //要取消这个,不然一直在等receive
    //    这里直接取消了launch开的子协程，也就是不再发送了
    coroutineContext.cancelChildren()
}



/**
 *
 * @description 把多个channel合并成一个channel
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 20:06
 */
fun <T> CoroutineScope.fanIn(
    vararg channels: ReceiveChannel<T>
): ReceiveChannel<T> = produce {
    for (channel in channels) {
        launch {
            for (elem in channel) {
                send(elem)
            }
        }
    }
}

suspend fun main16() = coroutineScope {
    val channel1 = Channel<String>()
    val channel2 = Channel<String>()
    launch { sendString(channel1, "foo", 200L) }
    launch { sendString(channel2, "BAR!", 500L) }

    val channel = fanIn(channel1,channel2)
    repeat(10) {
        println(channel.receive())
    }


    //记得要取消掉，不然会一直运行
//    这里直接取消了launch开的子协程，也就是不再发送了
    coroutineContext.cancelChildren()

}


// 一个 Channel 发送从 1 到 3
fun CoroutineScope.numbers(): ReceiveChannel<Int> = produce {
    repeat(3) { num ->
        send(num + 1)
    }
}

fun CoroutineScope.square(numbers: ReceiveChannel<Int>) = produce {
    for (num in numbers) {
        send(num * num)
    }
}

/**
 *
 * @description 有时我们设置两个 channel，其中一个产生的元素是基于从另一个接收到元素。在这种情况下，我们称之为管道。
 * @param
 * @return
 * @author est7
 * @time 2023/1/5 19:41
 */
suspend fun main17() = coroutineScope {
    val numbers = numbers()
    val squared = square(numbers)
    for (num in squared) {
        println(num)
    }
}






/*----------------------*

 */

sealed class Coffee
class Milk
class GroundCoffee
enum class CoffeeType { ESPRESSO, LATTE }

class Espresso(val ground: GroundCoffee) : Coffee() {
    override fun toString(): String = "Espresso"
}

class Latte(val milk: Milk, val espresso: Espresso) : Coffee() {
    override fun toString(): String = "Latte"
}

data class Order(val customer: String, val type: CoffeeType)

data class CoffeeResult(val coffee: Coffee, val customer: String, val baristaName: String)

private fun prepareCoffee(type: CoffeeType): Coffee {
    val groundCoffee = groundCoffee()
    val espresso = makeEspresso(groundCoffee)
    val coffee = when (type) {
        CoffeeType.ESPRESSO -> espresso
        CoffeeType.LATTE -> {
            val milk = brewMilk()
            Latte(milk, espresso)
        }
    }
    return coffee
}

fun groundCoffee(): GroundCoffee {
    longOperation()
    return GroundCoffee()
}

fun brewMilk(): Milk {
    longOperation()
    return Milk()
}

fun longOperation() {
    //    val size = 820 // ~1 second on my MacBook
    val size = 350 // ~0.1 second on my MacBook
    val list = List(size) { it }
    val listOfLists = List(size) { list }
    val listOfListsOfLists = List(size) { listOfLists }
    listOfListsOfLists.hashCode()
}


fun makeEspresso(ground: GroundCoffee): Espresso {
    longOperation()
    return Espresso(ground)
}

fun CoroutineScope.serveOrders(
    orders: ReceiveChannel<Order>,
    baristaName: String
): ReceiveChannel<CoffeeResult> = produce {
    for (order in orders) {
        //cpu密集操作
        val coffee = prepareCoffee(order.type)

        send(CoffeeResult(coffee, order.customer, baristaName))
    }
}


//sampleStart
suspend fun main() = coroutineScope {
    //一百个顾客，每个顾客点一杯咖啡
    val orders = List(100) { Order("Customer$it", CoffeeType.values().random()) }

    //总订单send进channel
    val ordersChannel = produce {
        orders.forEach { send(it) }
    }

    //fanIn
    val coffeeResults = fanIn(
        serveOrders(ordersChannel, "Alex"),
        serveOrders(ordersChannel, "Bob"),
        serveOrders(ordersChannel, "Celine"),
    )

    for (coffeeResult in coffeeResults) {
        println("Serving $coffeeResult")
    }
}

