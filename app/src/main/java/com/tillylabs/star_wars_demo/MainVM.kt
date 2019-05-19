package com.tillylabs.star_wars_demo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class MainVM: ViewModel() {

    val uiAdapter = ObservableField(NameAdapter())

    fun init(activity: AppCompatActivity){
        val repo = Repo(PeopleDatabase.getInstance(activity.applicationContext))
        repo.getPeopleData().observe(activity, Observer{ list ->
            if(list != null){
                val adapter = uiAdapter.get()
                adapter?.submitList(list)
                uiAdapter.set(adapter)
            }
        })
    }
}