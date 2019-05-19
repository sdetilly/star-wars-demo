package com.tillylabs.star_wars_demo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tillylabs.star_wars_demo.models.Person

@Database(entities = [Person::class], version = 1)
@TypeConverters(StringArrayRoomConverter::class)
abstract class PeopleDatabase : RoomDatabase(){
    abstract fun peopleDao(): PeopleDao

    companion object {
        var mInstance: PeopleDatabase? = null
        @JvmStatic
        fun getInstance(ctx: Context): PeopleDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(ctx, PeopleDatabase::class.java, "person")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance!!
        }
    }
}