package com.example.nasapictures.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class NasaAllDataModel(var list:ArrayList<NasaAllDataItem>)

@Parcelize
data class NasaAllDataItem(var copyright:String="",
                           var date:String="",
                           var explanation:String="",
                           var hdurl:String="",
                           var title:String="",
                           var url:String=""):Parcelable



