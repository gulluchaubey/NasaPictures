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
    private var nasaAllDataItemList: ArrayList<NasaAllDataItem> = ArrayList()

    private var _nasaModelData: MutableLiveData<Results<ArrayList<NasaAllDataItem>>> =
        MutableLiveData()
    var nasaModelData: LiveData<Results<ArrayList<NasaAllDataItem>>> = _nasaModelData

    fun getNasaData()=viewModelScope.launch {
        _nasaModelData.value=Results(Status.LOADING,null,null)
        _nasaModelData.postValue(Results(nasaAllDataRepository.loadJSONFromAsset().status,nasaAllDataRepository.loadJSONFromAsset().data,null))
    }
    /*fun getNasaData() {
        viewModelScope.launch {
            _nasaModelData.value = Results(Status.LOADING, null, null)
            //_nasaModelData.value(Results(Status.LOADING,null,null))
            _nasaModelData.postValue(
                Results(
                    nasaAllDataRepository.loadJSONFromAsset().status,
                    nasaAllDataRepository.loadJSONFromAsset().data,
                    null
                )
            )
        }
    }*/
}