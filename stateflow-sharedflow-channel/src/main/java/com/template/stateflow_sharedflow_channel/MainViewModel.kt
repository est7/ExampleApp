package com.template.stateflow_sharedflow_channel

import javax.inject.Inject
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.RENDEZVOUS
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 *
 * @author: est7
 * @date: 2023/1/5
 */
@HiltViewModel
class MainViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val initialValue_livedata = "Hello World!_livedata"
    private val initialValue_statedata = "Hello World!_statedata"

    //livedata可以不设置初始值，但是stateFlow没有空参构造
//    private val _liveData = MutableLiveData<String>()
    private val _liveData = MutableLiveData(initialValue_livedata)
    val liveData: LiveData<String> = _liveData


    //stateFlow is hot
    private val _stateFlow = MutableStateFlow(initialValue_statedata)
    val stateFlow = _stateFlow.asStateFlow()


    //冷流，不会发射任何值，直到有订阅者
    private val _flow: Flow<String> = flow {
        repeat(5) { count ->
            emit("Hello_flow $count")
            //在 Kotlin 1.1 起，数字常量支持下滑下 _ 按位分隔，使数字更易读。比如：
            //
            //按英文方式断位： 1_000 ，1_000_000
            //按中文方式断位：1_0000 ，10000_0000
            //
            //val oneThousand = 1_000
            //val oneMillion = 1_000_000
            //val oneBillion = 1_000_000_000
            //
            //// 按中文表示 一万
            //val wan = 1_0000
            //// 按中文表示 一亿
            //val yi = 10000_0000
            //
            delay(1_000)
        }
    }


    //名字本身就说明它是共享的，这个流量可以被多个消费者共享，
    // 如果在share stream上发生多个collect call，将有一个单一的stream在所有消费者之间共享，这与普通流量不同。
    private val _sharedFlow = MutableSharedFlow<String>()


    /**
     * 测试重放
     *
     *  replay - 重播给新订阅者的值的数量（不能为负数，默认为零）。
     *  extraBufferCapacity - 除了replay之外缓冲的值的数量。当有剩余的缓冲区空间时， emit不会挂起（可选，不能为负数，默认为零）。
     * onBufferOverflow - 配置缓冲区溢出时的emit操作。可选，默认为suspending发出值的尝试。
     * 仅当replay > 0或extraBufferCapacity > 0时才支持BufferOverflow.SUSPEND以外的值。
     * 只有当至少有一个订阅者还没有准备好接受新值时，才会发生缓冲区溢出。
     * 在没有订阅者的情况下，只会存储最近的replay值，并且不会触发缓冲区溢出行为，也不会产生任何影响。
     */
    private val _sharedFlowTest = MutableSharedFlow<String>(
        replay = 1,
        extraBufferCapacity = 0,
        onBufferOverflow = BufferOverflow.SUSPEND
    )

    val sharedFlow = _sharedFlowTest.asSharedFlow()

    /**
     *
     *SharedFlow作为一个热流，即使没有人听，也会发出数据。
     *channel将保留数据，创建具有指定缓冲区容量（或默认情况下没有缓冲区）的通道,直到有人消费它。
     *因此，如果你的视图没有准备好接收一个事件，而sharedFlow发射了它，那么这个事件就会丢失。所以channel更适合于发送一次性的事件。
     *当然，你可以为sharedflow设置回复次数，但这样你的事件就会被重复。*
     *
     *params：
     *capacity
     *
     *onBufferOverflow - 配置缓冲区溢出操作（可选，默认为suspending send值的尝试，
     * 仅在capacity >= 0或capacity == Channel.BUFFERED时支持，隐式创建一个至少包含一个缓冲元素的通道）。
     *
     *
     *onUndeliveredElement - 一个可选函数，在元素已发送但未交付给消费者时调用
     *throw：
     *IllegalArgumentException - 当capacity < -2 时
     */
    private val _channel = Channel<String>(
        capacity = RENDEZVOUS,
        onBufferOverflow = BufferOverflow.SUSPEND,
        onUndeliveredElement = { element ->
            // Called when the element is dropped due to the buffer overflow.
            // The default implementation does nothing.
            //当元素由于缓冲区溢出而被丢弃时调用
        }
    )

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun CoroutineScope.makeChannel() = produce {
        println("Channel started")
        for (i in 1..5) {
            delay(1_000)
            send(i)
        }
        /*
                repeat(5) { count ->
                    _channel.send("Hi_chan $count ,")
                    delay(1_000)
                }
        */

    }


    val channel = _channel.receiveAsFlow()


    fun updateLiveData() {
        _liveData.postValue(getApplication<Application>().applicationContext.resources.getString(R.string.livedata))
//        _liveData.value = ":dfasf"
    }

    fun updateStateFlow() {
        viewModelScope.launch {
            _stateFlow.emit(getApplication<Application>().applicationContext.resources.getString(R.string.stateflow))
            //livedata和stateFlow可以直接.value 赋值，sharedFlow.不行
//            _stateFlow.value = "StateFlow"

            //注意是stateFlow做了防重的比较，所以如果state跟上一个相同，那就不会再发射了
        }
    }

    fun updateFlow(): Flow<String> {
        return _flow
    }

    fun updateSharedFlow() {
        viewModelScope.launch {
//            _sharedFlow.emit(getApplication<Application>().applicationContext.resources.getString(R.string.sharedflow))
            _sharedFlowTest.emit(
                getApplication<Application>().applicationContext.resources.getString(
                    R.string.sharedflow
                )
            )

        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    fun updateChannel() {
        viewModelScope.launch {
            repeat(5) { count ->
                _channel.send("Hi_chan $count ,")
                delay(1_000)
            }
        }
    }
}