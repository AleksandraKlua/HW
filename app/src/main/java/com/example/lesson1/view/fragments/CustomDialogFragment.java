package com.example.lesson1.view.fragments;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.lesson1.R;
import com.example.lesson1.viewmodel.FragmentViewModel;

public class CustomDialogFragment extends DialogFragment implements View.OnClickListener{

    @SuppressLint("InflateParams")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_dialog, null);
    }

    @SuppressLint("FragmentLiveDataObserve")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceBundle){
        super.onViewCreated(view, savedInstanceBundle);
        FragmentViewModel fragmentViewModel = ViewModelProviders.of(requireActivity()).get(FragmentViewModel.class);

        view.findViewById(R.id.btn_ok).setOnClickListener(this);

        TextView messageText = view.findViewById(R.id.message);
        fragmentViewModel.getMessage().observe(this, str ->{
            str = fragmentViewModel.getStringMessage();
            messageText.setText(str);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(params);
    }

    public void onClick(View v){
        dismiss();
    }

    public void onDismiss(@NonNull DialogInterface dialog){
        super.onCancel(dialog);
    }
}