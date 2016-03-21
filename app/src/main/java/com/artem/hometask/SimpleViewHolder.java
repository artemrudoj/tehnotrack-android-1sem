package com.artem.hometask;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by artem on 17.03.16.
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitle;
    private CardView rootView;
    public SimpleViewHolder(CardView itemView) {
        super(itemView);
        rootView = itemView;
        mTitle = (TextView)itemView.findViewById(R.id.title);
    }

    public void setTitle(String title) {
        mTitle.setText(title);
    }

    public void setColor(int id) {
        rootView.setCardBackgroundColor(ContextCompat.getColor(rootView.getContext(), id));
    }
}
