package net.cattaka.android.learnlayout.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by takao on 2015/02/06.
 */
public class FoldableLayout extends LinearLayout {
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

    }

    private boolean mDragging;
    private float mLastX;
    private float mLastY;
    private int mOriginalValue = -1;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (getOrientation() == VERTICAL) {
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
                LayoutParams params = (LayoutParams) first.getLayoutParams();
                if (mOriginalValue < 0) {
                    mOriginalValue = first.getHeight();
                }
                float dy = ev.getY() - mLastY;
                int h = first.getHeight() + (int) dy;
                if (h <= 0) {
                    h = 0;
                } else if (h >= mOriginalValue) {
                    h = mOriginalValue;
                }
                if (params.height != h) {
                    params.height = h;
                    first.setLayoutParams(params);
                }
            }
        } else {
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
                LayoutParams params = (LayoutParams) first.getLayoutParams();
                if (mOriginalValue < 0) {
                    mOriginalValue = first.getWidth();
                }
                float dx = ev.getX() - mLastX;
                int w = first.getWidth() + (int) dx;
                if (w <= 0) {
                    w = 0;
                } else if (w >= mOriginalValue) {
                    w = mOriginalValue;
                }
                if (params.width != w) {
                    params.width = w;
                    first.setLayoutParams(params);
                }
            }
        }

        mLastX = ev.getX();
        mLastY = ev.getY();
        return super.dispatchTouchEvent(ev);
    }
}
