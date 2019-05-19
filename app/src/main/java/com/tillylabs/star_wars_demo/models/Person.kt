package com.tillylabs.star_wars_demo.models

import androidx.room.Entity

@Entity(tableName = "people",
    primaryKeys = ["name"])
data class Person(val birth_year: String = "",
                  val eye_color: String = "",
                  val films: ArrayList<String> = ArrayList(),
                  val gender: String = "",
                  val hair_color: String = "",
                  val height: String = "",
                  val homeworld: String = "",
                  val mass: String = "",
                  val name: String = "",
                  val skin_color: String = "",
                  val created: String = "",
                  val edited: String = "",
                  val species: ArrayList<String> = ArrayList(),
                  val starships: ArrayList<String> = ArrayList(),
                  val url: String = "",
                  val vehicles: ArrayList<String> = ArrayList())

data class PersonListResponse(val count: Int = 1,
                              val next: String = "",
                              val previous: String? = "",
                              val results: ArrayList<Person> = ArrayList()
)

/*
{
    "birth_year": "19 BBY",
    "eye_color": "Blue",
    "films": [
        "https://swapi.co/api/films/1/",
        ...
    ],
    "gender": "Male",
    "hair_color": "Blond",
    "height": "172",
    "homeworld": "https://swapi.co/api/planets/1/",
    "mass": "77",
    "name": "Luke Skywalker",
    "skin_color": "Fair",
    "created": "2014-12-09T13:50:51.644000Z",
    "edited": "2014-12-10T13:52:43.172000Z",
    "species": [
        "https://swapi.co/api/species/1/"
    ],
    "starships": [
        "https://swapi.co/api/starships/12/",
        ...
    ],
    "url": "https://swapi.co/api/people/1/",
    "vehicles": [
        "https://swapi.co/api/vehicles/14/"
        ...
    ]
}
 */