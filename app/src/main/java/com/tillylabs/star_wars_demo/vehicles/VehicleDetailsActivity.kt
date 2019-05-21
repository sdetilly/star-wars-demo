package com.tillylabs.star_wars_demo.vehicles

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tillylabs.star_wars_demo.R
import com.tillylabs.star_wars_demo.databinding.ActivityVehicleDetailsBinding

class VehicleDetailsActivity : AppCompatActivity() {

    lateinit var vm: VehicleDetailsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityVehicleDetailsBinding>(this, R.layout.activity_vehicle_details)
        if(intent.hasExtra("vehicle_url")){
            VehicleRepo(VehicleDatabase.getInstance(this)).getSpecificVehicleData(intent.getStringExtra("vehicle_url")).observe(this, Observer { vehicle ->
                if(vehicle != null) {
                    vm = ViewModelProviders.of(this)[VehicleDetailsVM::class.java].apply {
                        init(vehicle)
                    }
                    binding.vm = vm
                }
            })
        }
    }
}