package com.example.exchangerate.service

import com.example.exchangerate.model.ExchanceRateModel
import com.example.exchangerate.model.RatesModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ExchangeAPI {



    @GET("{date}")
    suspend fun getExchangeRate(@Path("date" ) date : String): Response<ExchanceRateModel>

    @GET("currencies")
    suspend fun getCurrencies(): Response<RatesModel>



}