package com.bangkit.sunsavvy.ui.skintype

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.localmodel.SkintypeModel

class SkintypeAdapterCategory(private var items: List<SkintypeModel>) : RecyclerView.Adapter<SkintypeAdapterCategory.ItemViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<SkintypeModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_skintype, parent, false)
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
        private val imgSkinType: ImageView = itemView.findViewById(R.id.img_skin_type)
        private val skinTypeFull: TextView = itemView.findViewById(R.id.skin_type_full)
        private val skinTypeDescription: TextView = itemView.findViewById(R.id.skin_type_description)
        private val skinTypeNumber: TextView = itemView.findViewById(R.id.skin_type_number)
        private val skinTypeExplanation: TextView = itemView.findViewById(R.id.skin_type_explanation)

        fun bind(item: SkintypeModel) {
            imgSkinType.setImageResource(item.imgSkinType)
            skinTypeFull.text = item.skinTypeFull.toString()
            skinTypeDescription.text = item.skinTypeDesctiption.toString()
            skinTypeNumber.text = item.skinTypeNumber.toString()
            skinTypeExplanation.text = item.skinTypeExplanation.toString()
        }
    }
}
