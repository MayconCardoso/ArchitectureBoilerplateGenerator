package com.mctech.samplesample_architecture

/**
 * @author MAYCON CARDOSO on 2019-09-05.
 *
 * Used to manage separated component lifecycle. For example, let's say we have 3 different components on your screen.
 * Each component should load some individual data and show on the screen.
 *
 * So basically, you can start three coroutine loading data flow and update each livedata with this component.
 * And according the requests return some data or error you just update this component that your screen component will be notified with the changes and so on.
 *
 */
sealed class ComponentState<out T> {
    object Initializing : ComponentState<Nothing>()
    sealed class Loading : ComponentState<Nothing>(){
        object FromEmpty : Loading()
        object FromData  : Loading()
    }
    data class Error(val reason: Throwable) : ComponentState<Nothing>()
    data class Success<T>(val result: T) : ComponentState<T>()
}