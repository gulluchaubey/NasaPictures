package com.example.nasapictures.nasaDetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ActivityNasaDetailBinding
import com.example.nasapictures.home.NasaAllDataItem
import java.security.AccessController.getContext


class NasaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNasaDetailBinding
    private var nasaData: NasaAllDataItem=NasaAllDataItem()
    var imgList:ArrayList<Image> = ArrayList()
    private var image : Image= Image()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_nasa_detail)


        nasaData = intent.getParcelableExtra("data")!!
        image.imgUrl=nasaData.url
        imgList.add(image)
        image.imgUrl=nasaData.hdurl
        imgList.add(image)
        binding.nasaDetail=nasaData
        binding.toolbar.tvToolbarTitle.text=nasaData.title
        binding.toolbar.ivBack.setOnClickListener {
            onBackPressed()
        }
        binding.recyclerViewImgDetail.adapter=NasaDetailImageAdapter(this,imgList)


    }
}