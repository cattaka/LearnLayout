package net.cattaka.android.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import net.cattaka.android.learnlayout.view.MyScrollView;


public class MultiScrollActivity extends Activity implements MyScrollView.OnScrollChangedListener {
    private MyScrollView mScrollView;
    private View mPhotoView;
    private View mHeaderView;
    private View mContentsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_scroll);

        mScrollView = (MyScrollView) findViewById(R.id.scrollView);
        mPhotoView = findViewById(R.id.photoView);
        mHeaderView = findViewById(R.id.headerView);
        mContentsView = findViewById(R.id.contentsView);

        mScrollView.setOnScrollChangedListener(this);

        mHeaderView.bringToFront();

        getActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        mPhotoView.setTranslationY(t / 2f);
        if (t < mHeaderView.getTop()) {
            mHeaderView.setTranslationY(0);
        } else {
            mHeaderView.setTranslationY(t - mHeaderView.getTop());
        }
    }
}
