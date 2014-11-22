package net.cattaka.android.learnlayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import net.cattaka.android.learnlayout.view.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cattaka on 14/11/21.
 */
public class RecyclerViewActivity extends Activity {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        {   // Create and Set adapter
            List<String> data = new ArrayList<String>();
            for (int i = 0; i < 100; i++) {
                data.add("Item " + i);
            }
            mRecyclerView.setAdapter(new MyRecyclerViewAdapter(this, data));
        }
    }
}
