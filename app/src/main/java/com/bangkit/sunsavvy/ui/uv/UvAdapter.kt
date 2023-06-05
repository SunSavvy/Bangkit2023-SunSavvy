package com.bangkit.sunsavvy.ui.uv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.model.InformationModel

class UvAdapter(private var items: List<InformationModel>) : RecyclerView.Adapter<UvAdapter.ItemViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<InformationModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_information, parent, false)
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
        private val headerTextView: TextView = itemView.findViewById(R.id.head)
        private val bodyTextView: TextView = itemView.findViewById(R.id.body)

        fun bind(item: InformationModel) {
            headerTextView.text = item.header
            bodyTextView.text = item.body
        }
    }
}
