package com.example.exchangerate.service

import android.util.Log
import com.example.exchangerate.model.ExchanceRateModel
import com.example.exchangerate.model.RatesModel
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ExchangeService {


    private val api = Retrofit.Builder()
        .baseUrl(APIUrl.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ExchangeAPI::class.java)


  suspend fun dataDetail(date : String) : Response<ExchanceRateModel> {

     return api.getExchangeRate(date)
   }


    suspend fun datagetCurrencies() : Response<RatesModel> {

        return api.getCurrencies()
    }

}