package com.tillylabs.star_wars_demo.people

import android.view.View
import com.tillylabs.star_wars_demo.NameAdapter

class RowPersonVM(private val person: Person, private val listener: NameAdapter.ItemClickListener? = null) {

    fun getName(): String{
        return person.name
    }

    fun onItemClick(): View.OnClickListener{
        return View.OnClickListener { view ->
            listener?.onItemClicked(view, person.name)
        }
    }
}