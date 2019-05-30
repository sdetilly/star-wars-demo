package com.tillylabs.star_wars_demo.starship

import androidx.lifecycle.LiveData
import com.tillylabs.star_wars_demo.network.Webservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class StarshipRepo(val db: StarshipDatabase) {

    private var starshipData: LiveData<List<Starship>>? = null

    fun getStarshipListData(): LiveData<List<Starship>> {
        if(starshipData == null){
            fetchStarships()
            starshipData = db.starshipDao().loadList()
        }
        return starshipData!!
    }

    fun getSpecificStarshipData(url: String): LiveData<Starship> {
        return db.starshipDao().getStarshipData(url)
    }

    suspend fun getSpecificStarship(url: String): Starship {
        return withContext(Dispatchers.IO){
            db.starshipDao().getStarship(url)
        }
    }

    private fun fetchStarships(){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Default) {
                try {
                    val list = Webservice.getStarshipList()
                    db.starshipDao().insertOrUpdate(list)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}