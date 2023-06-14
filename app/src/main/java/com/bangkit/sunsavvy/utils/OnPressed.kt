package com.bangkit.sunsavvy.utils

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.widget.Button
import android.animation.ObjectAnimator
import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.os.Build
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import org.w3c.dom.Text

class OnPressed {

    companion object {
        private const val ANIMATION_DURATION = 100
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    fun setButtonPressedPrimary(button: Button?) {
        val animatorSet = AnimatorSet().apply {
            playTogether(
                createAnimator(button, "elevation", ANIMATION_DURATION, 36f, 8f),
                createAnimator(button, "translationY", ANIMATION_DURATION, 0f, 8f),
                createAnimator(button, "scaleX", ANIMATION_DURATION, 1f, 0.98f),
                createAnimator(button, "scaleY", ANIMATION_DURATION, 1f, 0.98f)
            )
        }

        button?.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> animatorSet.start()
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> animatorSet.reverse()
            }
            false
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ClickableViewAccessibility")
    fun setButtonPressedSecondary(button: Button?, from: Int, to: Int) {
        val textColorAnimator = ObjectAnimator.ofObject(button, "textColor", ArgbEvaluator(), from, to).apply {
            this.duration = ANIMATION_DURATION.toLong()
        }

        val backgroundAnimator = ObjectAnimator.ofObject(button?.background, "tint", ArgbEvaluator(), to, from).apply {
            this.duration = ANIMATION_DURATION.toLong()
        }

        button?.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    textColorAnimator.reverse()
                    backgroundAnimator.reverse()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    textColorAnimator.start()
                    backgroundAnimator.start()
                }
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setTextPressedPrimary(text: TextView?, from: Int, to: Int) {
        text?.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    setTextViewFont(text, to)
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    setTextViewFont(text, from)
                }
            }
            false
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    fun setTextPressedSecondary(textView: TextView?, from: Int, to: Int) {
        val textColorAnimator = ObjectAnimator.ofObject(textView, "textColor", ArgbEvaluator(), from, to).apply {
            this.duration = ANIMATION_DURATION.toLong()
        }

        val backgroundAnimator = ObjectAnimator.ofObject(textView?.background, "tint", ArgbEvaluator(), to, from).apply {
            this.duration = ANIMATION_DURATION.toLong()
        }

        textView?.setOnTouchListener { _, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    textColorAnimator.reverse()
                    backgroundAnimator.reverse()
                }
                MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                    textColorAnimator.start()
                    backgroundAnimator.start()
                }
            }
            false
        }
    }

    private fun createAnimator(target: View?, property: String, duration: Int, vararg values: Float): ObjectAnimator {
        return ObjectAnimator.ofFloat(target, property, *values).apply {
            this.duration = ANIMATION_DURATION.toLong()
        }
    }

    private fun setTextViewFont(textView: TextView?, fontResId: Int) {
        val typeface = ResourcesCompat.getFont(textView?.context!!, fontResId)
        textView.typeface = typeface
    }
}