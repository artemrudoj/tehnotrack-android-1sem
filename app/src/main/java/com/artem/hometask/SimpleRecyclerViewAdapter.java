package com.artem.hometask;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import org.w3c.dom.Text;

import java.lang.ref.WeakReference;

/**
 * Created by artem on 17.03.16.
 */
public class SimpleRecyclerViewAdapter extends  RecyclerView.Adapter<SimpleViewHolder> {

    public final int ITEMS_COUNT = 1000000;

    private final WeakReference<LayoutInflater> mInflater;

    public SimpleRecyclerViewAdapter(LayoutInflater inflater) {
        mInflater = new WeakReference<LayoutInflater>(inflater);
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = mInflater.get();
        if (inflater != null) {
            return new SimpleViewHolder((CardView)inflater.inflate(R.layout.item, parent, false));
        }
        else {
            throw new RuntimeException("Oooops, looks like activity is dead");
        }
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        holder.setTitle(TextGenerator.convert(position + 1));
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
