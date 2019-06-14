package com.tillylabs.star_wars_demo.people

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tillylabs.star_wars_demo.R
import com.tillylabs.star_wars_demo.databinding.ActivityPersonDetailsBinding
import com.tillylabs.star_wars_demo.starship.Starship
import com.tillylabs.star_wars_demo.starship.StarshipDatabase
import com.tillylabs.star_wars_demo.starship.StarshipRepo
import com.tillylabs.star_wars_demo.vehicles.Vehicle
import com.tillylabs.star_wars_demo.vehicles.VehicleDatabase
import com.tillylabs.star_wars_demo.vehicles.VehicleRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PersonDetailsActivity : AppCompatActivity() {

    lateinit var vm: PersonDetailsVM
    lateinit var personLiveData: LiveData<Person>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityPersonDetailsBinding>(this, R.layout.activity_person_details)
        if(intent.hasExtra("person_name")){
            personLiveData = PeopleRepo(PeopleDatabase.getInstance(this)).getPersonData(intent.getStringExtra("person_name"))
            personLiveData.observe(this, Observer { person ->
                if(person != null) {
                    vm = ViewModelProviders.of(this)[PersonDetailsVM::class.java].apply {
                        init(person)
                    }
                    binding.vm = vm
                    observeVehicle(person.vehicles)
                    observeStarships(person.starships)
                }
            })
        }
    }

    fun observeVehicle(list: List<String>){
        if(list.isNotEmpty()) {
            val repo = VehicleRepo(VehicleDatabase.getInstance(this@PersonDetailsActivity))
            CoroutineScope(Dispatchers.Main).launch{
                vm.vehicle.set(getString(R.string.person_vehicle))
                val vehicleList = mutableListOf<Vehicle>()
                for (url in list) {
                    vehicleList.add(repo.getSpecificVehicle(url))
                }
                vm.vehicleList.set(vehicleList)
            }
        }else{
            vm.vehicle.set(getString(R.string.person_no_vehicle))
        }
    }

    fun observeStarships(list: List<String>){
        if(list.isNotEmpty()) {
            val repo = StarshipRepo(StarshipDatabase.getInstance(this@PersonDetailsActivity))
            CoroutineScope(Dispatchers.Main).launch{
                vm.starship.set(getString(R.string.person_starship))
                val starshipList = mutableListOf<Starship>()
                for (url in list) {
                    starshipList.add(repo.getSpecificStarship(url))
                }
                vm.starshipList.set(starshipList)
            }
        }else{
            vm.starship.set(getString(R.string.person_no_starship))
        }
    }
}