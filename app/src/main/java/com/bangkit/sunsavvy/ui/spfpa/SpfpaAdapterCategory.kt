package com.bangkit.sunsavvy.ui.spfpa

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.model.SpfpaModel

class SpfpaAdapterCategory(private var items: List<SpfpaModel>) : RecyclerView.Adapter<SpfpaAdapterCategory.ItemViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<SpfpaModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_spfpa, parent, false)
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
        private val spf: TextView = itemView.findViewById(R.id.spf)
        private val pa: TextView = itemView.findViewById(R.id.pa)
        private val explanation: TextView = itemView.findViewById(R.id.explanation)

        fun bind(item: SpfpaModel) {
            spf.text = item.spf.toString()
            pa.text = item.pa.toString()
            explanation.text = item.explanation.toString()
        }
    }
}
