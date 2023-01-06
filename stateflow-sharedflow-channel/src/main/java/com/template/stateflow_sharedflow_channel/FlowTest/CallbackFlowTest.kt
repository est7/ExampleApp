package com.template.stateflow_sharedflow_channel.FlowTest

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

/**
 *
 * @author: est7
 * @date: 2023/1/5
 */

private fun textChangeFlow(editText: EditText): Flow<String> {
    return callbackFlow {
        val watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    trySend(s.toString())
                }
            }
        }
        editText.addTextChangedListener(watcher)
        //在 flow 被 close 时调用，可以清理资源，一般必须要有
        awaitClose {
            editText.removeTextChangedListener(watcher)
        }
    }
}


suspend fun testCallbackFlow(editText: EditText) {
    coroutineScope {
        launch {
            textChangeFlow(editText).debounce(300)  //防抖处理，间隔 300ms 响应一次
                .flatMapLatest { keyword ->     //flatMapLatest 操作符，只处理最新的关键字，并且老的查询如果没有完成会自动 cancel 掉
                    println("keyword = $keyword")
                    flow {
                        //根据输入关键字查询信息
                        var result = queryByKeyword(keyword)
                        emit(result)
                    }
                }.catch {
                    println("exception: $it")
                }.collect {
                    //查询到相关结果
                }
        }
    }
}

fun queryByKeyword(keyword: String): String {
    return "query finish"

}










