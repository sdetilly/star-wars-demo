package com.tillylabs.star_wars_demo.people

import androidx.lifecycle.LiveData
import com.tillylabs.star_wars_demo.network.Webservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PeopleRepo(val db: PeopleDatabase) {

    private var peopleData: LiveData<List<Person>>? = null

    fun getPeopleData(): LiveData<List<Person>> {
        if(peopleData == null){
            fetchPeople()
            peopleData = db.peopleDao().loadList()
        }
        return peopleData!!
    }

    fun getPersonData(name: String): LiveData<Person> {
        return db.peopleDao().getPerson(name)
    }

    private fun fetchPeople(){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Default) {
                try {
                    val list = Webservice.getPeopleList()
                    db.peopleDao().insertOrUpdate(list)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}