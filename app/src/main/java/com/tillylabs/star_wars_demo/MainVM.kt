package com.tillylabs.star_wars_demo

import android.widget.SearchView
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.tillylabs.star_wars_demo.people.PeopleDatabase
import com.tillylabs.star_wars_demo.people.PeopleRepo
import com.tillylabs.star_wars_demo.people.Person
import com.tillylabs.star_wars_demo.vehicles.VehicleDatabase
import com.tillylabs.star_wars_demo.vehicles.VehicleRepo

class MainVM: ViewModel(), SearchView.OnQueryTextListener {

    val uiAdapter = ObservableField(NameAdapter())
    val fullNameSet = mutableSetOf<Person>()


    fun init(activity: AppCompatActivity, listener: NameAdapter.ItemClickListener){
        val repo = PeopleRepo(
            PeopleDatabase.getInstance(activity.applicationContext)
        )
        repo.getPeopleData().observe(activity, Observer{ list ->
            if(list != null){
                fullNameSet.addAll(list)
                val adapter = uiAdapter.get()
                adapter?.listener = listener
                adapter?.submitList(list)
                uiAdapter.set(adapter)
            }
        })
        //we only want to update the DB, not observe it yet
        VehicleRepo(VehicleDatabase.getInstance(activity.applicationContext)).getVehicleListData()
    }

    fun queryListener(): SearchView.OnQueryTextListener{
        return this
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        val adapter = uiAdapter.get()
        if(adapter != null && !newText.isNullOrEmpty()){
            val list = filterWithString(newText)
            adapter.submitList(list)
        }else adapter?.submitList(fullNameSet.toList())
        uiAdapter.set(adapter)

        return false
    }

    @VisibleForTesting
    fun filterWithString(string: String): List<Person>{
        return fullNameSet.filter { it.name.toLowerCase().contains(string.toLowerCase()) }
    }
}