package com.tillylabs.star_wars_demo

import android.content.Context
import android.widget.SearchView
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.tillylabs.star_wars_demo.models.Person

class MainVM: ViewModel(), SearchView.OnQueryTextListener {

    val uiAdapter = ObservableField(NameAdapter())
    val fullNameSet = mutableSetOf<Person>()


    fun init(activity: AppCompatActivity, listener: NameAdapter.ItemClickListener){
        val repo = Repo(PeopleDatabase.getInstance(activity.applicationContext))
        repo.getPeopleData().observe(activity, Observer{ list ->
            if(list != null){
                fullNameSet.addAll(list)
                val adapter = uiAdapter.get()
                adapter?.listener = listener
                adapter?.submitList(list)
                uiAdapter.set(adapter)
            }
        })
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