package com.tillylabs.star_wars_demo.people

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tillylabs.star_wars_demo.roomConverters.PersonConverter
import com.tillylabs.star_wars_demo.roomConverters.StringArrayRoomConverter

@Database(entities = [Person::class], version = 1)
@TypeConverters(PersonConverter::class)
abstract class PeopleDatabase : RoomDatabase(){
    abstract fun peopleDao(): PeopleDao<Person>

    companion object {
        var mInstance: PeopleDatabase? = null
        @JvmStatic
        fun getInstance(ctx: Context): PeopleDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(ctx.applicationContext, PeopleDatabase::class.java, "person")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance!!
        }
    }
}