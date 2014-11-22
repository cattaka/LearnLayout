package net.cattaka.android.learnlayout.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.cattaka.android.learnlayout.R;

import java.util.List;

/**
* Created by cattaka on 14/11/22.
*/
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public View mAddButton;
        public View mDeleteButton;

        public ViewHolder(View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.textView);
            mAddButton = view.findViewById(R.id.addButton);
            mDeleteButton = view.findViewById(R.id.deleteButton);
            mAddButton.setTag(this);
            mDeleteButton.setTag(this);
        }
    }

    private Context mContext;
    private List<String> mDataset;
    private int newItemCount = 0;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyRecyclerViewAdapter(Context context, List<String> myDataset) {
        mContext = context;
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recycler_view, null);
        // set the view's size, margins, paddings and layout parameters
        final ViewHolder vh = new ViewHolder(view);
        vh.mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAdd(vh.getPosition());
            }
        });
        vh.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickDelete(vh.getPosition());
            }
        });
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void onClickAdd(int position) {
        mDataset.add(position, "new Item " + (newItemCount++));
        notifyItemInserted(position);
    }
    public void onClickDelete(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
    }
}
