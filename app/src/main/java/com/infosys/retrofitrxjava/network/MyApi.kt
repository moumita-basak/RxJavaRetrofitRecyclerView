package com.infosys.retrofitrxjava.network

import com.google.gson.JsonObject
import com.infosys.retrofitrxjava.util.CommonConstants
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.concurrent.TimeUnit

interface MyApi {
@FormUrlEncoded
@POST("s/2iodh4vg0eortkl/facts.json")
suspend fun getItems(
    @FieldMap params: HashMap<String, String>
) : Response<JsonObject>

companion object{
    operator fun invoke(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): MyApi{
        val okkHttpClient = OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor).readTimeout(600, TimeUnit.SECONDS)
            .connectTimeout(600, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .client(okkHttpClient)
            .baseUrl(CommonConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

}
}