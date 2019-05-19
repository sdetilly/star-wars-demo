package com.tillylabs.star_wars_demo

import com.tillylabs.star_wars_demo.models.PersonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoints {

    @GET("people/")
    fun fetchPeopleList(@Query("page") page: Int): Call<PersonListResponse>
}