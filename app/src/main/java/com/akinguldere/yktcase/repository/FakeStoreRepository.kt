package com.akinguldere.yktcase.repository

import com.akinguldere.yktcase.api.FakeStoreService
import com.akinguldere.yktcase.model.LoginRequest
import javax.inject.Inject

class FakeStoreRepository @Inject constructor(private val api: FakeStoreService) {
    suspend fun getProducts() = api.getProducts()
    suspend fun login(request: LoginRequest) = api.login(request)

}