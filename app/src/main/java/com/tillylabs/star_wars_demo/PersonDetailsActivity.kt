package com.tillylabs.star_wars_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tillylabs.star_wars_demo.databinding.ActivityPersonDetailsBinding

class PersonDetailsActivity : AppCompatActivity() {

    lateinit var vm: PersonDetailsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityPersonDetailsBinding>(this, R.layout.activity_person_details)
        if(intent.hasExtra("person_name")){
            Repo(PeopleDatabase.getInstance(this)).getPersonData(intent.getStringExtra("person_name")).observe(this, Observer { person ->
                if(person != null) {
                    vm = ViewModelProviders.of(this)[PersonDetailsVM::class.java].apply {
                        init(person)
                    }
                    binding.vm = vm
                }
            })
        }
    }
}