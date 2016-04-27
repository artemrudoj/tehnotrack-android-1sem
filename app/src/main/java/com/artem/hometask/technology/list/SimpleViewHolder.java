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
import com.artem.hometask.utils.Utils;

/**
 * Created by artem on 17.03.16.
 */
public class SimpleViewHolder extends RecyclerView.ViewHolder {
    private TextView mTitle;
    private ImageView mImageView;
    private CardView rootView;
    private Technology item;
    private int position;
    private final float IMAGE_SIZE_DP = 64;

    public SimpleViewHolder(CardView itemView, final SimpleRecyclerViewAdapter.IClickListener onClickListener) {
        super(itemView);
        rootView = itemView;
        mImageView = (ImageView) itemView.findViewById(R.id.image);
        mTitle = (TextView)itemView.findViewById(R.id.title);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(position);
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
            ImageDownloadManager.getInstance().setImageViewByUrl(rootView.getContext(), TechnologyDetailFragment.BASE_URL + item.getPicture(),
                    mImageView, (int) Utils.convertDpToPixel(IMAGE_SIZE_DP, rootView.getContext()));
        }
    }

    public void setItem(Technology item) {
        this.item = item;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
