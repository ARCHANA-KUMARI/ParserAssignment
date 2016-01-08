package com.robosoft.archanakumari.parserassignment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayListViewByXml extends AppCompatActivity {

    private ImageView mThumb_UrlImageView;
    private TextView mTitle;
    private TextView mArtist;
    private TextView mDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_display_list_view_by_xml);
            mTitle = (TextView) findViewById(R.id.title);
            mArtist = (TextView) findViewById(R.id.artist);
            mDuration = (TextView) findViewById(R.id.duration);
            mThumb_UrlImageView = (ImageView) findViewById(R.id.thumb_url);
            mThumb_UrlImageView = (ImageView) findViewById(R.id.thumb_url);
            Bundle bundle = getIntent().getExtras();
            mTitle.setText(bundle.getString("Title"));
            mArtist.setText(bundle.getString("Artist"));
            mDuration.setText(bundle.getString("Duration"));
            Bitmap bitmap = (Bitmap)bundle.getParcelable("ImageBitMap");
            mThumb_UrlImageView.setImageBitmap(bitmap);
            Toolbar toolbar = (Toolbar) findViewById(R.id.appbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
