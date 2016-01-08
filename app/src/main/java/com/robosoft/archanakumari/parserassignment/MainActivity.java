package com.robosoft.archanakumari.parserassignment;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.robosoft.archanakumari.parserassignment.Modal.SongSite;
import com.robosoft.archanakumari.parserassignment.adapter.MyPagerAdapter;
import com.robosoft.archanakumari.parserassignment.dto.Communicator;
import com.robosoft.archanakumari.parserassignment.tabs.SlidingTabLayout;

public class MainActivity extends AppCompatActivity implements Communicator{


    Toolbar toolbar;
    private ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;
    SongSite songSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setIcon(R.drawable.toolimage);
        getSupportActionBar().setIcon(R.drawable.download);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(),this));
        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slidetab);
        slidingTabLayout.setViewPager(viewPager);

    }

    @Override
    public void toCommunicate(SongSite songSite) {
        Intent intent = new Intent( this, DisplayRecylerViewList.class);
        intent.putExtra("Data",songSite);
        startActivity(intent);
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // MenuInflater menuInflater = getMenuInflater();
        //menuInflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }*/
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.menu_main, menu);
      return true;
  }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(getBaseContext(),"Hi i am in onOption Method",Toast.LENGTH_LONG).show();
        int id = item.getItemId();
        if(id==R.id.next)
        {
                Toast.makeText(getBaseContext(),"Hi i am in onOption Method",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this,ConnectToFacebookActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }
}