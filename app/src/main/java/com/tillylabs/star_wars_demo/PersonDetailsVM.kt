package com.tillylabs.star_wars_demo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tillylabs.star_wars_demo.models.Person

class PersonDetailsVM(val app: Application): AndroidViewModel(app) {

    lateinit var person: Person

    fun init(person: Person){
        this.person = person
    }

    fun getName(): String {
        return person.name
    }

    fun getGender(): String{
        return app.getString(R.string.person_gender, person.gender)
    }

    fun getHeight(): String{
        return app.getString(R.string.person_height, person.height)
    }

    fun getBirthYear(): String{
        return app.getString(R.string.person_birth, person.birth_year)
    }

    fun getHairColor(): String{
        return app.getString(R.string.person_hair, person.hair_color)
    }

    fun getMass(): String{
        return app.getString(R.string.person_mass, person.mass)
    }

    fun getSkinColor(): String{
        return app.getString(R.string.person_skin, person.skin_color)
    }

    fun getEyeColor(): String{
        return app.getString(R.string.person_eye, person.eye_color)
    }
}