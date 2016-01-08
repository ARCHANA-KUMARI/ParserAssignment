package com.robosoft.archanakumari.parserassignment.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by archanakumari on 16/12/15.
 */
public class ListWrapper {

    @SerializedName("android")
    public List<ListItem> userList;
}

