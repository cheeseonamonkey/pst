package com.smol.pst.models;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.smol.pst.MainActivity;
import com.smol.pst.databinding.ActivityMainBinding;
import com.smol.pst.databinding.FragmentHomeBinding;
import com.smol.pst.misc.PrefWriter;
import com.smol.pst.models.PasteList;
import com.smol.pst.ui.Home.HomeFragment;

import java.util.ArrayList;
import java.util.Map;

public class PasteStore
{

    public static boolean isInit = false;

    public static PasteList pasteList = new PasteList();

    public static Paste selectedPaste = new Paste();
    //public static Map<> pastes =

    public static boolean isSet = false;

    public static void init(Context context, HomeFragment homeFragment)
    {
        Log.d("init", "init pasteStore");


        if(PrefWriter.isPrefSet(context, "prefPasteList"))
        {
            Log.d("init", "init pastestore - found saved pref!");


            try {
                String j = PrefWriter.readPref(context,"prefPasteList");
                pasteList = new Gson().fromJson(j, pasteList.getClass());
                Log.d("init", "init pastestore - pastelist set");
                homeFragment.initAdapters();
                Log.d("init", "init pastestore - adapters set");


            }catch(Exception ex) {
                Log.e("err", "init error in PasteSTore: " + ex.getMessage() + "- \n(handled - set pastelist to new object)");
                pasteList = new PasteList();

            }
        }else
        {
            Log.d("init", "init - saved pastelist not found, creating new pastelist obj");
            pasteList = new PasteList();
        }



    }


}
