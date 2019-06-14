package com.tillylabs.star_wars_demo.roomConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object StringArrayRoomConverter {

    @JvmStatic
    @TypeConverter
    fun stringToArrayList(value: String): ArrayList<String>{
        val listType = object : TypeToken<ArrayList<String>>() {

        }.type
        return Gson().fromJson(value, listType)
    }

    @JvmStatic
    @TypeConverter
    fun arrayListToString(list: ArrayList<String>): String?{
        val gson = Gson()
        return gson.toJson(list)
    }
}