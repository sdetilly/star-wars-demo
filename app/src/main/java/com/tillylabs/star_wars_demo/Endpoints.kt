package com.tillylabs.star_wars_demo

import com.tillylabs.star_wars_demo.models.PersonListResponse
import retrofit2.Call
import retrofit2.http.GET

interface Endpoints {

    @GET("people/")
    fun fetchPeopleList(): Call<PersonListResponse>
}