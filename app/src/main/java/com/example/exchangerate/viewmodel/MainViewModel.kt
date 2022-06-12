package com.example.exchangerate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.exchangerate.model.ExchanceRateModel
import com.example.exchangerate.model.RatesModel
import com.example.exchangerate.service.ExchangeService
import kotlinx.coroutines.*
import retrofit2.Response

class MainViewModel : ViewModel() {


    val exchangeService = ExchangeService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        onError("Exception: ${throwable.localizedMessage}")
    }

    val exchangeRate = MutableLiveData<ExchanceRateModel>()
    val exchangeRateCurrencies = MutableLiveData<RatesModel>()

    val exchangeLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(date : String) {
        fetchExchange(date)
    }

    fun refreshCurrency() {
        fetchExchangeCurrency()
    }

    private fun fetchExchange(date : String) {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = exchangeService.dataDetail(date)
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    exchangeRate.value = response.body()
                    exchangeLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun fetchExchangeCurrency() {
        loading.value = true

        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = exchangeService.datagetCurrencies()
            withContext(Dispatchers.Main) {
                if(response.isSuccessful) {
                    exchangeRateCurrencies.value = response.body()
                    exchangeLoadError.value = null
                    loading.value = false
                } else {
                    onError("Error: ${response.message()}")
                }
            }
        }
    }

    private fun onError(message: String) {
        exchangeLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}