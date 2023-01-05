package com.template.mviexample.second

import androidx.lifecycle.viewModelScope
import com.template.mviexample.baseinterface.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SecondViewModel : BaseViewModel<SecondContract.Event, SecondContract.State, SecondContract.Effect>() {

    override fun createInitialState(): SecondContract.State {
        return SecondContract.State(SecondContract.RandomNumberState.Idle)
    }

    override fun handleEvent(event: SecondContract.Event) {
        when(event){
            SecondContract.Event.OnRandomNumberClicked -> {
                generateRandomNumber()
            }
            SecondContract.Event.OnShowToastClicked -> setEffect {
                SecondContract.Effect.ShowToast
            }
        }
    }

    /**
     * Generate a random number
     */
    private fun generateRandomNumber() {
        viewModelScope.launch {
            // Set Loading
            setState { copy(randomNumberState = SecondContract.RandomNumberState.Loading) }
            try {
                delay(5000)
                val random = (0..10).random()
                if (random % 2 == 0) {
                    setState { copy(randomNumberState = SecondContract.RandomNumberState.Idle) }
                    throw RuntimeException("Number is even")
                }
                setState { copy(randomNumberState = SecondContract.RandomNumberState.Success(number = random)) }
            } catch (exception : Exception) {
                setEffect { SecondContract.Effect.ShowToast }
            }
        }
    }
}
