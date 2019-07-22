package com.example.practice2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_recycler_view.view.*

class BreweryAdapter(private val brewerylist: MutableList<Brewery>) : RecyclerView.Adapter<BreweryAdapter.Holder>() {

    var onItemClickFunction: ((v: View, position: Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false))
    }

    override fun getItemCount(): Int {
        return brewerylist.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.itemView.apply {
            brewerylist[position].apply {
                tv_name.text = name
                tv_country.text = country
                tv_city.text = city
                tv_street.text = street
            }
        }

        holder.itemView.tv_city.text = brewerylist[position].city
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                //способ 4, только с функцией. При инициализации объекта класса Holder вызываем эту функцию
                onItemClickFunction?.invoke(it, adapterPosition)
            }
        }
    }

    fun setBreweries(list: List<Brewery>) {
        brewerylist.clear()
        brewerylist.addAll(list)
        notifyDataSetChanged()
    }

    fun getBrewery(pos:Int):Brewery{
        return brewerylist[pos]
    }
}