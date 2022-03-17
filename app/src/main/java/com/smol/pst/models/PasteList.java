package com.smol.pst.models;

import android.util.Log;

import androidx.fragment.app.Fragment;

import com.android.volley.VolleyError;
import com.smol.pst.misc.PrefWriter;
import com.smol.pst.misc.Requester;
import com.smol.pst.ui.Home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class PasteList extends Response implements iSettable
{
    //in super:
    //  Boolean success

    public int currentPage;
    public List<PasteListItem> data = new ArrayList<>();
    public String firstPageUrl;
    public int from;
    public int lastPage;
    public String lastPageUrl;
    public String nextPageUrl;
    public String path;
    public int perPage;
    public Object prevPageUrl;
    public int to;
    public int total;

    //not serialized
    public transient HomeFragment parentFrag;

    //pass frag to update UI on the same screen
    public PasteList(HomeFragment parentFrag)
    {
        this.parentFrag = parentFrag;
    }

    public PasteList()
    {

    }


    @Override
    public void onResponse(String response)
    {
        String json = response;

        //Log.d("web", "pasteList success response: \n" + json);

        //try to save to prefs
        if(parentFrag != null)
        try {
            PrefWriter.setPasteList(parentFrag.getContext(), json);
        }catch(Exception ex) {
            Log.d("error", "error 421 in pastelist response callback - couldn't save pastelist to prefs (probably no context to parentFrag)");
        }



        PasteList pl = new PasteList();
        pl = Requester.gson.fromJson(json, pl.getClass());


        set(pl);


    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        try
        {
            Log.d("web", "pasteList error " + error.networkResponse.statusCode + ": " + new String(error.networkResponse.data, "utf-8"));
        }catch(Exception exc)
        {
            Log.d("error", "ERROR 140 in pastelist - " + exc.getMessage());
        }
    }


    @Override
    public void set(iSettable iSettable)
    {
        PasteList  pl = (PasteList) iSettable;



        this.success = true;
        this.currentPage = pl.currentPage;
        this.data = pl.data;
        this.firstPageUrl = pl.firstPageUrl;
        this.from = pl.from;
        this.lastPage = pl.lastPage;
        this.lastPageUrl = pl.lastPageUrl;
        this.nextPageUrl = pl.nextPageUrl;
        this.path = pl.path;
        this.perPage = pl.perPage;
        this.prevPageUrl = pl.prevPageUrl;
        this.to = pl.to;
        this.total = pl.total;

        PasteStore.pasteList = this;
        PasteStore.isSet = true;

        //PrefWriter.setPasteList(parentFrag.getContext() );

        //update same page adapter
        //( (HomeFragment) parentFrag).initAdapters();

        Log.d("set", "pastelist set - \n" + toString());
    }


/*
    @Override
    public String toString()
    {
        return "PasteList{" +
                "currentPage=" + currentPage +
                ", data=" + data.toString() +
                ", firstPageUrl='" + firstPageUrl.toString() + '\'' +
                ", from=" + from +
                ", lastPage=" + lastPage +
                ", lastPageUrl='" + lastPageUrl.toString() + '\'' +
                ", nextPageUrl='" + nextPageUrl.toString() + '\'' +
                ", path='" + path.toString() + '\'' +
                ", perPage=" + perPage +
                ", prevPageUrl=" + prevPageUrl.toString() +
                ", to=" + to +
                ", total=" + total +
                '}';
    }
*/

}
