package com.robosoft.archanakumari.parserassignment.fragments;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.robosoft.archanakumari.parserassignment.MainActivity;
import com.robosoft.archanakumari.parserassignment.R;
import com.robosoft.archanakumari.parserassignment.adapter.ListViewAdapter;
import com.robosoft.archanakumari.parserassignment.manager.Downloader;
import com.robosoft.archanakumari.parserassignment.network.SitesXmlPullParser;

import java.io.FileNotFoundException;

/**
 * Created by archanakumari on 16/12/15.
 */
public class SongFragmentForListView extends Fragment {

    ListView listView;
    View view;
    Context context;
    private ListViewAdapter mAdapter;
    private android.support.v7.app.ActionBar mActionBar;

    public SongFragmentForListView() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_xml, container, false);
        listView = (ListView) view.findViewById(R.id.list);
        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(isNetworkAvailable()){
            SitesDownloadTask download = new SitesDownloadTask();
            download.execute();
        }else{
            mAdapter = new ListViewAdapter(context, -1, SitesXmlPullParser.getStackSitesFromFile(context));
            listView.setAdapter(mAdapter);
        }
        mAdapter = new ListViewAdapter(context, -1, SitesXmlPullParser.getStackSitesFromFile(context));
        listView.setAdapter(mAdapter);
        mActionBar = ((MainActivity)getActivity()).getSupportActionBar();
        listView.setOnTouchListener(new View.OnTouchListener() {
            float height;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                float height = event.getY();
                if (action == MotionEvent.ACTION_DOWN) {
                    this.height = height;
                } else if (action == MotionEvent.ACTION_UP) {
                    if (this.height < height) {
                        if (!mActionBar.isShowing()) {
                            mActionBar.show();
                        }
                    }

                } else if (this.height > height){

                    if (mActionBar.isShowing()) {
                        mActionBar.hide();

                    }
                }
                return false;
            }
        });


    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private class SitesDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl("http://api.androidhive.info/music/music.xml",context.openFileOutput("SongSites.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //setup our Adapter and set it to the ListView.
            mAdapter = new ListViewAdapter(context, -1, SitesXmlPullParser.getStackSitesFromFile(context));
            listView.setAdapter(mAdapter);
        }
    }


}
