package com.bangkit.sunsavvy.ui.aboutus

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.R
import com.bangkit.sunsavvy.data.model.TeamModel

class AboutusAdapter(private var items: List<TeamModel>) : RecyclerView.Adapter<AboutusAdapter.ItemViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<TeamModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
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
        private val picture: ImageView = itemView.findViewById(R.id.picture)
        private val name: TextView = itemView.findViewById(R.id.name)
        private val email: TextView = itemView.findViewById(R.id.email)
        private val cohort: TextView = itemView.findViewById(R.id.cohort)
        private val socialOneIcon: ImageView = itemView.findViewById(R.id.social1)
        private val socialTwoIcon: ImageView = itemView.findViewById(R.id.social2)
        private val socialThreeIcon: ImageView = itemView.findViewById(R.id.social3)
        private val socialFourIcon: ImageView = itemView.findViewById(R.id.social4)

        @SuppressLint("SetTextI18n")
        fun bind(item: TeamModel) {
            picture.setImageResource(item.picture)
            name.text = item.name.toString()
            email.text = item.email.toString()
            cohort.text = "Bangkit 2023\n ${item.cohort}"

            socialOneIcon.setImageResource(item.socialOneIcon)
            socialOneIcon.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.socialOneLink)
                itemView.context.startActivity(intent)
            }

            socialTwoIcon.setImageResource(item.socialTwoIcon)
            socialTwoIcon.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.socialTwoLink)
                itemView.context.startActivity(intent)
            }

            socialThreeIcon.setImageResource(item.socialThreeIcon)
            socialThreeIcon.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.socialThreeLink)
                itemView.context.startActivity(intent)
            }

            socialFourIcon.setImageResource(item.socialFourIcon)
            socialFourIcon.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(item.socialFourLink)
                itemView.context.startActivity(intent)
            }
        }
    }
}
