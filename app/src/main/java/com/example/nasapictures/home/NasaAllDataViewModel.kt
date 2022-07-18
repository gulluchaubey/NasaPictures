package com.example.nasapictures.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nasapictures.utils.Results
import com.example.nasapictures.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class NasaAllDataViewModel @Inject constructor(private val nasaAllDataRepository: NasaAllDataRepository):ViewModel() {
    private var nasaAllDataItemList :ArrayList<NasaAllDataItem> = ArrayList()

    private var _nasaModelData : MutableLiveData<Results<ArrayList<NasaAllDataItem>>> = MutableLiveData()
    var nasaModelData : LiveData<Results<ArrayList<NasaAllDataItem>>> = _nasaModelData

    fun getNasaData()=viewModelScope.launch {
        _nasaModelData.postValue(Results(Status.LOADING,null,null))
        val obj = JSONObject(nasaAllDataRepository.loadJSONFromAsset().data!!)
        val nasaArray = obj.getJSONArray("data")
        for (i in 0 until nasaArray.length()-1){
            val nasaDetail = nasaArray.getJSONObject(i)
           val nasaAllDataItem :NasaAllDataItem = NasaAllDataItem()

            if(nasaDetail.has("copyright"))
            nasaAllDataItem.copyright = nasaDetail.getString("copyright")
            nasaAllDataItem.date = nasaDetail.getString("date")
            nasaAllDataItem.explanation = nasaDetail.getString("explanation")
            nasaAllDataItem.title = nasaDetail.getString("title")
            nasaAllDataItem.hdurl = nasaDetail.getString("hdurl")
            nasaAllDataItem.url = nasaDetail.getString("url")
            nasaAllDataItemList.add(nasaAllDataItem)
        }
        _nasaModelData.postValue(Results(nasaAllDataRepository.loadJSONFromAsset().status,nasaAllDataItemList,null))
    }
}