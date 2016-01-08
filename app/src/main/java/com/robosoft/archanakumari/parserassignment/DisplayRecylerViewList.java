package com.robosoft.archanakumari.parserassignment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.robosoft.archanakumari.parserassignment.Modal.SongSite;

public class DisplayRecylerViewList extends AppCompatActivity {

    private ImageView mThumb_UrlImageView;
    private TextView mTitle;
    private TextView mArtist;
    private TextView mDuration;
    ImageLoader imageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_display);

         mTitle = (TextView) findViewById(R.id.title);
         mArtist = (TextView) findViewById(R.id.artist);
         mDuration = (TextView) findViewById(R.id.duration);
         mThumb_UrlImageView = (ImageView) findViewById(R.id.thumb_url);
         Intent intent = getIntent();
         SongSite songSite = (SongSite) intent.getSerializableExtra("Data");
         ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
         imageLoader = ImageLoader.getInstance();
         imageLoader.init(config);
         imageLoader.displayImage(songSite.getThumb_url(), mThumb_UrlImageView);
         mTitle.setText(songSite.getTitle());
         mArtist.setText(songSite.getArtist());
         mDuration.setText(songSite.getDuration());
         Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
         setSupportActionBar(toolbar);
         getSupportActionBar().setHomeButtonEnabled(true);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id==android.R.id.home){
            onBackPressed();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

}
