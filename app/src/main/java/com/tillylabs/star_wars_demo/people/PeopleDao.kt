package com.tillylabs.star_wars_demo.people

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PeopleDao<T> {

    @Query("SELECT * FROM people")
    fun loadList(): LiveData<List<T>>

    @Query("SELECT * FROM people WHERE name = :name")
    fun getPerson(name: String): LiveData<T>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrUpdate(people: List<T>)
}