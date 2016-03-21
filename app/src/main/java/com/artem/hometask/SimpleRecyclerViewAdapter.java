package com.artem.hometask;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;
import java.util.zip.Inflater;

/**
 * Created by artem on 17.03.16.
 */
public class SimpleRecyclerViewAdapter extends  RecyclerView.Adapter<SimpleViewHolder> {

    public final int ITEMS_COUNT = 1000000;

    private final LayoutInflater mInflater;
    Context mContext;

    public SimpleRecyclerViewAdapter(LayoutInflater inflater, Context context) {
        mInflater = inflater;
        mContext = context;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater != null) {
            return new SimpleViewHolder((CardView)mInflater.inflate(R.layout.item, parent, false));
        }
        else {
            throw new RuntimeException("Oooops, looks like activity is dead");
        }
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.setTitle(TextGenerator.convert(mContext, position + 1));
        if(position % 2 == 0) {
            holder.setColor(R.color.grey);
        } else {
            holder.setColor(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return ITEMS_COUNT;
    }
}
