package com.tillylabs.star_wars_demo

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tillylabs.star_wars_demo.people.PersonDetailsActivity
import com.tillylabs.star_wars_demo.databinding.ActivityMainBinding
import com.tillylabs.star_wars_demo.people.PeopleDatabase
import com.tillylabs.star_wars_demo.people.PeopleRepo
import com.tillylabs.star_wars_demo.starship.StarshipDatabase
import com.tillylabs.star_wars_demo.starship.StarshipRepo
import com.tillylabs.star_wars_demo.vehicles.VehicleDatabase
import com.tillylabs.star_wars_demo.vehicles.VehicleRepo

class MainActivity : AppCompatActivity(), NameAdapter.ItemClickListener {

    lateinit var vm: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repo = PeopleRepo(PeopleDatabase.getInstance(this))
       val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        vm = ViewModelProviders.of(this, MainVM.FACTORY(repo))[MainVM::class.java].apply {
            init(this@MainActivity)
            binding.vm = this
        }
        vm.people.observe(this, Observer { list ->
            if(list != null){
                vm.refreshAdapter(list)
            }
        })
        //we only want to update the DB, not observe it yet
        VehicleRepo(VehicleDatabase.getInstance(this)).getVehicleListData()
        StarshipRepo(StarshipDatabase.getInstance(this)).getStarshipListData()
    }

    override fun onItemClicked(view: View, personName: String) {
        startPersonDetailsActivity(view, personName)
    }

    private fun startPersonDetailsActivity(view: View, personName: String){
        val intent = Intent(this, PersonDetailsActivity::class.java)
        intent.putExtra("person_name", personName)
        // Check if we're running on Android 5.0 or higher
        if (buildSdkAfter21()) {
            // Apply activity transition
            val options = ActivityOptions
                .makeSceneTransitionAnimation(this, view, getString(R.string.trans_name))
            // start the new activity
            startActivity(intent, options.toBundle())
        } else {
            // Swap without transition
            startActivity(intent)
        }
    }
}
