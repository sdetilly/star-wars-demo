package com.tillylabs.star_wars_demo.network

import com.tillylabs.star_wars_demo.people.Person
import com.tillylabs.star_wars_demo.vehicles.Vehicle
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    @GET("people/")
    fun fetchPeopleList(@Query("page") page: Int): Call<ListResponse<Person>>

    @GET("vehicles/")
    fun fetchVehicleList(@Query("page") page: Int): Call<ListResponse<Vehicle>>
}