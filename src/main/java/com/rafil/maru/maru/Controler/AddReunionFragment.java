package com.rafil.maru.maru.Controler;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.rafil.maru.R;
import com.rafil.maru.maru.DI.DI;
import com.rafil.maru.maru.Model.Reunion;
import com.rafil.maru.maru.service.ReunionApiService;

import java.util.ArrayList;
import java.util.Arrays;


public class AddReunionFragment extends DialogFragment {

    private ReunionApiService mApiService;
    private ArrayList<Reunion> mReunions;

    public static AddReunionFragment newDialogInstance(){
        AddReunionFragment dialog = new AddReunionFragment();
        return dialog;
    }



    private Toolbar mToolbar;
    private ImageButton mReturnButton;
    private TextView mTextView;

    private EditText mSubjectInput;
    private EditText mDateInput;
    private EditText mLocationInput;
    private EditText mParticipantsInput;

    private Button mAddButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.new_reunion_layout, container, false);
        mToolbar = view.findViewById(R.id.toolbar_new);
        mReturnButton = view.findViewById(R.id.return_btn);
        mTextView = view.findViewById(R.id.toolbar_txt);

        mSubjectInput = view.findViewById(R.id.subject_input);
        mDateInput = view.findViewById(R.id.date_input);
        mLocationInput = view.findViewById(R.id.location_input);
        mParticipantsInput = view.findViewById(R.id.participants_input);

        mAddButton = view.findViewById(R.id.add_btn);

        mApiService = DI.getNewInstanceApiService();



        mReturnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exit();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Reunion newReunion = new Reunion(mDateInput.getText().toString(), mLocationInput.getText().toString(), mSubjectInput.getText().toString(), new ArrayList(Arrays.asList("djil", "silé")));
                mApiService.createReunion(newReunion);

                dismiss();




            }
        });






        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();

        if (dialog != null){
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }

    }


    public void exit(){
        getDialog().cancel();

    }
}
