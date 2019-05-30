package com.tillylabs.star_wars_demo

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Paint
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.core.view.setPadding
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tillylabs.star_wars_demo.starship.Starship
import com.tillylabs.star_wars_demo.starship.StarshipDetailsActivity
import com.tillylabs.star_wars_demo.vehicles.Vehicle
import com.tillylabs.star_wars_demo.vehicles.VehicleDetailsActivity

@BindingAdapter("adapter")
fun <T : RecyclerView.ViewHolder> setRvAdapter(rv: RecyclerView, adapter: RecyclerView.Adapter<T>?) {
    if(adapter != null) rv.adapter = adapter
}

@BindingAdapter("queryTextListener")
fun queryTextListener(sv: SearchView, listener: SearchView.OnQueryTextListener?){
    if(listener != null){
        sv.setOnQueryTextListener(listener)
    }
}

@BindingAdapter("addVehicle")
fun addVehicleToLinearLayout(ll: LinearLayout, list: List<Vehicle>?){
    if(list != null){
        ll.removeAllViews()
        val ctx = ll.context
        for (vehicle in list) {
            val tv = TextView(ctx)
            tv.text = vehicle.name
            tv.setPadding(12)
            tv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            tv.setOnClickListener {
                val intent = Intent(ctx, VehicleDetailsActivity::class.java)
                intent.putExtra("vehicle_url", vehicle.url)
                ctx.startActivity(intent)
            }
            ll.addView(tv)
        }
    }
}

@BindingAdapter("addStarship")
fun addStarshipToLinearLayout(ll: LinearLayout, list: List<Starship>?){
    if(list != null){
        ll.removeAllViews()
        val ctx = ll.context
        for (starship in list) {
            val tv = TextView(ctx)
            tv.text = starship.name
            tv.setPadding(12)
            tv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            tv.setOnClickListener {
                val intent = Intent(ctx, StarshipDetailsActivity::class.java)
                intent.putExtra("starship_url", starship.url)
                ctx.startActivity(intent)
            }
            ll.addView(tv)
        }
    }
}