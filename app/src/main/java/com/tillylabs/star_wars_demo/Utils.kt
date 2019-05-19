package com.tillylabs.star_wars_demo

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun <T : RecyclerView.ViewHolder> setRvAdapter(rv: RecyclerView, adapter: RecyclerView.Adapter<T>?) {
    if(adapter != null) rv.adapter = adapter
}