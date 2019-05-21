package com.tillylabs.star_wars_demo.people

import android.view.View
import com.tillylabs.star_wars_demo.NameAdapter

class RowPersonVM(val person: Person, val listener: NameAdapter.ItemClickListener? = null) {

    fun getName(): String{
        return person.name
    }

    fun onItemClick(): View.OnClickListener{
        return View.OnClickListener { view ->
            listener?.onItemClicked(view, person.name)
        }
    }
}