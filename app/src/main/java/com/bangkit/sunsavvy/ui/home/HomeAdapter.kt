package com.bangkit.sunsavvy.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.sunsavvy.R

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private var items: List<String> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<String>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_slogan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val slogan: TextView = itemView.findViewById(R.id.slogan_protect_yourself)

        fun bind(item: String) {
            val sloganId = when (adapterPosition) {
                0 -> R.drawable.ic_outfit
                1 -> R.drawable.ic_lotion
                2 -> R.drawable.ic_cap
                3 -> R.drawable.ic_sunglasses
                4 -> R.drawable.ic_tree
                else -> throw IndexOutOfBoundsException("Invalid item position")
            }
            isActive(slogan, sloganId)
            slogan.text = item
        }
    }

    @SuppressLint("InlinedApi")
    private fun isActive(textView: TextView, drawableResId: Int) {
        val colorPrimary = resolveThemeAttribute(textView.context, android.R.attr.colorPrimary)
        val colorAccent = resolveThemeAttribute(textView.context, android.R.attr.colorAccent)

        textView.backgroundTintList = ColorStateList.valueOf(colorAccent)
        textView.setTextColor(colorPrimary)
        setCompoundDrawableWithTint(textView, drawableResId, colorPrimary)
    }

    @SuppressLint("InlinedApi")
    private fun isInactive(textView: TextView, drawableResId: Int) {
        val colorSecondary = resolveThemeAttribute(textView.context, android.R.attr.colorSecondary)
        val colorTransparent = ContextCompat.getColor(textView.context, R.color.transparent)

        textView.backgroundTintList = ColorStateList.valueOf(colorTransparent)
        textView.setTextColor(colorSecondary)
        setCompoundDrawableWithTint(textView, drawableResId, colorSecondary)
    }

    private fun setCompoundDrawableWithTint(view: TextView, drawableResId: Int, tint: Int) {
        val drawable = ContextCompat.getDrawable(view.context, drawableResId)?.apply {
            setTint(tint)
        }
        view.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
    }

    private fun resolveThemeAttribute(context: Context, attribute: Int): Int {
        val typedValue = TypedValue()
        context.theme.resolveAttribute(attribute, typedValue, true)
        return typedValue.data
    }
}