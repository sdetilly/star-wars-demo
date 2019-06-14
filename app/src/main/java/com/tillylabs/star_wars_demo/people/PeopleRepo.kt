package com.tillylabs.star_wars_demo.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tillylabs.star_wars_demo.network.ListResponse
import com.tillylabs.star_wars_demo.network.Webservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

class PeopleRepo(val db: PeopleDatabase) {

    val peopleData: LiveData<List<Person>> = db.peopleDao().loadList()

    fun getPersonData(name: String): LiveData<Person> {
        return db.peopleDao().getPerson(name)
    }

    suspend fun fetchPeople(){
        withContext(Dispatchers.IO) {
            try {
                val list = Webservice.networkCall<Person>(Webservice.ObjectType.PERSON)
                //val list = networkCall()
                val dbList = ArrayList(list)
                db.peopleDao().insertOrUpdate(dbList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

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
}