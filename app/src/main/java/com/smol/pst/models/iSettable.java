package com.smol.pst.models;

public interface iSettable extends com.android.volley.Response.Listener<String>, com.android.volley.Response.ErrorListener
{
    public void set(iSettable iSettable);
}
