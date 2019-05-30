package com.tillylabs.star_wars_demo.starship

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.tillylabs.star_wars_demo.R
import com.tillylabs.star_wars_demo.databinding.*

class StarshipDetailsActivity : AppCompatActivity() {

    lateinit var vm: StarshipDetailsVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityStarshipDetailsBinding>(this, R.layout.activity_starship_details)
        if(intent.hasExtra("starship_url")){
            StarshipRepo(StarshipDatabase.getInstance(this)).getSpecificStarshipData(intent.getStringExtra("starship_url")).observe(this, Observer { starship ->
                if(starship != null) {
                    vm = ViewModelProviders.of(this)[StarshipDetailsVM::class.java].apply {
                        init(starship)
                    }
                    binding.vm = vm
                }
            })
        }
    }
}