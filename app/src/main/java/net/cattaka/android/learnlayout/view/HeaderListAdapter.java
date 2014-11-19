package net.cattaka.android.learnlayout.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.cattaka.android.learnlayout.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by cattaka on 14/11/19.
 */
public class HeaderListAdapter extends ArrayAdapter<String> implements AbsListView.OnScrollListener {
    private static class ViewHolder {
        TextView headerText;
    }

    public HeaderListAdapter(Context context, List<String> objects) {
        super(context, R.layout.item_header_list, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_header_list, null);
            vh = new ViewHolder();
            vh.headerText = (TextView) convertView.findViewById(R.id.headerText);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        String item = getItem(position);
        vh.headerText.setText(item);
        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        for (int i=0;i<getCount();i++) {
            View child = view.getChildAt(i);
            if (child == null) {
                return;
            }
            ViewHolder vh = (ViewHolder) child.getTag();
            int top = -child.getTop();
            if (top >= 0) {
                if (top < child.getHeight() - vh.headerText.getHeight()) {
                    vh.headerText.setTranslationY(top);
                } else {
                    vh.headerText.setTranslationY(child.getHeight() - vh.headerText.getHeight());
                }
            } else {
                vh.headerText.setTranslationY(0);
            }
        }
    }
}
