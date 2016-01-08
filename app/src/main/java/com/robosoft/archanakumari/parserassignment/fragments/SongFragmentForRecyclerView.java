package com.robosoft.archanakumari.parserassignment.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.robosoft.archanakumari.parserassignment.MainActivity;
import com.robosoft.archanakumari.parserassignment.Modal.SongSite;
import com.robosoft.archanakumari.parserassignment.R;
import com.robosoft.archanakumari.parserassignment.adapter.RecyclerViewAdapter;
import com.robosoft.archanakumari.parserassignment.manager.Downloader;
import com.robosoft.archanakumari.parserassignment.network.SitesXmlPullParser;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by archanakumari on 16/12/15.
 */
public class SongFragmentForRecyclerView extends Fragment {

    private android.support.v7.app.ActionBar mActionBar;
    RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView mRecyclerView;
    View view;
    Context context;
    List<SongSite> songSiteList;

    public SongFragmentForRecyclerView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = container.getContext();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_song, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        if(isNetworkAvailable()){
            SitesDownloadTask download = new SitesDownloadTask();
            download.execute();
        }else{
            songSiteList = SitesXmlPullParser.getStackSitesFromFile(context);
            recyclerViewAdapter = new RecyclerViewAdapter((Activity) context, SitesXmlPullParser.getStackSitesFromFile(context));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(recyclerViewAdapter);
        }


        songSiteList = SitesXmlPullParser.getStackSitesFromFile(context);
        recyclerViewAdapter = new RecyclerViewAdapter(context,SitesXmlPullParser.getStackSitesFromFile(context));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(recyclerViewAdapter);
        mActionBar = ((MainActivity)getActivity()).getSupportActionBar();
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
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
    private class SitesDownloadTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl("http://api.androidhive.info/music/music.xml", context.openFileOutput("SongSites.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //setup our Adapter and set it to the the Recycler.
            recyclerViewAdapter = new RecyclerViewAdapter((Activity) context,SitesXmlPullParser.getStackSitesFromFile(context));
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(recyclerViewAdapter);
            ItemTouchHelper swipeToDismissTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    // callback for drag-n-drop, false to skip this feature
                    return false;
                }

                @Override
                public void onSwiped(final RecyclerView.ViewHolder viewHolder, int direction) {
                    // callback for swipe to dismiss, removing item from data and adapter

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("User be alert");
                    alertDialogBuilder.setMessage("Are you sure to remove this item?");
                    alertDialogBuilder.setPositiveButton("remove", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {

                            songSiteList.remove(viewHolder.getAdapterPosition());
                            recyclerViewAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                        }
                    });
                    alertDialogBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            clearView(mRecyclerView, viewHolder);
                        }
                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            });
            swipeToDismissTouchHelper.attachToRecyclerView(mRecyclerView);

        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
