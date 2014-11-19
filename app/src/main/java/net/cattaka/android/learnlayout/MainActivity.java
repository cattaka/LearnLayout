package net.cattaka.android.learnlayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import net.cattaka.android.learnlayout.view.MyScrollView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity implements AdapterView.OnItemClickListener {
    private static class ButtonItem {
        private String label;
        private Class<? extends Activity> clazz;

        private ButtonItem(String label, Class<? extends Activity> clazz) {
            this.label = label;
            this.clazz = clazz;
        }

        @Override
        public String toString() {
            return label;
        }
    }

    private ListView mListView;
    private List<ButtonItem> items = new ArrayList<ButtonItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mListView.setOnItemClickListener(this);
        items.add(new ButtonItem("MultiScroll", MultiScrollActivity.class));
        items.add(new ButtonItem("PullToReflesh", PullToRefleshActivity.class));
        items.add(new ButtonItem("HeaderList", HeaderListActivity.class));
        items.add(new ButtonItem("ViewPager", ViewPagerActivity.class));

        ArrayAdapter<ButtonItem> adapter = new ArrayAdapter<ButtonItem>(this, android.R.layout.simple_list_item_1, items);
        mListView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.listView) {
            ButtonItem item = (ButtonItem) parent.getItemAtPosition(position);
            Intent intent = new Intent(this, item.clazz);
            startActivity(intent);
        }
    }
}
