package com.example.nasapictures.NasaDetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ActivityNasaDetailBinding

class NasaDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNasaDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_nasa_detail)
    }
}