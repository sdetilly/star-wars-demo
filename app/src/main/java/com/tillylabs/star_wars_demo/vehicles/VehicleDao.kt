package com.tillylabs.star_wars_demo.vehicles

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface VehicleDao {

    @Query("SELECT * FROM vehicle")
    fun loadList(): LiveData<List<Vehicle>>

    @Query("SELECT * FROM vehicle WHERE url = :url")
    fun getVehicleData(url: String): LiveData<Vehicle>

    @Query("SELECT * FROM vehicle WHERE url = :url")
    fun getVehicle(url: String): Vehicle

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(people: List<Vehicle>)
}