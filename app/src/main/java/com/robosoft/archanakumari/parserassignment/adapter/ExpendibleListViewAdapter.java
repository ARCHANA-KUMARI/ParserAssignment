package com.robosoft.archanakumari.parserassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.robosoft.archanakumari.parserassignment.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by archanakumari on 15/12/15.
 */
public class ExpendibleListViewAdapter extends BaseExpandableListAdapter {

    private Context mCtx;
    private HashMap<String,List<String>> mAndroid_categories;
    private List<String> mAndroidItem_list;

    public ExpendibleListViewAdapter(Context mCtx, HashMap<String, List<String>> mAndroid_categories, List<String> mAndroidItem_list){
        this.mCtx=mCtx;
        this.mAndroid_categories = mAndroid_categories;
        this.mAndroidItem_list = mAndroidItem_list;
    }

    @Override
    public int getGroupCount() {
        return  mAndroidItem_list.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mAndroid_categories.get( mAndroidItem_list.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return  mAndroidItem_list.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mAndroid_categories.get( mAndroidItem_list.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parentView) {
        String group_title = (String) getGroup(groupPosition);
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_layout,parentView,false);
        }
        TextView parentTextView = (TextView) convertView.findViewById(R.id.parenttext);
        parentTextView.setText(group_title);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parentView) {
        String child_title = (String) getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) mCtx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.child_layout,parentView,false);
        }
        TextView childTextView = (TextView) convertView.findViewById(R.id.childtext);
        childTextView.setText(child_title);
        return  convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
