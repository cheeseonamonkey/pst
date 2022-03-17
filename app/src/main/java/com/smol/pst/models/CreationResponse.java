package com.smol.pst.models;

import android.util.Log;

import com.android.volley.VolleyError;
import com.smol.pst.misc.Requester;

public class CreationResponse extends Response implements iSettable
{
    //in super:
    //  Boolean success

    public String id;
    public String link;


    public CreationResponse()
    {

    }


    @Override
    public void onResponse(String response)
    {
        Log.d("web", "Creation response: " + response);

        CreationResponse cr = Requester.gson.fromJson(response.toString(), this.getClass());

        set(cr);
    }

    @Override
    public void onErrorResponse(VolleyError error)
    {
        try
        {
            Log.d("web", "creation http error " + error.networkResponse.statusCode + ": " + new String(error.networkResponse.data, "utf-8"));
        }catch(Exception exc)
        {
            Log.d("error", "ERROR 145 in CreationResponse - " + exc.getMessage());
        }
    }

    @Override
    public void set(iSettable iSettable)
    {

    }
}
