package com.example.nasapictures.home
import android.content.Context
import com.example.nasapictures.utils.Results
import com.example.nasapictures.utils.Status
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import javax.inject.Inject


class NasaAllDataRepository @Inject constructor(private val mContext:Context) {
    suspend fun loadJSONFromAsset():Results<ArrayList<NasaAllDataItem>> {
        val json: String?
        return try {
            val inputStream = mContext.assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)


            val nasaAllDataItemList :ArrayList<NasaAllDataItem> = ArrayList()
            val obj = JSONObject(json)
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
            Results(Status.SUCCESS,nasaAllDataItemList,null)
        } catch (ex: IOException) {
            //ex.printStackTrace()
            Results(Status.ERROR,null,null)
        }
    }
}