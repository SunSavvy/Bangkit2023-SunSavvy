package com.bangkit.sunsavvy.ui.sunprotection

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.model.SunprotectionModel

class SunprotectionAdapterCategory(private var items: List<SunprotectionModel>) : RecyclerView.Adapter<SunprotectionAdapterCategory.ItemViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<SunprotectionModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sunprotection, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgSlogan: ImageView = itemView.findViewById(R.id.img_slogan)
        private val slogan: TextView = itemView.findViewById(R.id.slogan)
        private val sloganDescription: TextView = itemView.findViewById(R.id.slogan_description)

        fun bind(item: SunprotectionModel) {
            imgSlogan.setImageResource(item.imgSlogan)
            slogan.text = item.slogan.toString()
            sloganDescription.text = item.sloganDescription.toString()
        }
    }
}
