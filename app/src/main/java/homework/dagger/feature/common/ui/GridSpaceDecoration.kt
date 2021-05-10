package homework.dagger.feature.common.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpaceDecoration(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.bottom = space
        // Add top margin only for the top items to avoid double space between items
        if (parent.getChildLayoutPosition(view) <= 1) {
            outRect.top = space
        } else {
            outRect.top = 0
        }
        //add full padding on side that item belongs to
        if (parent.getChildLayoutPosition(view) % 2 == 0) { //left side
            outRect.left = space
            outRect.right = space / 2
        } else { //right side
            outRect.right = space
            outRect.left = space / 2
        }
    }
}