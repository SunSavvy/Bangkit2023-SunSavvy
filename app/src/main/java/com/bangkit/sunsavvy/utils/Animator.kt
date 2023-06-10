package com.bangkit.sunsavvy.utils

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView

class Animator(private val cloud: ImageView, private val screenWidth: Int, private val speedMultiplier: Float) {

    companion object {
        private const val ANIMATION_DURATION = 10000L
        private const val ANIMATION_DISTANCE_FACTOR = 1
    }

    private val cloudWidth: Float = cloud.width.toFloat()
    private val animDistance: Float = (screenWidth + cloudWidth) * ANIMATION_DISTANCE_FACTOR

    private var animator: ObjectAnimator? = null

    fun startFloatingAnimation(view: View, distance: Float, duration: Long) {
        animator = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0f, distance)
        animator?.duration = duration
        animator?.interpolator = AccelerateDecelerateInterpolator()
        animator?.repeatCount = ObjectAnimator.INFINITE
        animator?.repeatMode = ObjectAnimator.REVERSE
        animator?.start()
    }

    fun startAnimation() {
        val duration = (ANIMATION_DURATION / speedMultiplier).toLong()

        animator = ObjectAnimator.ofFloat(cloud, View.TRANSLATION_X, -animDistance, animDistance)
        animator?.duration = duration
        animator?.interpolator = LinearInterpolator()
        animator?.repeatCount = ValueAnimator.INFINITE
        animator?.start()
    }

    fun stopAnimation() {
        animator?.cancel()
        animator = null
    }
}
