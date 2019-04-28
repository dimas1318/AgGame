package com.hackaton.agilegamificator.custom_views;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Dmitry Parshin on 27.04.2019.
 */
public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mOffset;

    public ItemOffsetDecoration(int offset) {
        mOffset = offset;
    }

    public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect,
                               @NonNull View view,
                               @NonNull RecyclerView parent,
                               @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int itemCount = state.getItemCount();

        final int itemPosition = parent.getChildAdapterPosition(view);

        // no position, leave it alone
        if (itemPosition == RecyclerView.NO_POSITION) {
            return;
        }

        // first item
        if (itemPosition == 0) {
            outRect.set(0, mOffset, 0, mOffset / 2);
        }
        // last item
        else if (itemCount > 0 && itemPosition == itemCount - 1) {
            outRect.set(0, mOffset / 2, 0, mOffset);
        }
        // every other item
        else {
            outRect.set(0, mOffset / 2, 0, mOffset / 2);
        }
    }
}
