package com.example.nasapictures.home
import android.content.Context
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject


class NasaAllDataRepository @Inject constructor(private val mContext:Context) {
    suspend fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = mContext.assets.open("datajson.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}