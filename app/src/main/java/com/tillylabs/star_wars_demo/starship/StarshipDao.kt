package com.tillylabs.star_wars_demo.starship

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface StarshipDao {

    @Query("SELECT * FROM starship")
    fun loadList(): LiveData<List<Starship>>

    @Query("SELECT * FROM starship WHERE url = :url")
    fun getStarshipData(url: String): LiveData<Starship>

    @Query("SELECT * FROM starship WHERE url = :url")
    fun getStarship(url: String): Starship

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(starship: List<Starship>)
}