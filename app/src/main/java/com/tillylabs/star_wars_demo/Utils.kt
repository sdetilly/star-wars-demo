package com.tillylabs.star_wars_demo

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Paint
import android.os.Build
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.setPadding
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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
        val ctx = ll.context as AppCompatActivity //Views get their context from their parent activity
        for (vehicle in list) {
            val tv = TextView(ctx)
            tv.text = vehicle.name
            tv.setPadding(12)
            tv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            tv.setOnClickListener {
                val intent = Intent(ctx, VehicleDetailsActivity::class.java)
                intent.putExtra("vehicle_url", vehicle.url)
                if (buildSdkAfter21()){
                    // Apply activity transition
                    val options = ActivityOptions
                        .makeSceneTransitionAnimation(ctx, tv, ctx.getString(R.string.trans_vehicle_name))
                    // start the new activity
                    ctx.startActivity(intent, options.toBundle())
                    //ctx.startActivity(intent)
                }else ctx.startActivity(intent)
            }
            if(buildSdkAfter21()) tv.transitionName = ctx.getString(R.string.trans_vehicle_name)
            ll.addView(tv)
        }
    }
}

@BindingAdapter("addStarship")
fun addStarshipToLinearLayout(ll: LinearLayout, list: List<Starship>?){
    if(list != null){
        ll.removeAllViews()
        val ctx = ll.context as AppCompatActivity //Views get their context from their parent activity
        for (starship in list) {
            val tv = TextView(ctx)
            tv.text = starship.name
            tv.setPadding(12)
            tv.paintFlags = Paint.UNDERLINE_TEXT_FLAG
            tv.setOnClickListener {
                val intent = Intent(ctx, StarshipDetailsActivity::class.java)
                intent.putExtra("starship_url", starship.url)
                if (buildSdkAfter21()){
                    // Apply activity transition
                    val options = ActivityOptions
                        .makeSceneTransitionAnimation(ctx, tv, ctx.getString(R.string.trans_starship_name))
                    // start the new activity
                    ctx.startActivity(intent, options.toBundle())
                    //ctx.startActivity(intent)
                }else ctx.startActivity(intent)
            }
            if(buildSdkAfter21()) tv.transitionName = ctx.getString(R.string.trans_starship_name)
            ll.addView(tv)
        }
    }
}

fun buildSdkAfter21(): Boolean{
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP
}

/**
 * Convenience factory to handle ViewModels with one parameter.
 *
 * Make a factory:
 * ```
 * // Make a factory
 * val FACTORY = viewModelFactory(::MyViewModel)
 * ```
 *
 * Use the generated factory:
 * ```
 * ViewModelProviders.of(this, FACTORY(argument))
 *
 * ```
 *
 * @param constructor A function (A) -> T that returns an instance of the ViewModel (typically pass
 * the constructor)
 * @return a function of one argument that returns ViewModelProvider.Factory for ViewModelProviders
 */
fun <T : ViewModel, A> singleArgViewModelFactory(constructor: (A) -> T):
            (A) -> ViewModelProvider.NewInstanceFactory {
    return { arg: A ->
        object : ViewModelProvider.NewInstanceFactory() {
            @Suppress("UNCHECKED_CAST")
            override fun <V : ViewModel> create(modelClass: Class<V>): V {
                return constructor(arg) as V
            }
        }
    }
}