package com.artem.hometask.technology.list;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.artem.hometask.R;
import com.artem.hometask.model.Technology;

import java.util.List;

/**
 * Created by artem on 17.03.16.
 */
public class SimpleRecyclerViewAdapter extends  RecyclerView.Adapter<SimpleViewHolder> {
    private final LayoutInflater mInflater;
    Context mContext;
    List<Technology> technologyList;
    IClickListener clickListener;
    int position;


    public SimpleRecyclerViewAdapter(LayoutInflater inflater, Context context, List<Technology> technologies, IClickListener clickListener) {
        this.mInflater = inflater;
        this.mContext = context;
        this.technologyList = technologies;
        this.clickListener = clickListener;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater != null) {
            return new SimpleViewHolder((CardView)mInflater.inflate(R.layout.item, parent, false),position, clickListener);
        }
        else {
            throw new RuntimeException("Oooops, looks like activity is dead");
        }
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, int position) {
        if(technologyList != null) {
            this.position = position;
            holder.setItem(technologyList.get(position));
            holder.updateView();
        }
    }

    @Override
    public int getItemCount() {
        if(technologyList != null) {
            return technologyList.size();
        }
        return 0;
    }

    public  interface IClickListener {
        void onClick(Technology technology, int position);
    }
}
