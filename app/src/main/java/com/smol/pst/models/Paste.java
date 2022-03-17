package com.smol.pst.models;

import android.util.Log;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.smol.pst.misc.Requester;

import java.util.ArrayList;
import java.util.List;

public class Paste extends PasteListItem implements iSettable
{
    //in super super:
    //  Boolean success

    //in super:
    //  String id, String description, Integer views, String createdAt

    public Boolean encrypted;
    public String expiresAt;
    public List<Section> sections = new ArrayList<>();

    public boolean isSet = false;

    //parentFrag


    /* parent frag constructor

    public Paste()

    {

    }

    */

    public Paste()
    {

    }


    @Override
    public void onResponse(String response)
    {
        String json = response;

        //Log.d("web", "pasteList success response: \n" + json);

        //try to save to prefs
/*
        if(parentFrag != null)
            try {
                PrefWriter.setPasteList(parentFrag.getContext(), json);
            }catch(Exception ex) {
                Log.d("error", "error 421 in pastelist response callback - couldn't save pastelist to prefs (probably no context to parentFrag)");
            }
*/

        PasteResponse pr = new PasteResponse();
        pr = Requester.gson.fromJson(json, pr.getClass());

        Paste p = pr.paste;

 //


        set(p);

    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        try
        {
            Log.d("web", "error 142 in Paste - " + error.networkResponse.statusCode + ": " + new String(error.networkResponse.data, "utf-8"));
        }catch(Exception exc)
        {
            Log.d("error", "Paste error - " + exc.getMessage());
        }
    }

    @Override
    public void set(iSettable iSettable)
    {

        Paste p = (Paste) iSettable;


        this.success = p.success;
        this.id = p.id;
        this.description = p.description;
        this.views = p.views;
        this.createdAt = p.createdAt;
        this.encrypted = p.encrypted;
        this.expiresAt = p.expiresAt;
        this.sections = p.sections;

        PasteStore.selectedPaste = this;
        this.isSet = true;



        Log.d("set", "paste set - \n" + p.toString());

    }

    @Override
    public String toString()
    {
        return "Paste{" +
                "encrypted=" + encrypted +
                ", expiresAt='" + expiresAt + '\'' +
                ", sections=" + sections +
                ", isSet=" + isSet +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", views=" + views +
                ", createdAt='" + createdAt + '\'' +
                ", success=" + success +
                '}';
    }
}
