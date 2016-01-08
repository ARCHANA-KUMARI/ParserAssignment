package com.robosoft.archanakumari.parserassignment.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.robosoft.archanakumari.parserassignment.MainActivity;
import com.robosoft.archanakumari.parserassignment.R;
import com.robosoft.archanakumari.parserassignment.adapter.ExpendibleListViewAdapter;
import com.robosoft.archanakumari.parserassignment.dto.ListItem;
import com.robosoft.archanakumari.parserassignment.dto.ListWrapper;
import com.robosoft.archanakumari.parserassignment.manager.DownloadManager;
import com.robosoft.archanakumari.parserassignment.network.VolleyHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by archanakumari on 16/12/15.
 */
public class JsonFragment extends Fragment {

    View view;
    private android.support.v7.app.ActionBar mActionBar;
    private String mUrl;
    ExpandableListView expandableListView;
    List<String> versionList = new ArrayList<String>();
    HashMap<String,List<String>>  androidCategory = new HashMap<String,List<String>>();
    ExpendibleListViewAdapter adapter;

    public JsonFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         view = inflater.inflate(R.layout.jsonfragment, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.exp_list);
        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        downoadData();
        mActionBar = ((MainActivity)getActivity()).getSupportActionBar();
        expandableListView.setOnTouchListener(new View.OnTouchListener() {
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

                } else if (this.height > height) {
                    if (mActionBar.isShowing()) {
                        mActionBar.hide();

                    }
                }
                return false;
            }
        });

    }
    private void downoadData() {

        mUrl = "http://api.learn2crack.com/android/jsonos/";
        DownloadManager.downloadData(getActivity(), mUrl, new Response.Listener<ListWrapper>() {

            @Override
            public void onResponse(ListWrapper response) {

                if (getActivity() == null)
                     return;
                List<ListItem> listItem = response.userList;
                int size = listItem.size();
                for(int i=0;i<size;i++){
                    List<String> versionDetails = new ArrayList<String>();
                    ListItem item = listItem.get(i);
                    versionList.add(item.name);         //Cupcake name of list
                    versionDetails.add(item.ver);       //subitem of list i.e version
                    versionDetails.add(item.api);      //subitem of list i.e. api
                    androidCategory.put(item.name,versionDetails);//put key value pair i.e item.name = Cupcake and VersionDetails = list of subitem

                }
                adapter =  new ExpendibleListViewAdapter(view.getContext(), androidCategory,versionList);
                expandableListView.setAdapter(adapter);
                expandableListView.expandGroup(0);


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (getActivity() == null)
                     return;
            }


        });
    }
    @Override

    public void onDetach() {

        super.onDetach();
        VolleyHelper.getInstance(getActivity()).cancelRequest(mUrl);
    }
}
