package com.example.nasapictures.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.json.JSONObject
import javax.inject.Inject

@HiltViewModel
class NasaAllDataViewModel @Inject constructor(private val nasaAllDataRepository: NasaAllDataRepository):ViewModel() {

    private var nasaAllDataItem :NasaAllDataItem = NasaAllDataItem()
    private var nasaAllDataItemList :ArrayList<NasaAllDataItem> =ArrayList()

    private var _nasaModelData : MutableLiveData<ArrayList<NasaAllDataItem>> = MutableLiveData()
    var nasaModelData : LiveData<ArrayList<NasaAllDataItem>> = _nasaModelData

    fun getNasaData()=viewModelScope.launch {
        var obj = JSONObject(nasaAllDataRepository.loadJSONFromAsset())
        val nasaArray = obj.getJSONArray("data")
        for (i in 0 until nasaArray.length()-1){
            var nasaDetail = nasaArray.getJSONObject(i)

            nasaAllDataItem.copyright = nasaDetail.getString("copyright")
            nasaAllDataItem.date = nasaDetail.getString("date")
            nasaAllDataItem.explanation = nasaDetail.getString("explanation")
            nasaAllDataItem.title = nasaDetail.getString("title")
            nasaAllDataItem.hdurl = nasaDetail.getString("hdurl")
            nasaAllDataItem.url = nasaDetail.getString("url")
            nasaAllDataItemList.add(nasaAllDataItem)
        }
        _nasaModelData.postValue(nasaAllDataItemList)
    }
}