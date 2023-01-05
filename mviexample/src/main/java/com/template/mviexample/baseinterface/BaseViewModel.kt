package com.template.mviexample.baseinterface

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.channels.Channel

/**
 * @author: est7
 * @date: 2023/1/4
 */
abstract class BaseViewModel<Event : UiEvent, State : UiState, Effect : UiEffect> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State


    private val _state = MutableStateFlow<State>(initialState)
    val uiState = _state.asStateFlow()

    private val _event = MutableSharedFlow<Event>()
    val uiEvent = _event.asSharedFlow()

    private val _effect = Channel<Effect>()
    val uiEffect = _effect.receiveAsFlow()


    init {
        subscribeEvents()
    }

    /**
     * Start listening to Event
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            _event.collect {
                handleEvent(it)
            }
        }
    }

    fun setEvent(event: Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }


    val currentState: State
        get() = _state.value

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _state.value = newState
    }


    fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    abstract fun handleEvent(event: Event)


}