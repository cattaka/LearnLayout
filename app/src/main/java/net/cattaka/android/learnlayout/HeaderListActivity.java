package net.cattaka.android.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

import net.cattaka.android.learnlayout.view.HeaderListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cattaka on 14/11/19.
 */
public class HeaderListActivity extends Activity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header_list);

        mListView = (ListView) findViewById(R.id.listView);

        HeaderListAdapter adapter;
        {
            List<String> items = new ArrayList<String>();
            for (int i=0;i<100;i++) {
                items.add("Item " + i);
            }
            adapter = new HeaderListAdapter(this, items);
        }
        mListView.setAdapter(adapter);
        mListView.setOnScrollListener(adapter);
    }
}
