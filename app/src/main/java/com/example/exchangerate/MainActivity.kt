package com.example.exchangerate

import android.app.AlertDialog
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.adapter.ExchangeRateAdapter
import com.example.exchangerate.databinding.ActivityMainBinding
import com.example.exchangerate.model.RatesModel
import com.example.exchangerate.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var viewmodel: MainViewModel
    private lateinit var adapter: ExchangeRateAdapter
    val myCalendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewmodel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        viewmodel.refresh("2022-02-02")

        //viewmodel.refreshCurrency()
        binding.updateDate.setOnClickListener {
            var date = binding.fieldDate.text
            viewmodel.refresh(date.toString())
            observeViewModel()
        }

        observeViewModel()

        val date = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, monthOfYear)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLabel()
        }


            binding.fieldDate.setOnClickListener {
                var datePicker = DatePickerDialog(
                    this, AlertDialog.THEME_HOLO_LIGHT, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)

                )
                datePicker.show()

            }

    }


    private fun updateLabel() {
        val myFormat = "yyyy-MM-dd"
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding.fieldDate.setText(sdf.format(myCalendar.time))

    }

    fun observeViewModel() {
       viewmodel.exchangeRate.observe(this, androidx.lifecycle.Observer { it ->
           it?.let {
               val rates = ArrayList<String>()

               rates.addAll(listOf(it.rates.toString()))
              var  ratesArrayList = arrayListOf(it.rates)

              adapter = ExchangeRateAdapter(ratesArrayList as ArrayList<RatesModel>)

               val recyclerView: RecyclerView = binding.recyclerView
               recyclerView.adapter = adapter

           }
       })

    }

}