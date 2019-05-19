package com.tillylabs.star_wars_demo

import com.tillylabs.star_wars_demo.models.Person

class RowPersonVM(val person: Person) {

    fun getName(): String{
        return person.name
    }
}