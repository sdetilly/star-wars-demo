package com.tillylabs.star_wars_demo.starship

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tillylabs.star_wars_demo.R

class StarshipDetailsVM(val app: Application): AndroidViewModel(app) {

    lateinit var starship: Starship

    fun init(starship: Starship){
        this.starship = starship
    }

    fun getName(): String {
        return starship.name
    }

    fun getCargoCapacity(): String{
        return app.getString(R.string.vehicle_cargo, starship.cargo_capacity)
    }

    fun getStarshipClass(): String{
        return app.getString(R.string.vehicle_class, starship.starship_class)
    }

    fun getCost(): String{
        return app.getString(R.string.vehicle_cost, starship.cost_in_credits)
    }

    fun getCrew(): String{
        return app.getString(R.string.vehicle_crew, starship.crew)
    }

    fun getLength(): String{
        return app.getString(R.string.vehicle_length, starship.length)
    }

    fun getManufacturer(): String{
        return app.getString(R.string.vehicle_manufacturer, starship.manufacturer)
    }

    fun getModel(): String{
        return app.getString(R.string.vehicle_model, starship.model)
    }
}