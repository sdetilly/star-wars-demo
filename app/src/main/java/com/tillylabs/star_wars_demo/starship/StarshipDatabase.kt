package com.tillylabs.star_wars_demo.starship

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tillylabs.star_wars_demo.StringArrayRoomConverter

@Database(entities = [Starship::class], version = 1)
@TypeConverters(StringArrayRoomConverter::class)
abstract class StarshipDatabase : RoomDatabase(){
    abstract fun starshipDao(): StarshipDao

    companion object {
        var mInstance: StarshipDatabase? = null
        @JvmStatic
        fun getInstance(ctx: Context): StarshipDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(ctx.applicationContext, StarshipDatabase::class.java, "starship")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance!!
        }
    }
}