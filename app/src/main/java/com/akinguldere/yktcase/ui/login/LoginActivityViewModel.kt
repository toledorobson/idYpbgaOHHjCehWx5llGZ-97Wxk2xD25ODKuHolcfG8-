package com.akinguldere.yktcase.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinguldere.yktcase.model.FSProduct
import com.akinguldere.yktcase.model.LoginRequest
import com.akinguldere.yktcase.model.LoginResponse
import com.akinguldere.yktcase.repository.FakeStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginActivityViewModel @Inject constructor(private val repository: FakeStoreRepository) :
    ViewModel() {
    private val _response = MutableLiveData<LoginResponse>()
    private val _isLoading = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val loginResponse: LiveData<LoginResponse> get() = _response
    val isLoading: LiveData<Boolean> get() = _isLoading
    val error: LiveData<String> get() = _error

    fun login(request: LoginRequest) = viewModelScope.launch {
        _isLoading.postValue(true)

        repository.login(request).let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                _error.postValue("Username or password is incorrect.")
            }

            // api call completed.
            _isLoading.postValue(false)

        }
    }
}