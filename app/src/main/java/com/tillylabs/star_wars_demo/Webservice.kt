package com.tillylabs.star_wars_demo

import com.tillylabs.star_wars_demo.models.Person
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object Webservice {

    var BASE_URL = "https://swapi.co/api/"
    var api: Endpoints

    init {
        val client = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
        api = retrofit.create(Endpoints::class.java)
    }

    @JvmStatic
    @Throws(IOException::class)
    fun getPeopleList(): List<Person>{
        val response = api.fetchPeopleList().execute()
        return response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
    }
}