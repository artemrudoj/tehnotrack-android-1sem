package com.artem.hometask.technology.list;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.artem.hometask.R;
import com.artem.hometask.model.Technology;

/**
 * Created by artem on 17.03.16.
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitle;
    private CardView rootView;
    private Technology item;
    public SimpleViewHolder(CardView itemView, final SimpleRecyclerViewAdapter.IClickListener onClickListener) {
        super(itemView);
        rootView = itemView;
        mTitle = (TextView)itemView.findViewById(R.id.title);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(item);
            }
        });
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setColor(int id) {
        rootView.setCardBackgroundColor(ContextCompat.getColor(rootView.getContext(), id));
    }

    public void updateView() {
        if(item != null) {
            setTitle(item.getTitle());
        }
    }

    public void setItem(Technology item) {
        this.item = item;
    }
}
