package com.tillylabs.star_wars_demo.vehicles

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.tillylabs.star_wars_demo.roomConverters.StringArrayRoomConverter

@Database(entities = [Vehicle::class], version = 1)
@TypeConverters(StringArrayRoomConverter::class)
abstract class VehicleDatabase : RoomDatabase(){
    abstract fun vehicleDao(): VehicleDao

    companion object {
        var mInstance: VehicleDatabase? = null
        @JvmStatic
        fun getInstance(ctx: Context): VehicleDatabase {
            if (mInstance == null) {
                mInstance = Room.databaseBuilder(ctx.applicationContext, VehicleDatabase::class.java, "vehicle")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return mInstance!!
        }
    }
}