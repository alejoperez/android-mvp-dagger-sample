package com.mvp.dagger.sample.view

import android.content.Context
import android.graphics.Canvas
import androidx.recyclerview.widget.RecyclerView
import androidx.core.content.ContextCompat
import android.graphics.drawable.Drawable
import com.mvp.dagger.sample.R

class SimpleDividerItemDecorator(context: Context) : RecyclerView.ItemDecoration() {

    private val divider: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_linde_divider)

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)

            val params = child.layoutParams as RecyclerView.LayoutParams

            val top = child.bottom + params.bottomMargin
            val bottom = top + (divider?.intrinsicHeight ?: 0)

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
        }
    }
}