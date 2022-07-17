package com.example.nasapictures.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.nasapictures.NasaDetails.NasaDetailActivity
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ActivityNasaAllDataBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NasaAllDataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNasaAllDataBinding
    val nasaAllDataViewModel : NasaAllDataViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_nasa_all_data)

        nasaAllDataViewModel.getNasaData()
        nasaAllDataViewModel.nasaModelData.observe(this){
            binding.recyclerViewAllImg.adapter = NasaAllDataAdapter(this,it){ data->ClickHandler(data)

            }
        }
    }

    private fun ClickHandler(data: NasaAllDataItem) {
        startActivity(Intent(this,NasaDetailActivity::class.java)).apply {
            .putExtra
            //start from here
        }
    }
}