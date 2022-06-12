package com.example.exchangerate.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerate.R
import com.example.exchangerate.model.RatesModel

class ExchangeRateAdapter(  private var dataNetModel: ArrayList<RatesModel>) :  RecyclerView.Adapter<ExchangeRateAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExchangeRateAdapter.ViewHolder {

        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rate, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExchangeRateAdapter.ViewHolder, position: Int) {
        var getRateDetails  = dataNetModel[position]

        holder.txtRateName.text = getRateDetails.AUD

    }

    override fun getItemCount(): Int {
        return dataNetModel.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val txtRateName: TextView
        val txtRateDescription: TextView
        val imageViewRate: ImageView


        init {
            txtRateName = mView.findViewById<View>(R.id.textViewRateName) as TextView
            txtRateDescription= mView.findViewById(R.id.textViewRateDescription)
            imageViewRate = mView.findViewById(R.id.imageViewRateLogo)


        }
    }
}