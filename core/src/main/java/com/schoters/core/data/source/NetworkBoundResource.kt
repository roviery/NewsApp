package com.schoters.core.data.source

import android.util.Log
import com.schoters.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(isOnline: Boolean) {

    private var result: Flow<Resource<ResultType>> = flow {
        emit(Resource.Loading())
        if (isOnline) {
            emit(Resource.Loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    Log.d("News API", "SUCCESS")
                    emit(Resource.Success(changeTypeResult(apiResponse.data)))
                }
                is ApiResponse.Empty -> {
                    Log.d("News API", "EMPTY")
                }
                is ApiResponse.Error -> {
                    Log.e("News API", "ERROR")
                    emit(Resource.Error(apiResponse.errorMessage))
                }
            }
        } else {
            Log.e("News API", "No Connection")
        }
    }

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun changeTypeResult(data: RequestType): ResultType

    fun asFlow(): Flow<Resource<ResultType>> = result


}