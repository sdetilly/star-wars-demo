package com.tillylabs.star_wars_demo.people

import android.app.Application
import com.tillylabs.star_wars_demo.R
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class PersonDetailsVMTest {

    lateinit var vm: PersonDetailsVM
    lateinit var person: Person

    @Mock
    lateinit var app: Application

    val name = "Phil"
    val gender = "Male"
    val height = "180"
    val birth = "1000"
    val hair = "Red"
    val mass = "200"
    val skin = "Blue"
    val eyes = "Brown"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        person = Person(name = name, gender = gender, height = height, birth_year = birth, hair_color = hair,
            mass = mass, skin_color = skin, eye_color = eyes)
        vm = PersonDetailsVM(app)
        vm.init(person)
        stringMocker()
    }

    @Test
    fun getName() {
        assertEquals(name, vm.getName())
    }

    @Test
    fun getGender() {
        assertEquals(genderString, vm.getGender())
    }

    @Test
    fun getHeight() {
        assertEquals(heightString, vm.getHeight())
    }

    @Test
    fun getBirthYear() {
        assertEquals(birthString, vm.getBirthYear())
    }

    @Test
    fun getHairColor() {
        assertEquals(hairString, vm.getHairColor())
    }

    @Test
    fun getMass() {
        assertEquals(massString, vm.getMass())
    }

    @Test
    fun getSkinColor() {
        assertEquals(skinString, vm.getSkinColor())
    }

    @Test
    fun getEyeColor() {
        assertEquals(eyeString, vm.getEyeColor())
    }

    val genderString = "Gender: $gender"
    val heightString = "Height: $height"
    val birthString = "Birth year: $birth"
    val hairString = "Hair color: $hair"
    val massString = "Mass: $mass"
    val skinString = "Skin color: $skin"
    val eyeString = "Eye color: $eyes"


    private fun stringMocker(){
        Mockito.`when`(app.getString(R.string.person_gender, person.gender)).thenReturn(genderString)
        Mockito.`when`(app.getString(R.string.person_height, person.height)).thenReturn(heightString)
        Mockito.`when`(app.getString(R.string.person_birth, person.birth_year)).thenReturn(birthString)
        Mockito.`when`(app.getString(R.string.person_hair, person.hair_color)).thenReturn(hairString)
        Mockito.`when`(app.getString(R.string.person_mass, person.mass)).thenReturn(massString)
        Mockito.`when`(app.getString(R.string.person_skin, person.skin_color)).thenReturn(skinString)
        Mockito.`when`(app.getString(R.string.person_eye, person.eye_color)).thenReturn(eyeString)
    }
}