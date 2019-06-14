package com.tillylabs.star_wars_demo

import android.widget.SearchView
import androidx.annotation.VisibleForTesting
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tillylabs.star_wars_demo.people.PeopleRepo
import com.tillylabs.star_wars_demo.people.Person
import kotlinx.coroutines.launch

class MainVM(private val repo: PeopleRepo): ViewModel(), SearchView.OnQueryTextListener {

    val uiAdapter = ObservableField(NameAdapter())
    private val fullNameSet = mutableSetOf<Person>()

    val people = repo.peopleData

    fun init(listener: NameAdapter.ItemClickListener){
        uiAdapter.get()?.listener = listener
        viewModelScope.launch { repo.fetchPeople() }
    }

    fun refreshAdapter(list: List<Person>){
        fullNameSet.addAll(list)
        val adapter = uiAdapter.get()
        adapter?.submitList(list)
        uiAdapter.set(adapter)
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

    companion object {
        /**
         * Factory for creating [MainVM]
         * @param arg the repository to pass to [MainVM]
         */
        val FACTORY = singleArgViewModelFactory(::MainVM)
    }
}