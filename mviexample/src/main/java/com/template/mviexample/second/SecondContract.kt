package com.template.mviexample.second

import com.template.mviexample.baseinterface.UiEffect
import com.template.mviexample.baseinterface.UiEvent
import com.template.mviexample.baseinterface.UiState

/**
 *
 * @author: est7
 * @date: 2023/1/4
 */
class SecondContract {
    data class State(
        val randomNumberState: RandomNumberState
    ) : UiState

    sealed class RandomNumberState{
        object Idle : RandomNumberState()
        object Loading : RandomNumberState()
        data class Success(val number: Int) : RandomNumberState()
    }

    sealed class Event : UiEvent {
        object OnRandomNumberClicked : Event()
        object OnShowToastClicked : Event()
    }

    sealed class Effect : UiEffect {
        object ShowToast : Effect()

    }

}