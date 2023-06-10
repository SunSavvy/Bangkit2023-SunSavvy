package com.bangkit.sunsavvy.utils

import android.content.Context
import android.os.Build
import android.util.TypedValue
import androidx.annotation.RequiresApi
import com.google.android.material.R

@RequiresApi(Build.VERSION_CODES.O)
class GetColor {
    companion object {
        fun getPrimaryColor(context: Context): Int {
            val typedValue = TypedValue()
            val typedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorPrimary))
            val color = typedArray.getColor(0, 0)
            typedArray.recycle()
            return color
        }

        fun getPrimaryVariantColor(context: Context): Int {
            val typedValue = TypedValue()
            val typedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorPrimaryVariant))
            val color = typedArray.getColor(0, 0)
            typedArray.recycle()
            return color
        }

        fun getSecondaryColor(context: Context): Int {
            val typedValue = TypedValue()
            val typedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorSecondary))
            val color = typedArray.getColor(0, 0)
            typedArray.recycle()
            return color
        }

        fun getAccentColor(context: Context): Int {
            val typedValue = TypedValue()
            val typedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorAccent))
            val color = typedArray.getColor(0, 0)
            typedArray.recycle()
            return color
        }

        fun getErrorColor(context: Context): Int {
            val typedValue = TypedValue()
            val typedArray = context.obtainStyledAttributes(typedValue.data, intArrayOf(R.attr.colorError))
            val color = typedArray.getColor(0, 0)
            typedArray.recycle()
            return color
        }
    }
}