package com.template.mviexample.main

import androidx.lifecycle.viewModelScope
import com.template.mviexample.baseinterface.BaseViewModel
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : BaseViewModel<MainContract.Event, MainContract.State, MainContract.Effect>() {

    override fun createInitialState(): MainContract.State {
        return MainContract.State(MainContract.RandomNumberState.Idle)
    }

    override fun handleEvent(event: MainContract.Event) {
        when(event){
            MainContract.Event.OnRandomNumberClicked -> {
                generateRandomNumber()
            }
            MainContract.Event.OnShowToastClicked -> setEffect {
                MainContract.Effect.ShowToast
            }
        }
    }

    /**
     * Generate a random number
     */
    private fun generateRandomNumber() {
        viewModelScope.launch {
            // Set Loading
            setState { copy(randomNumberState = MainContract.RandomNumberState.Loading) }
            try {
                delay(5000)
                val random = (0..10).random()
                if (random % 2 == 0) {
                    setState { copy(randomNumberState = MainContract.RandomNumberState.Idle) }
                    throw RuntimeException("Number is even")
                }
                setState { copy(randomNumberState = MainContract.RandomNumberState.Success(number = random)) }
            } catch (exception : Exception) {
                setEffect { MainContract.Effect.ShowToast }
            }
        }
    }
}
