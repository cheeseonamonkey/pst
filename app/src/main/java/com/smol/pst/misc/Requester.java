package com.smol.pst.misc;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Requester
{
    static RequestQueue requestQueue;
    public static Gson gson;

    public static boolean isInit = false;

    public static void init(Context context)
    {
        requestQueue = Volley.newRequestQueue(context);
        gson = new Gson();

        Log.d("init", "init Requester");
        isInit = true;
    }

    public static void getSet(Context context, String url, Response.Listener<String> listener)
    {
        //settable param has set() which can be called in the response thread to set with the json response

        StringRequest request = new StringRequest(Request.Method.GET, url, listener, (Response.ErrorListener) listener)
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError
            {

                String apiKey = PrefWriter.readPref( context,"apiKey");

                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("X-Auth-Token", apiKey);

                return headers;
            }
        };

        requestQueue.add(request);

    }


}
