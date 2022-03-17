package com.smol.pst.ui.Home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.smol.pst.adapters.AdapterPasteList;
import com.smol.pst.databinding.FragmentHomeBinding;
import com.smol.pst.misc.PrefWriter;
import com.smol.pst.models.PasteStore;
import com.smol.pst.misc.Requester;
import com.smol.pst.models.PasteList;

public class HomeFragment extends Fragment
{

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        if(! Requester.isInit )
            Requester.init(getContext());

        PasteStore.init(getContext(), this);


        if(PrefWriter.isPrefSet(getContext(), "prefPasteList"))
        {
            String j = PrefWriter.readPref(getContext(),"prefPasteList");

/*
            //
            // ??
            //
            PasteStore.pasteList = new Gson().fromJson(j, PasteList.class);
            PasteStore.isSet = true;
            initAdapters();
            PasteStore.pasteList.parentFrag = this;

 */
            Requester.getSet(getContext(),"https://api.paste.ee/v1/pastes", PasteStore.pasteList);
        }

        if(! PrefWriter.isPrefSet(getContext(), "prefPasteList"))
        {
            Requester.getSet(getContext(),"https://api.paste.ee/v1/pastes", new PasteList(this));
        }









        //todo:
        //make a case for each scenario...





        if(PasteStore.isSet)
        {
            initAdapters();
        }











        return root;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }



    public void initAdapters()
    {
        AdapterPasteList adapterPasteList = new AdapterPasteList(getContext(), PasteStore.pasteList.data, this);
        binding.recycPasteList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recycPasteList.setAdapter(adapterPasteList);
        if(PasteStore.pasteList.data.size() > 0)
            binding.txtNumPastes.setText(PasteStore.pasteList.total + " pastes found");

    }





}