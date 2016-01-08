package com.robosoft.archanakumari.parserassignment.Modal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.robosoft.archanakumari.parserassignment.R;

/**
 * Created by archanakumari on 17/12/15.
 */

public class ViewHolder extends RecyclerView.ViewHolder{

    public TextView mIdTextView;
    public TextView mTitleTextView;
    public TextView mArtistTextView;
    public TextView mDurationTextView;
    public ImageView mThumb_urlImageView;
    public   ProgressBar progressBar;

        public ViewHolder(View itemView) {
            super(itemView);
            mIdTextView = (TextView) itemView.findViewById(R.id.id);
            mTitleTextView = (TextView) itemView.findViewById(R.id.title);
            mArtistTextView = (TextView) itemView.findViewById(R.id.artist);
            mDurationTextView = (TextView) itemView.findViewById(R.id.duration);
            mThumb_urlImageView = (ImageView) itemView.findViewById(R.id.thumb_url);
            progressBar = (ProgressBar)itemView.findViewById(R.id.progress);

        }

    }

