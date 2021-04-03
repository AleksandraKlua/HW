package com.example.lesson1.fragments;

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

import com.example.lesson1.R;

public class CustomDialogFragment extends DialogFragment implements View.OnClickListener{

    @SuppressLint("InflateParams")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_dialog, null);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceBundle){
        super.onViewCreated(view, savedInstanceBundle);

        view.findViewById(R.id.btn_ok).setOnClickListener(this);

        TextView messageText = view.findViewById(R.id.message);
        String strMessage = getArguments().getString("message");
        messageText.setText(strMessage);
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
