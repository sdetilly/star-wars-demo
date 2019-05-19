package com.tillylabs.star_wars_demo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tillylabs.star_wars_demo.models.Person

@Dao
interface PeopleDao {

    @Query("SELECT * FROM people")
    fun loadList(): LiveData<List<Person>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(people: List<Person>)
}