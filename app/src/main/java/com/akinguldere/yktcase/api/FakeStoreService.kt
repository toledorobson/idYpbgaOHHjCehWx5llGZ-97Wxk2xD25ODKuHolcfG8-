package com.akinguldere.yktcase.api

import com.akinguldere.yktcase.model.FSProduct
import com.akinguldere.yktcase.model.LoginRequest
import com.akinguldere.yktcase.model.LoginResponse
import com.akinguldere.yktcase.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FakeStoreService {

    @GET(Constants.EndPoints.GET_PRODUCTS)
    suspend fun getProducts(): Response<List<FSProduct>>

    @POST(Constants.EndPoints.LOGIN)
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}