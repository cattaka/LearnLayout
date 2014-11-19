package net.cattaka.android.learnlayout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cattaka on 14/11/19.
 */
public class PullToRefleshActivity extends Activity implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private List<String> mItems;
    private ArrayAdapter<String> adapter;

    public PullToRefleshActivity() {
        mItems = new ArrayList<String>();
        for (int i = 0; i<100;i++) {
            mItems.add("Item " + i);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ActionBarOverlayAppTheme);
        setContentView(R.layout.activity_pull_to_reflesh);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnScrollListener(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItems);
        mListView.setAdapter(adapter);
        {
            int h = getActionBar().getHeight();
            TextView view = new TextView(this);
            view.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h));
            mListView.addHeaderView(view);
        }

        mSwipeRefreshLayout.setOnRefreshListener(this);
        // mSwipeRefreshLayout.setColorScheme(android.R.color.holo_red_light, android.R.color.holo_green_light, android.R.color.holo_blue_light, android.R.color.holo_orange_light);

        int top = getActionBar().getHeight();
        mSwipeRefreshLayout.setProgressViewOffset(false, top, top + 100);
    }

    @Override
    public void onRefresh() {
        // 3秒待機
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.add("Item " + adapter.getCount());
                adapter.notifyDataSetInvalidated();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }, 2000);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (firstVisibleItem > 10) {
            getActionBar().hide();
        } else {
            getActionBar().show();
        }
    }
}
