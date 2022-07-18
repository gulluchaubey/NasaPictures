package com.example.nasapictures.home
import android.content.Context
import com.example.nasapictures.utils.Results
import com.example.nasapictures.utils.Status
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject


class NasaAllDataRepository @Inject constructor(private val mContext:Context) {
    suspend fun loadJSONFromAsset():Results<String> {
        val json: String?
        return try {
            val inputStream = mContext.assets.open("data.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
            Results(Status.SUCCESS,json,null)
        } catch (ex: IOException) {
            //ex.printStackTrace()
            Results(Status.ERROR,null,null)
        }
    }
}