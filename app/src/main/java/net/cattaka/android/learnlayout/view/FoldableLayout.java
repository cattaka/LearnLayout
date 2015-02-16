package net.cattaka.android.learnlayout.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takao on 2015/02/06.
 */
public class FoldableLayout extends LinearLayout {
    private List<Item> mItems = new ArrayList<Item>();

    public FoldableLayout(Context context) {
        super(context);
    }

    public FoldableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FoldableLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        if (mItems.size() == 0) {
            Rect rect = new Rect();
            for (int i=0;i<getChildCount();i++) {
                Item item = new Item();
                item.view = getChildAt(i);
                item.view.getGlobalVisibleRect(rect);
                if (getOrientation() == VERTICAL) {
                    item.mOriginalSize = rect.height();
                } else {
                    item.mOriginalSize = rect.width();
                }
                mItems.add(item);
            }
        }
    }

    private boolean mDragging;
    private float mLastX;
    private float mLastY;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View first = getChildAt(0);

        if (ev.getActionMasked() == MotionEvent.ACTION_DOWN) {
            Rect r = new Rect();
            first.getLocalVisibleRect(r);
            if (!r.contains((int) ev.getX(), (int) ev.getY())) {
                mDragging = true;
            }
        } else if (ev.getActionMasked() == MotionEvent.ACTION_UP) {
            mDragging = false;
        } else if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
            boolean v = (getOrientation() == VERTICAL);
            if (v) {
                float dy = ev.getY() - mLastY;
                fold(dy);
            } else {
                float dx = ev.getX() - mLastX;
                fold(dx);
            }
        }

        mLastX = ev.getX();
        mLastY = ev.getY();
        return super.dispatchTouchEvent(ev);
    }

    private void fold(float diff) {
        if (diff >= 0) {
            for (int i = 0;i < mItems.size() - 1; i++) {
                if (!fold(diff, mItems.get(i))) {
                    break;
                }
            }
        } else {
            for (int i = mItems.size() - 2; i >= 0; i--) {
                if (!fold(diff, mItems.get(i))) {
                    break;
                }
            }
        }
    }

    private boolean fold(float diff, Item item) {
        boolean v = (getOrientation() == VERTICAL);

        LayoutParams params = (LayoutParams) item.view.getLayoutParams();
        int h = v ? (item.view.getHeight() + (int) diff) : (item.view.getWidth() + (int) diff);
        boolean next = false;
        if (h < 0) {
            h = 0;
            next = true;
        } else if (h > item.mOriginalSize) {
            h = item.mOriginalSize;
            next = true;
        }
        if (v) {
            if (params.height != h) {
                params.height = h;
                item.view.setLayoutParams(params);
            }
        } else {
            if (params.width != h) {
                params.width = h;
                item.view.setLayoutParams(params);
            }
        }
        return next;
    }

    public static class Item {
        int mOriginalSize;
        View view;
    }
}
