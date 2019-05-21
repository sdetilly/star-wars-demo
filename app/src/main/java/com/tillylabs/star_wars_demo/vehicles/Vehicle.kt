package com.tillylabs.star_wars_demo.vehicles

import androidx.room.Entity

@Entity(tableName = "vehicle",
    primaryKeys = ["name"])
data class Vehicle(val cargo_capacity: String = "",
                  val consumables: String = "",
                  val cost_in_credits: String = "",
                  val created: String = "",
                  val crew: String = "",
                  val edited: String = "",
                  val length: String = "",
                  val manufacturer: String = "",
                  val max_atmosphering_speed: String = "",
                  val model: String = "",
                  val name: String = "",
                  val passengers: String = "",
                  val pilots: ArrayList<String> = ArrayList(),
                  val films: ArrayList<String> = ArrayList(),
                  val url: String = "",
                  val vehicle_class: String = "")

/*
{
    "cargo_capacity": "50000",
    "consumables": "2 months",
    "cost_in_credits": "150000",
    "created": "2014-12-10T15:36:25.724000Z",
    "crew": "46",
    "edited": "2014-12-10T15:36:25.724000Z",
    "length": "36.8",
    "manufacturer": "Corellia Mining Corporation",
    "max_atmosphering_speed": "30",
    "model": "Digger Crawler",
    "name": "Sand Crawler",
    "passengers": "30",
    "pilots": [],
    "films": [
        "https://swapi.co/api/films/1/"
    ],
    "url": "https://swapi.co/api/vehicles/4/",
    "vehicle_class": "wheeled"
}
 */