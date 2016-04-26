package com.artem.hometask.technology.list;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.artem.hometask.R;
import com.artem.hometask.imageloader.ImageDownloadManager;
import com.artem.hometask.model.Technology;
import com.artem.hometask.technology.detail.TechnologyDetailFragment;

/**
 * Created by artem on 17.03.16.
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitle;
    private ImageView mImageView;
    private CardView rootView;
    private Technology item;
    private int position;
    public SimpleViewHolder(CardView itemView, final int position, final SimpleRecyclerViewAdapter.IClickListener onClickListener) {
        super(itemView);
        this.position = position;
        rootView = itemView;
        mImageView = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView)itemView.findViewById(R.id.title);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(item, position);
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
            //// TODO: 27.04.16 correct size  and context
            ImageDownloadManager.getInstance().setImageViewByUrl(rootView.getContext(), TechnologyDetailFragment.BASE_URL + item.getPicture(),
                    mImageView, 120);
        }
    }

    public void setItem(Technology item) {
        this.item = item;
    }
}
