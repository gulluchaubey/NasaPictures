package com.example.nasapictures.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ItemNasaAllDataBinding

class NasaAllDataAdapter(private var mcontext:Context,var list:ArrayList<NasaAllDataItem>,var clickHandler:(data:NasaAllDataItem)->Unit):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext),
        R.layout.item_nasa_all_data,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).setData(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
    inner class MyViewHolder(var binding: ItemNasaAllDataBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(data:NasaAllDataItem){
            binding.nasaData = data
            binding.root.setOnClickListener { clickHandler.invoke(data) }
        }
    }
}