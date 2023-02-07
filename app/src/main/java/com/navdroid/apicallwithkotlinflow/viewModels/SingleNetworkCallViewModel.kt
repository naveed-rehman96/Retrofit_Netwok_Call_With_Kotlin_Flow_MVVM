package com.navdroid.apicallwithkotlinflow.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.navdroid.apicallwithkotlinflow.data.api.ApiHelper
import com.navdroid.apicallwithkotlinflow.data.model.ApiUser
import com.navdroid.apicallwithkotlinflow.states.DispatcherProvider
import com.navdroid.apicallwithkotlinflow.states.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class SingleNetworkCallViewModel(
    private val apiHelper: ApiHelper,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _users = MutableStateFlow<Resource<List<ApiUser>>>(Resource.loading())
    val users: StateFlow<Resource<List<ApiUser>>> = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch(dispatcherProvider.main) {
            _users.value = Resource.loading()
            apiHelper.getUsers()
                .flowOn(dispatcherProvider.io)
                .catch { e ->
                    _users.value = Resource.error(e.toString())
                }
                .collect {
                    _users.value = Resource.success(it)
                }
        }
    }
}
