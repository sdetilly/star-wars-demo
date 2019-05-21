package com.tillylabs.star_wars_demo.vehicles

import androidx.lifecycle.LiveData
import com.tillylabs.star_wars_demo.network.Webservice
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class VehicleRepo(val db: VehicleDatabase) {

    private var peopleData: LiveData<List<Vehicle>>? = null

    fun getVehicleListData(): LiveData<List<Vehicle>> {
        if(peopleData == null){
            fetchVehicles()
            peopleData = db.vehicleDao().loadList()
        }
        return peopleData!!
    }

    fun getSpecificVehicleData(url: String): LiveData<Vehicle> {
        return db.vehicleDao().getVehicleData(url)
    }

    suspend fun getSpecificVehicle(url: String): Vehicle {
        return withContext(Dispatchers.IO){
            db.vehicleDao().getVehicle(url)
        }
    }

    private fun fetchVehicles(){
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Default) {
                try {
                    val list = Webservice.getVehicleList()
                    db.vehicleDao().insertOrUpdate(list)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}