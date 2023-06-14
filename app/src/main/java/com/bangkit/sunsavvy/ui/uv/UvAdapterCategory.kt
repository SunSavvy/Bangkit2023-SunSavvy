package com.bangkit.sunsavvy.ui.uv

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.model.UvcategoryModel

class UvAdapterCategory(private var items: List<UvcategoryModel>) : RecyclerView.Adapter<UvAdapterCategory.ItemViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<UvcategoryModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_uvcategory, parent, false)
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
        private val indicator: ImageView = itemView.findViewById(R.id.indicator)
        private val uvIndex: TextView = itemView.findViewById(R.id.uv_index)
        private val uvCategory: TextView = itemView.findViewById(R.id.uv_category)
        private val protectionLevel: TextView = itemView.findViewById(R.id.protection_level)
        private val protectionSpf: TextView = itemView.findViewById(R.id.protection_spf)
        private val protectionOne: ImageView = itemView.findViewById(R.id.protection1)
        private val protectionTwo: ImageView = itemView.findViewById(R.id.protection2)
        private val protectionThree: ImageView = itemView.findViewById(R.id.protection3)
        private val protectionFour: ImageView = itemView.findViewById(R.id.protection4)
        private val protectionFive: ImageView = itemView.findViewById(R.id.protection5)

        fun bind(item: UvcategoryModel) {
            indicator.setImageResource(item.indicator)
            uvIndex.text = item.uvIndex.toString()
            uvCategory.text = item.uvCategory.toString()
            protectionLevel.text = item.protectionLevel.toString()
            protectionSpf.text = item.protectionSpf.toString()
            protectionOne.setImageResource(item.protectionOne)
            protectionTwo.setImageResource(item.protectionTwo)
            protectionThree.setImageResource(item.protectionThree)
            protectionFour.setImageResource(item.protectionFour)
            protectionFive.setImageResource(item.protectionFive)

            when (uvCategory.text) {
                "Extreme" -> {
                    val textColor = ContextCompat.getColor(uvIndex.context, R.color.uv_extreme)
                    uvIndex.setTextColor(textColor)
                    uvCategory.setTextColor(textColor)
                }
                "Very High" -> {
                    val textColor = ContextCompat.getColor(uvIndex.context, R.color.uv_very_high)
                    uvIndex.setTextColor(textColor)
                    uvCategory.setTextColor(textColor)
                }
                "High" -> {
                    val textColor = ContextCompat.getColor(uvIndex.context, R.color.uv_high)
                    uvIndex.setTextColor(textColor)
                    uvCategory.setTextColor(textColor)
                }
                "Medium" -> {
                    val textColor = ContextCompat.getColor(uvIndex.context, R.color.uv_medium)
                    uvIndex.setTextColor(textColor)
                    uvCategory.setTextColor(textColor)
                }
                "Low" -> {
                    val textColor = ContextCompat.getColor(uvIndex.context, R.color.uv_low)
                    uvIndex.setTextColor(textColor)
                    uvCategory.setTextColor(textColor)
                }
            }
        }
    }
}
