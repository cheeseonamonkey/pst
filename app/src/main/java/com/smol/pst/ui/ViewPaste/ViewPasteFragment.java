package com.smol.pst.ui.ViewPaste;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smol.pst.databinding.FragmentViewPasteBinding;
import com.smol.pst.misc.Requester;
import com.smol.pst.models.PasteStore;

public class ViewPasteFragment extends Fragment
{

    private ViewPasteViewModel mViewModel;
    private FragmentViewPasteBinding binding;

    public static ViewPasteFragment newInstance()
    {
        return new ViewPasteFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        //Inflate the layout for this fragment:
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//private Binding binding;

        binding = FragmentViewPasteBinding.inflate(getLayoutInflater(), container,false);

        View view = binding.getRoot();

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//=========
//INIT:


        if(! Requester.isInit )
            Requester.init(getContext());


        if(PasteStore.selectedPaste.isSet)
        {
            binding.txtPasteId.setText(PasteStore.selectedPaste.id);
        }


//=========
//LISTENERS:





//=========







//return bound view
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ViewPasteViewModel.class);
        // TODO: Use the ViewModel
    }

}