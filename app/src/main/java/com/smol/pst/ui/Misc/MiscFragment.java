package com.smol.pst.ui.Misc;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import android.util.Log;

import com.smol.pst.R;
import com.smol.pst.misc.PrefWriter;

public class MiscFragment extends PreferenceFragmentCompat
{

    private MiscViewModel mViewModel;

    public static MiscFragment newInstance()
    {
        return new MiscFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //init
        setDynamicPreferences();
        Log.d("init", "init prefs");

    }



    void setDynamicPreferences()
    {
        if(PrefWriter.isPrefSet(getContext(),"apiKey"))
            findPreference("prefAccessToken").setSummary(PrefWriter.readPref(getContext(),"apiKey"));
        else
            findPreference("prefAccessToken").setSummary("API key not set!");



    }


    @Override
    public void onCreatePreferences(@Nullable Bundle savedInstanceState, @Nullable String rootKey)
    {
        setPreferencesFromResource(R.xml.preferences_main, rootKey);

        setDynamicPreferences();


        findPreference("prefSignIn").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
        {
            @Override
            public boolean onPreferenceClick(@NonNull Preference preference)
            {
                Intent webLinkIntent = new Intent(Intent.ACTION_VIEW);
                webLinkIntent.setData(Uri.parse("https://paste.ee/account/api/authorize/aKXMiuQbOBVUkRF7dYtmMquiKbg8NdKjc0vGoBvB3"));
                startActivity(webLinkIntent);

                return true;
            }
        });




        findPreference("prefApiKey").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
        {
            @Override
            public boolean onPreferenceChange(@NonNull Preference preference, Object newValue)
            {

                PrefWriter.setApiKey(getContext(), newValue.toString() );
                setDynamicPreferences();
                return true;
            }
        });

    }




    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MiscViewModel.class);
        // TODO: Use the ViewModel
    }

}