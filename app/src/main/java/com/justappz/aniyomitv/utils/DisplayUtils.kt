package com.justappz.aniyomitv.utils

import android.content.Context
import kotlin.math.max

object DisplayUtils {

    /**
     * Calculates the span count for a grid based on available screen width
     * and a minimum desired item width (in dp).
     *
     * @param context The Context for accessing resources.
     * @param minItemWidthDp Minimum width per item in dp.
     * @param minSpan Minimum number of columns (default: 2).
     * @return Calculated span count.
     */
    fun calculateSpanCount(
        context: Context,
        minItemWidthDp: Int,
        minSpan: Int = 2
    ): Int {
        val displayMetrics = context.resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return max(minSpan, (screenWidthDp / minItemWidthDp).toInt())
    }
}