package com.example.otchallenge.ui

import android.content.Context
import androidx.window.layout.WindowMetricsCalculator

// https://developer.android.com/develop/ui/views/layout/responsive-adaptive-design-with-views#smallest_width

object WindowSizeClassUtil {

    enum class WindowSizeClass {
        COMPACT, MEDIUM, EXPANDED
    }

    fun computeHorizontalWindowSizeClass(context: Context): WindowSizeClass {
        val calculator = WindowMetricsCalculator.getOrCreate()
        val metrics = calculator.computeCurrentWindowMetrics(context)
        val displayMetrics = context.resources.displayMetrics
        val widthDp = metrics.bounds.width() / displayMetrics.density

        return if (widthDp < 600) {
            WindowSizeClass.COMPACT
        } else if (widthDp < 960) {
            WindowSizeClass.MEDIUM
        } else {
            WindowSizeClass.EXPANDED
        }
    }
}
