package com.robosoft.archanakumari.parserassignment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.robosoft.archanakumari.parserassignment.Modal.SongSite;
import com.robosoft.archanakumari.parserassignment.R;

import java.util.List;

/**
 * Created by archanakumari on 16/12/15.
 */
public class ListViewAdapter extends ArrayAdapter<SongSite> {

    private  ImageLoader mImageLoader;
    DisplayImageOptions options;

    public ListViewAdapter(Context ctx, int textViewResourceId, List<SongSite> sites) {
        super(ctx, textViewResourceId, sites);

        //Setup the ImageLoader, we'll use this to display our images
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ctx).build();
        mImageLoader = ImageLoader.getInstance();
        mImageLoader.init(config);

        //Setup options for ImageLoader so it will handle caching for us.
        options = new DisplayImageOptions.Builder()
                .cacheInMemory()
                .cacheOnDisc()
                .build();

    }


     /*
       This method is responsible for creating row views out of a SongSite object that can be put
       into our ListView
      */

    @Override
    public View getView(int pos, View convertView, ViewGroup parent){
        RelativeLayout row = (RelativeLayout)convertView;
        if(null == row){
            //No recycled View, we have to inflate one.
            LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (RelativeLayout) inflater.inflate(R.layout.song_sites, null);
        }

        //Get our View References
        TextView idText = (TextView)row.findViewById(R.id.id);
        TextView titleText = (TextView) row.findViewById(R.id.title);
        TextView artistText = (TextView) row.findViewById(R.id.artist);
        TextView durationText = (TextView) row.findViewById(R.id.duration);
        final ImageView thumb_urlImageView = (ImageView)row.findViewById(R.id.thumb_url);
        final ProgressBar indicator = (ProgressBar)row.findViewById(R.id.progress);

        //Initially we want the progress indicator visible, and the image invisible
        indicator.setVisibility(View.VISIBLE);
        thumb_urlImageView.setVisibility(View.INVISIBLE);

        //Setup a listener we can use to swtich from the loading indicator to the Image once it's ready
        ImageLoadingListener listener = new ImageLoadingListener(){

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
                indicator.setVisibility(View.INVISIBLE);
                thumb_urlImageView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                // TODO Auto-generated method stub

            }

        };

        //Set the relavent text in our TextViews
        idText.setText(getItem(pos).getId());
        titleText.setText(getItem(pos).getTitle());
        artistText.setText(getItem(pos).getArtist());
        durationText.setText(getItem(pos).getDuration());
        //Load image through url and display using displayImage method.
        mImageLoader.displayImage(getItem(pos).getThumb_url(), thumb_urlImageView, options, listener);
        return row;


    }

}
