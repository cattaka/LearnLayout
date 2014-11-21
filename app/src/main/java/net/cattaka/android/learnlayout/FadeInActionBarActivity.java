package net.cattaka.android.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

import net.cattaka.android.learnlayout.view.MyScrollView;

/**
 * Created by cattaka on 14/11/21.
 */
public class FadeInActionBarActivity extends Activity implements MyScrollView.OnScrollChangedListener {
    MyScrollView mScrollView;
    View mHeaderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.FadeInActionBarAppTheme);
        setContentView(R.layout.activity_fade_in_action_bar);

        mScrollView = (MyScrollView) findViewById(R.id.scrollView);
        mHeaderView = findViewById(R.id.headerView);

        mScrollView.setOnScrollChangedListener(this);

        getActionBar().hide();
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (mHeaderView.getTop() < t) {
            getActionBar().show();
        } else {
            getActionBar().hide();
        }
    }
}
