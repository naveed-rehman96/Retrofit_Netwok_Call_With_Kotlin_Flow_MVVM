package com.navdroid.apicallwithkotlinflow.di // ktlint-disable filename

import com.navdroid.apicallwithkotlinflow.data.api.ApiHelper
import com.navdroid.apicallwithkotlinflow.data.api.ApiHelperImpl
import com.navdroid.apicallwithkotlinflow.data.api.RetrofitBuilder
import com.navdroid.apicallwithkotlinflow.states.DefaultDispatcherProvider
import com.navdroid.apicallwithkotlinflow.viewModels.SingleNetworkCallViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    fun provideApiService(): ApiHelper {
        return ApiHelperImpl(RetrofitBuilder.apiService)
    }

    viewModel { SingleNetworkCallViewModel(apiHelper = provideApiService(), dispatcherProvider = DefaultDispatcherProvider()) }
}
