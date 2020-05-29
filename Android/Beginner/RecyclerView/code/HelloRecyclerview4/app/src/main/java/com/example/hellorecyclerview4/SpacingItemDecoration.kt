package com.example.hellorecyclerview4

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpacingItemDecoration(private val spanCount: Int, private val spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position < spanCount) {
            outRect.top = spacing
        }
        outRect.bottom = spacing
        outRect.left = spacing
        outRect.right = spacing
        if (position % spanCount == 0) {
            outRect.right = spacing / 2
        }
        if ((position + 1) % spanCount == 0) {
            outRect.left = spacing / 2
        }
    }
}