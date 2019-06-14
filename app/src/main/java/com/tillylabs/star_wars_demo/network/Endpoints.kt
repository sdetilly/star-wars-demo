package com.tillylabs.star_wars_demo.network

import com.tillylabs.star_wars_demo.people.Person
import com.tillylabs.star_wars_demo.starship.Starship
import com.tillylabs.star_wars_demo.vehicles.Vehicle
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoints {

    @GET("people/")
    suspend fun fetchPeopleList(@Query("page") page: Int): Response<ListResponse<Person>>

    @GET("vehicles/")
    fun fetchVehicleList(@Query("page") page: Int): Call<ListResponse<Vehicle>>

    @GET("starships/")
    fun fetchStarshipList(@Query("page") page: Int): Call<ListResponse<Starship>>

    @GET("{type}/")
    suspend fun <T> fetchGeneric(@Path("type") type: String,
                                @Query("page") page: Int): Response<ListResponse<T>>
}