package net.cattaka.android.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import net.cattaka.android.learnlayout.view.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cattaka on 14/11/21.
 */
public class CardviewActivity extends Activity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardview);

        mListView = (ListView) findViewById(R.id.listView);

        String[] items = new String[100];
        for (int i=0;i<items.length;i++) {
            items[i] = "Item " + i;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_cardview, R.id.textView, items);
        mListView.setAdapter(adapter);
    }
}
