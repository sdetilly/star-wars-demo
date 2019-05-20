package com.tillylabs.star_wars_demo

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tillylabs.star_wars_demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NameAdapter.ItemClickListener {

    lateinit var vm: MainVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        vm = ViewModelProviders.of(this)[MainVM::class.java].apply {
            init(this@MainActivity, this@MainActivity)
            binding.vm = this
        }
    }

    override fun onItemClicked(view: View, personName: String) {
        startPersonDetailsActivity(view, personName)
    }

    fun startPersonDetailsActivity(view: View, personName: String){
        val intent = Intent(this, PersonDetailsActivity::class.java)
        intent.putExtra("person_name", personName)
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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
