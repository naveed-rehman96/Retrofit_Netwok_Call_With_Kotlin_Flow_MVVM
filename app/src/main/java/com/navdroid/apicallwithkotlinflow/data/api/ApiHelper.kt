package com.navdroid.apicallwithkotlinflow.data.api

import com.navdroid.apicallwithkotlinflow.data.model.ApiUser
import kotlinx.coroutines.flow.Flow

interface ApiHelper {

    fun getUsers(): Flow<List<ApiUser>>

    fun getMoreUsers(): Flow<List<ApiUser>>

    fun getUsersWithError(): Flow<List<ApiUser>>
}
