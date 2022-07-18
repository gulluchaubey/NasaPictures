package com.example.nasapictures.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.nasapictures.nasaDetails.NasaDetailActivity
import com.example.nasapictures.R
import com.example.nasapictures.databinding.ActivityNasaAllDataBinding
import com.example.nasapictures.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class NasaAllDataActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNasaAllDataBinding
    private val nasaAllDataViewModel : NasaAllDataViewModel by viewModels()
    private lateinit var nasaAllDataAdapter:NasaAllDataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_nasa_all_data)


        setContentView(binding.root)
        nasaAllDataViewModel.getNasaData()
            nasaAllDataViewModel.nasaModelData.observe(this@NasaAllDataActivity){
                when(it.status){
                    Status.LOADING->{
                        binding.progressBar.visibility=View.VISIBLE
                    }
                    Status.ERROR->{
                        binding.progressBar.visibility=View.GONE
                    }
                    Status.SUCCESS->{
                        nasaAllDataAdapter = NasaAllDataAdapter(this@NasaAllDataActivity, it.data!!){ data->
                            ClickHandler(data)
                        }
                        binding.recyclerViewAllImg.adapter = nasaAllDataAdapter
                        binding.progressBar.visibility=View.GONE
                    }
                }
            }
    }

    private fun ClickHandler(data: NasaAllDataItem) {
        startActivity(Intent(this,NasaDetailActivity::class.java).apply {
            putExtra("data",data)
        })
    }
}