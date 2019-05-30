package com.tillylabs.star_wars_demo.starship

import androidx.room.Entity

@Entity(tableName = "starship",
    primaryKeys = ["name"])
data class Starship(val MGLT: String = "",
                    val cargo_capacity: String = "",
                    val consumables: String = "",
                    val cost_in_credits: String = "",
                    val created: String = "",
                    val crew: String = "",
                    val edited: String = "",
                    val hyperdrive_rating: String = "",
                    val length: String = "",
                    val manufacturer: String = "",
                    val max_atmosphering_speed: String = "",
                    val model: String = "",
                    val name: String = "",
                    val passengers: String = "",
                    val films: ArrayList<String> = ArrayList(),
                    val pilots: ArrayList<String> = ArrayList(),
                    val starship_class: String = "",
                    val url: String = "")

/*
{
    "MGLT": "10 MGLT",
    "cargo_capacity": "1000000000000",
    "consumables": "3 years",
    "cost_in_credits": "1000000000000",
    "created": "2014-12-10T16:36:50.509000Z",
    "crew": "342953",
    "edited": "2014-12-10T16:36:50.509000Z",
    "hyperdrive_rating": "4.0",
    "length": "120000",
    "manufacturer": "Imperial Department of Military Research, Sienar Fleet Systems",
    "max_atmosphering_speed": "n/a",
    "model": "DS-1 Orbital Battle Station",
    "name": "Death Star",
    "passengers": "843342",
    "films": [
        "https://swapi.co/api/films/1/"
    ],
    "pilots": [],
    "starship_class": "Deep Space Mobile Battlestation",
    "url": "https://swapi.co/api/starships/9/"
}
 */