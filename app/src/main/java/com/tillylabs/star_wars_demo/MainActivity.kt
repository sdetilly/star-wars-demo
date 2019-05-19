package com.tillylabs.star_wars_demo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tillylabs.star_wars_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var vm: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        vm = ViewModelProviders.of(this)[MainVM::class.java].apply {
            init(this@MainActivity)
            binding.vm = this
        }
    }
}
