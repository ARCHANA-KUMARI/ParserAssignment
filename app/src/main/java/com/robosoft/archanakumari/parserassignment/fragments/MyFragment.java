package com.robosoft.archanakumari.parserassignment.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.robosoft.archanakumari.parserassignment.R;

/**
 * Created by archanakumari on 15/12/15.
 */
public class MyFragment extends Fragment {

    TextView textView;

    public static MyFragment getInstance(int position){
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("Position",position);
        myFragment.setArguments(bundle);
        return  myFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment,container,false);
        textView = (TextView) view.findViewById(R.id.text);
        Bundle bundle = getArguments();
        if(bundle!=null){
            textView.setText("The page is currently selected"+bundle.getInt("Position"));
        }
        return  view;
    }
}

