package com.akinguldere.yktcase.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akinguldere.yktcase.model.FSProduct
import com.akinguldere.yktcase.repository.FakeStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val repository: FakeStoreRepository) :
    ViewModel() {
    private val _response = MutableLiveData<List<FSProduct>>()
    private val _isLoading = MutableLiveData<Boolean>()

    val responseMovies: LiveData<List<FSProduct>> get() = _response
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getProducts() = viewModelScope.launch {
        _isLoading.postValue(true)

        repository.getProducts().let { response ->

            if (response.isSuccessful) {
                _response.postValue(response.body())
            } else {
                println("Error ${response.errorBody()}")
            }

            // api call completed.
            _isLoading.postValue(false)

        }
    }
}