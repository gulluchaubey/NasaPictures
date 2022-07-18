package com.example.nasapictures.nasaDetails

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ItemNasaDataBinding

class NasaDetailImageAdapter(var mcontext:Context,var imgList:ArrayList<Image>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(DataBindingUtil.inflate(LayoutInflater.from(mcontext), R.layout.item_nasa_data,parent,false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as MyViewHolder).setData(imgList[position])
    }
    inner class MyViewHolder(var binding:ItemNasaDataBinding):RecyclerView.ViewHolder(binding.root){
        fun setData(img : Image){
            binding.image = img
        }
    }

    override fun getItemCount(): Int {
        return imgList.size
    }
}