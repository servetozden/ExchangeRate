package com.example.exchangerate.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class RatesModel(

    var AUD: String,
    var BGN: String,
    var BRL: String,
    var CAD: String,
    var CHF: String,
    var CNY: String,
    var CZK: String,
    var DKK: String,
    var GBP: String,
    var HKD: String,
    var HRK: String,
    var HUF: String,
    var IDR: String,

    var ILS: String,
    var INR: String,
    var ISK: String,
    var JPY: String,
    var KRW: String,
    var MXN: String,
    var MYR: String,
    var NOK: String,
    var NZD: String,
    var PHP: String,

    var PLN: String,
    var RON: String,
    var SEK: String,
    var SGD: String,
    var THB: String,
    var TRY: String,
    var USD: String,
    var ZAR: String
)