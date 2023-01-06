package com.example.utils.ktx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.CoroutineScope

/* ********************************************************************** *\
 *               ╭──→───→───→──╮                │                         *
 *               ↑   RESUMED   ↓                ↑   at least resumed      *
 *  ON_RESUME ←──┼── ─── ─── ──┼──→ ON_PAUSE ───┼────────────────────     *
 *               ↑   STARTED   ↓                ↑   at least started      *
 *  ON_START ←───┼── ─── ─── ──┼──→ ON_STOP ────┼────────────────────     *
 *               ↑   CREATED   ↓                ↑   at least created      *
 *  ON_CREATE ←──┼── ─── ─── ──┼──→ ON_DESTROY ─┼────────────────────     *
 *               ↑ INITIALIZED ↓                │  at least initialized   *
 *        time →─╯      &      │                ↑                         *
 *                  DESTROYED  ↓                │   at least destroyed    *
 *                             ╰→──→───→───→──→─╯                         *
\* ********************************************************************** */

/*
 *  init currentState                 : INITIALIZED
 *  init isAtLeastDestroyed           : true
 *  init isAtLeastInitialized         : true
 *  init isAtLeastCreated             : false
 *  init isAtLeastStarted             : false
 *  init isAtLeastResumed             : false
 *  --------------
 *  onCreate currentState             : INITIALIZED
 *  onCreate isAtLeastDestroyed       : true
 *  onCreate isAtLeastInitialized     : true
 *  onCreate isAtLeastCreated         : false
 *  onCreate isAtLeastStarted         : false
 *  onCreate isAtLeastResumed         : false
 *  --------------
 *  onStart currentState              : CREATED
 *  onStart isAtLeastDestroyed        : true
 *  onStart isAtLeastInitialized      : true
 *  onStart isAtLeastCreated          : true
 *  onStart isAtLeastStarted          : false
 *  onStart isAtLeastResumed          : false
 *  --------------
 *  onResume currentState             : STARTED
 *  onResume isAtLeastDestroyed       : true
 *  onResume isAtLeastInitialized     : true
 *  onResume isAtLeastCreated         : true
 *  onResume isAtLeastStarted         : true
 *  onResume isAtLeastResumed         : false
 *  --------------
 *  afterResume currentState          : RESUMED
 *  afterResume isAtLeastDestroyed    : true
 *  afterResume isAtLeastInitialized  : true
 *  afterResume isAtLeastCreated      : true
 *  afterResume isAtLeastStarted      : true
 *  afterResume isAtLeastResumed      : true
 *  --------------
 *  onPause currentState              : STARTED
 *  onPause isAtLeastDestroyed        : true
 *  onPause isAtLeastInitialized      : true
 *  onPause isAtLeastCreated          : true
 *  onPause isAtLeastStarted          : true
 *  onPause isAtLeastResumed          : false
 *  --------------
 *  onStop currentState               : CREATED
 *  onStop isAtLeastDestroyed         : true
 *  onStop isAtLeastInitialized       : true
 *  onStop isAtLeastCreated           : true
 *  onStop isAtLeastStarted           : false
 *  onStop isAtLeastResumed           : false
 *  --------------
 *  onDestroy currentState            : DESTROYED
 *  onDestroy isAtLeastDestroyed      : true
 *  onDestroy isAtLeastInitialized    : false
 *  onDestroy isAtLeastCreated        : false
 *  onDestroy isAtLeastStarted        : false
 *  onDestroy isAtLeastResumed        : false
 *  --------------
 *  afterDestroy currentState         : DESTROYED
 *  afterDestroy isAtLeastDestroyed   : true
 *  afterDestroy isAtLeastInitialized : false
 *  afterDestroy isAtLeastCreated     : false
 *  afterDestroy isAtLeastStarted     : false
 *  afterDestroy isAtLeastResumed     : false
 */

val Lifecycle.isAtLeastDestroyed
    get() = true

val Lifecycle.isAtLeastInitialized
    get() = onInitializedState || isAtLeastCreated

val Lifecycle.isAtLeastCreated
    get() = onCreatedState || isAtLeastStarted

val Lifecycle.isAtLeastStarted
    get() = onStartedState || isAtLeastResumed

val Lifecycle.isAtLeastResumed
    get() = onResumedState


val LifecycleOwner.isAtLeastDestroyed
    get() = lifecycle.isAtLeastDestroyed

val LifecycleOwner.isAtLeastInitialized
    get() = lifecycle.isAtLeastInitialized

val LifecycleOwner.isAtLeastCreated
    get() = lifecycle.isAtLeastCreated

val LifecycleOwner.isAtLeastStarted
    get() = lifecycle.isAtLeastStarted

val LifecycleOwner.isAtLeastResumed
    get() = lifecycle.isAtLeastResumed


val Lifecycle.onDestroyedState
    get() = currentState == Lifecycle.State.DESTROYED

val Lifecycle.onInitializedState
    get() = currentState == Lifecycle.State.INITIALIZED

val Lifecycle.onCreatedState
    get() = currentState == Lifecycle.State.CREATED

val Lifecycle.onStartedState
    get() = currentState == Lifecycle.State.STARTED

val Lifecycle.onResumedState
    get() = currentState == Lifecycle.State.RESUMED


val LifecycleOwner.onDestroyedState
    get() = lifecycle.onDestroyedState

val LifecycleOwner.onInitializedState
    get() = lifecycle.onInitializedState

val LifecycleOwner.onCreatedState
    get() = lifecycle.onCreatedState

val LifecycleOwner.onStartedState
    get() = lifecycle.onStartedState

val LifecycleOwner.onResumedState
    get() = lifecycle.onResumedState


suspend fun LifecycleOwner.repeatOnCreated(
    block: suspend CoroutineScope.() -> Unit
) = repeatOnLifecycle(Lifecycle.State.CREATED, block)

suspend fun LifecycleOwner.repeatOnStarted(
    block: suspend CoroutineScope.() -> Unit
) = repeatOnLifecycle(Lifecycle.State.STARTED, block)

suspend fun LifecycleOwner.repeatOnResumed(
    block: suspend CoroutineScope.() -> Unit
) = repeatOnLifecycle(Lifecycle.State.RESUMED, block)