package com.tillylabs.star_wars_demo.network

import com.tillylabs.star_wars_demo.people.Person
import com.tillylabs.star_wars_demo.starship.Starship
import com.tillylabs.star_wars_demo.vehicles.Vehicle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

object Webservice {

    var BASE_URL = "https://swapi.co/api/"
    var api: Endpoints

    init {
        val client = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(logging)
        client.connectTimeout(30, TimeUnit.SECONDS)
        client.readTimeout(30, TimeUnit.SECONDS)
        client.writeTimeout(30, TimeUnit.SECONDS)
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
        var i = 1
        var response = api.fetchPeopleList(i).execute()
        val list = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
        while(!response.body()?.next.isNullOrEmpty()){
            i++
            response = api.fetchPeopleList(i).execute()
            val tempList = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
            list.addAll(tempList)
        }
        return list
    }

    @JvmStatic
    @Throws(IOException::class)
    fun getVehicleList(): List<Vehicle>{
        var i = 1
        var response = api.fetchVehicleList(i).execute()
        val list = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
        while(!response.body()?.next.isNullOrEmpty()){
            i++
            response = api.fetchVehicleList(i).execute()
            val tempList = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
            list.addAll(tempList)
        }
        return list
    }

    @JvmStatic
    @Throws(IOException::class)
    fun getStarshipList(): List<Starship>{
        var i = 1
        var response = api.fetchStarshipList(i).execute()
        val list = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
        while(!response.body()?.next.isNullOrEmpty()){
            i++
            response = api.fetchStarshipList(i).execute()
            val tempList = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
            list.addAll(tempList)
        }
        return list
    }
}