package com.navdroid.apicallwithkotlinflow.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.navdroid.apicallwithkotlinflow.data.api.ApiHelper
import com.navdroid.apicallwithkotlinflow.states.DispatcherProvider

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val dispatcherProvider: DispatcherProvider
) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dispatcherProvider) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
