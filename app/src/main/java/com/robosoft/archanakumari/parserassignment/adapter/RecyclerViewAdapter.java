package com.robosoft.archanakumari.parserassignment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.robosoft.archanakumari.parserassignment.Modal.SongSite;
import com.robosoft.archanakumari.parserassignment.Modal.ViewHolder;
import com.robosoft.archanakumari.parserassignment.R;
import com.robosoft.archanakumari.parserassignment.dto.Communicator;

import java.util.List;

/**
 * Created by archanakumari on 17/12/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

    private View mOneRow;
    private  ImageLoader mImageLoader;
    DisplayImageOptions options;
    private Context mContext;
    List<SongSite> list;
    Communicator communicator;
//constructor i have added just for cheak
    public RecyclerViewAdapter(Context  mContext, List<SongSite> list){
        communicator = (Communicator) mContext;
        this. mContext = mContext;
        this.list = list;
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext).build();
        mImageLoader= ImageLoader.getInstance();
        mImageLoader.init(config);
        //Setup options for ImageLoader so it will handle caching for us.
        options = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mOneRow = LayoutInflater.from( mContext).inflate(R.layout.song,parent,false);
        ViewHolder viewHolder = new ViewHolder( mOneRow);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        //Initially we want the progress indicator visible, and the image invisible
        holder.progressBar.setVisibility(View.VISIBLE);
        holder.mThumb_urlImageView.setVisibility(View.INVISIBLE);

        //Setup a listener we can use to swtich from the loading indicator to the Image once it's ready
        final ImageLoadingListener listener = new ImageLoadingListener(){

            @Override
            public void onLoadingStarted(String arg0, View arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onLoadingCancelled(String arg0, View arg1) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
                holder.progressBar.setVisibility(View.INVISIBLE);
                holder.mThumb_urlImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                // TODO Auto-generated method stub

            }

        };
        final SongSite stackSite = list.get(position);
        holder.mIdTextView.setText(stackSite.getId());
        holder.mTitleTextView.setText(stackSite.getTitle());
        holder.mArtistTextView.setText(stackSite.getArtist());
        holder.mDurationTextView.setText(stackSite.getDuration());
        mImageLoader.displayImage(stackSite.getThumb_url(), holder.mThumb_urlImageView, options, listener);
        holder.mThumb_urlImageView.setTag(holder);
        mOneRow.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongSite listPosition = list.get(position);
                //iterface method for going to next activity
                 communicator.toCommunicate(listPosition);

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }



}