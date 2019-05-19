package com.tillylabs.star_wars_demo

import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun fetchList() {
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
}
