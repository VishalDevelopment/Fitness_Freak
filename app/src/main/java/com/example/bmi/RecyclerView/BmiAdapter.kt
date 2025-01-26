package com.example.bmi.RecyclerView

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bmi.R
import com.example.bmi.RoomDb.BmiTable

class BmiAdapter(var context: Context , var dataList: List<BmiTable>):RecyclerView.Adapter<BmiAdapter.ViewHolder>() {
  inner  class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
      var  id = itemView.findViewById<TextView>(R.id.Id_data)
      var gender = itemView.findViewById<TextView>(R.id.Gender_data)
      var height = itemView.findViewById<TextView>(R.id.Height_data)
      var weight = itemView.findViewById<TextView>(R.id.Weight_data)
      var date = itemView.findViewById<TextView>(R.id.Date)
      var bmiLevel = itemView.findViewById<TextView>(R.id.bmi_data)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.rv_datalist,parent,false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.id.text = dataList[position].id.toString()
        holder.gender.text = "Gender : ${ dataList[position].gender }"
        holder.height.text = "Height : ${dataList[position].height}"
        holder.weight.text = "Weight : ${dataList[position].weight}"
        holder.date.text = "date : ${dataList[position].date.toString()}"
        holder.bmiLevel.text = "Bmi level : ${dataList[position].bmiLevel}"
    }
}