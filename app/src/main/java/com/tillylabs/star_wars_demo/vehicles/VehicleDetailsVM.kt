package com.tillylabs.star_wars_demo.vehicles

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.tillylabs.star_wars_demo.R

class VehicleDetailsVM(val app: Application): AndroidViewModel(app) {

    lateinit var vehicle: Vehicle

    fun init(person: Vehicle){
        this.vehicle = person
    }

    fun getName(): String {
        return vehicle.name
    }

    fun getCargoCapacity(): String{
        return app.getString(R.string.vehicle_cargo, vehicle.cargo_capacity)
    }

    fun getVehicleClass(): String{
        return app.getString(R.string.vehicle_class, vehicle.vehicle_class)
    }

    fun getCost(): String{
        return app.getString(R.string.vehicle_cost, vehicle.cost_in_credits)
    }

    fun getCrew(): String{
        return app.getString(R.string.vehicle_crew, vehicle.crew)
    }

    fun getLength(): String{
        return app.getString(R.string.vehicle_length, vehicle.length)
    }

    fun getManufacturer(): String{
        return app.getString(R.string.vehicle_manufacturer, vehicle.manufacturer)
    }

    fun getModel(): String{
        return app.getString(R.string.vehicle_model, vehicle.model)
    }
}