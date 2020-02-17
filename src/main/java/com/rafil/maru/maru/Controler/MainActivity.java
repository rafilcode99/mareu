package com.rafil.maru.maru.Controler;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.LogPrinter;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.rafil.maru.R;
import com.rafil.maru.maru.DI.DI;
import com.rafil.maru.maru.Model.Reunion;
import com.rafil.maru.maru.service.ReunionApiService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView myRecyclerView;
    private List<Reunion> mReunionsList;
    private ReunionApiService mApiService;

    private FloatingActionButton mAddButton;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = DI.getReunionApiService();

        myRecyclerView = findViewById(R.id.reunion_recyclerview1);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initList();


        mAddButton = findViewById(R.id.new_reunion_btn);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment;
                fragment = AddReunionFragment.newDialogInstance();
                fragment.show(getSupportFragmentManager(), "Add new reunion");
            }
        });










    }

    private void initList(){
        mReunionsList = mApiService.getReunions();
        myRecyclerView.setAdapter(new ReunionsListRecyclerViewAdapter((ArrayList<Reunion>) mReunionsList));

    }




    @Override
    protected void onStart() {
        super.onStart();
        initList();

    }

    @Override
    protected void onResume() {
        super.onResume();
        initList();
    }


}
