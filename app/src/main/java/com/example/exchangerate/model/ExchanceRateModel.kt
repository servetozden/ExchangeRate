package com.example.exchangerate.model

import com.google.gson.annotations.SerializedName
import java.time.temporal.TemporalAmount

data class ExchanceRateModel(
    @SerializedName("amount")
    var amount: String,
    @SerializedName("base")
    var base : String,
    @SerializedName("date")
    var date: String,

    var rates : RatesModel


)
