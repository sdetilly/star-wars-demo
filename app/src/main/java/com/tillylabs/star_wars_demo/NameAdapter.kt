package com.tillylabs.star_wars_demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tillylabs.star_wars_demo.NameAdapter.PersonViewHolder.Companion.DIFF
import com.tillylabs.star_wars_demo.people.RowPersonVM
import com.tillylabs.star_wars_demo.people.Person
import com.tillylabs.star_wars_demo.databinding.*

class NameAdapter : ListAdapter<Person, NameAdapter.PersonViewHolder>(DIFF) {

    var listener: ItemClickListener? = null

    interface ItemClickListener{
        fun onItemClicked(view: View, personName: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        val binding = DataBindingUtil.inflate<RowPersonBinding>(LayoutInflater.from(parent.context), R.layout.row_person, parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
    }

    class PersonViewHolder(itemView: RowPersonBinding): RecyclerView.ViewHolder(itemView.root){
        private val mViewDataBinding: RowPersonBinding = itemView

        fun bind(item: Person, listener: ItemClickListener?){
            val viewModel = RowPersonVM(item, listener)
            mViewDataBinding.vm = viewModel
            mViewDataBinding.executePendingBindings()
        }

        companion object {
            val DIFF = object : DiffUtil.ItemCallback<Person>(){
                override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                    return oldItem.name == newItem.name
                }

                override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                    return oldItem == newItem
                }
            }
        }
    }
}