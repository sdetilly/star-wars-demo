package com.tillylabs.star_wars_demo

import com.tillylabs.star_wars_demo.network.Webservice
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun fetchPeopleList() {
        val list = Webservice.getPeopleList()
        try{
            val person = list[0]
            assertEquals(true, person.name.isNotEmpty())
            println("PERSON NAME: ${person.name}")
        }catch (e: Exception){
            e.printStackTrace()
            assertEquals(true, false)
        }
    }

    @Test
    fun fetchVehicleList() {
        val list = Webservice.getVehicleList()
        try{
            val vehicle = list[0]
            assertEquals(true, vehicle.name.isNotEmpty())
            println("Vehicle NAME: ${vehicle.name}")
        }catch (e: Exception){
            e.printStackTrace()
            assertEquals(true, false)
        }
    }
}
