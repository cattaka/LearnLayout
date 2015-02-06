package net.cattaka.android.learnlayout.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import net.cattaka.android.learnlayout.view.HeaderListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by takao on 2015/02/06.
 */
public class MyListFragment extends ListFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<String> items = new ArrayList<String>();
        for (int i=0;i<100;i++) {
            items.add("Item " + i);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);
        setListAdapter(adapter);
    }
}
