package com.tillylabs.star_wars_demo.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.tillylabs.star_wars_demo.network.Webservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeopleRepo(val db: PeopleDatabase) {

    val peopleData: LiveData<List<Person>> = db.peopleDao().loadList()

    fun getPersonData(name: String): LiveData<Person> {
        return db.peopleDao().getPerson(name)
    }

    suspend fun fetchPeople(){
        withContext(Dispatchers.IO) {
            try {
                val list = Webservice.getPeopleList()
                db.peopleDao().insertOrUpdate(list)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}