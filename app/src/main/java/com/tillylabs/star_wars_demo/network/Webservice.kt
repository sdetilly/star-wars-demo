package com.tillylabs.star_wars_demo.network

import com.tillylabs.star_wars_demo.starship.Starship
import com.tillylabs.star_wars_demo.vehicles.Vehicle
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import com.tillylabs.star_wars_demo.network.Webservice.ObjectType.*

object Webservice {

    var BASE_URL = "https://swapi.co/api/"
    private var retrofit: Retrofit

    init {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            addInterceptor(logging)
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
            writeTimeout(30, TimeUnit.SECONDS)
        }
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .build()
    }

    val endpoint: Endpoints get() = retrofit.create(Endpoints::class.java)

    suspend fun <T>networkCall(type: ObjectType): List<T>{
        var i = 1
        var response: Response<ListResponse<T>>
        val list = mutableListOf<T>()
        val typePath = when(type){
            PERSON -> "people"
            VEHICLE -> "vehicles"
            STARSHIP -> "starships"
        }
        do{
            response = Webservice.endpoint.fetchGeneric(typePath, i)
            val tempList: List<T> = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
            list.addAll(tempList)
            i++
        } while(!response.body()?.next.isNullOrEmpty())
        return list
    }

    /*
    private suspend fun networkCall(): List<Person>{
        var i = 1
        var response: Response<ListResponse<Person>>
        val list = mutableListOf<Person>()
        do{
            response = Webservice.endpoint.fetchPeopleList(i)
            val tempList = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
            list.addAll(tempList)
            i++
        } while(!response.body()?.next.isNullOrEmpty())
        return list
    }
     */

    @JvmStatic
    @Throws(IOException::class)
    fun getVehicleList(): List<Vehicle>{
        var i = 1
        var response = endpoint.fetchVehicleList(i).execute()
        val list = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
        while(!response.body()?.next.isNullOrEmpty()){
            i++
            response = endpoint.fetchVehicleList(i).execute()
            val tempList = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
            list.addAll(tempList)
        }
        return list
    }

    @JvmStatic
    @Throws(IOException::class)
    fun getStarshipList(): List<Starship>{
        var i = 1
        var response = endpoint.fetchStarshipList(i).execute()
        val list = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
        while(!response.body()?.next.isNullOrEmpty()){
            i++
            response = endpoint.fetchStarshipList(i).execute()
            val tempList = response.body()?.results ?: throw IOException(response.errorBody()?.string() ?: "")
            list.addAll(tempList)
        }
        return list
    }

    enum class ObjectType{
        PERSON,
        STARSHIP,
        VEHICLE
    }
}