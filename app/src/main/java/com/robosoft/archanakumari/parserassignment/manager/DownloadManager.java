package com.robosoft.archanakumari.parserassignment.manager;

import android.content.Context;

import com.android.volley.Response;
import com.robosoft.archanakumari.parserassignment.dto.ListWrapper;
import com.robosoft.archanakumari.parserassignment.network.GsonRequest;
import com.robosoft.archanakumari.parserassignment.network.VolleyHelper;

/**
 * Created by archanakumari on 15/12/15.
 */
public class DownloadManager {

    public static void downloadData(Context ctx,String url,Response.Listener<ListWrapper> listener,Response.ErrorListener errorListener){
        GsonRequest<ListWrapper> request = new GsonRequest<ListWrapper>(url,ListWrapper.class,listener,errorListener);
        request.setTag(url);
        VolleyHelper.getInstance(ctx).addToRequestQueue(request);

    }


}
