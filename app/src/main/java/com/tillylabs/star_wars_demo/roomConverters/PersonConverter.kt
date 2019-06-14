package com.tillylabs.star_wars_demo.roomConverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tillylabs.star_wars_demo.people.Person

object PersonConverter {

    @JvmStatic
    @TypeConverter
    fun personToArrayList(value: String): ArrayList<Person>{
        val listType = object : TypeToken<ArrayList<Person>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @JvmStatic
    @TypeConverter
    fun arrayListToperson(list: ArrayList<Person>): String?{
        val listType = object : TypeToken<ArrayList<Person>>() {}.type
        return Gson().toJson(list, listType)
    }

    @JvmStatic
    @TypeConverter
    fun stringToArrayList(value: String): ArrayList<String>{
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @JvmStatic
    @TypeConverter
    fun arrayListToString(list: ArrayList<String>): String?{
        val gson = Gson()
        return gson.toJson(list)
    }
}

/*
Type fooType = new TypeToken<Foo<Bar>>() {}.getType();
gson.toJson(foo, fooType);

gson.fromJson(json, fooType);
 */