package com.rafil.maru.maru.Controler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rafil.maru.maru.DI.DI;
import com.rafil.maru.maru.Model.Reunion;
import com.rafil.maru.R;
import com.rafil.maru.maru.service.ReunionApiService;

import java.util.ArrayList;

public class ReunionsListRecyclerViewAdapter extends RecyclerView.Adapter<ReunionsListRecyclerViewAdapter.ReunionsListViewHolder> {

    private ArrayList<Reunion> mReunionsList;
    private ReunionApiService mApiService;


    public ReunionsListRecyclerViewAdapter (ArrayList<Reunion> items) {
        mReunionsList = items;
    }

    @NonNull
    @Override
    public ReunionsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reunion_item_list, parent, false);
        ReunionsListViewHolder holder = new ReunionsListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReunionsListViewHolder holder, int position) {

        final Reunion reunion = mReunionsList.get(position);
        holder.subject.setText(reunion.getSubject());
        holder.Date.setText(reunion.getTime());
        holder.Location.setText(reunion.getLocation());
        String firstLetter = String.valueOf(reunion.getSubject().charAt(0));
        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getColor(reunion);


        TextDrawable drawable = TextDrawable.builder()
                .buildRound(firstLetter, color); // radius in px

        //Glide.with(holder.picture.getContext()).load(drawable).apply(RequestOptions.circleCropTransform()).into(holder.picture);
        holder.picture.setImageDrawable(drawable);


        holder.Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DI.getNewInstanceApiService().deleteReunion(reunion);



            }
        });
        ParticipantsListRecyclerViewAdapter participantsAdapter = new ParticipantsListRecyclerViewAdapter(reunion.getParticipants());
        holder.Participants.setAdapter(participantsAdapter);
        holder.Participants.addItemDecoration(new DividerItemDecoration(holder.Participants.getContext(), DividerItemDecoration.HORIZONTAL));



    }

    @Override
    public int getItemCount() {
        return mReunionsList.size();
    }


    public class ReunionsListViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView subject;
        TextView Location;
        TextView Date;
        ImageButton Delete;
        RecyclerView Participants;
        ConstraintLayout parentLayout;



        public ReunionsListViewHolder(@NonNull View itemView) {
            super(itemView);
            picture= itemView.findViewById(R.id.ItemImageView);
            subject= itemView.findViewById(R.id.subject_txt);
            Location= itemView.findViewById(R.id.location_txt);
            Date= itemView.findViewById(R.id.date_txt);
            Delete= itemView.findViewById(R.id.delete_btn);
            Participants= itemView.findViewById(R.id.emails_recyclerView);
            parentLayout= itemView.findViewById(R.id.parent_itemLayout);



        }
    }
    public  void refresh(Reunion reunion){
        mApiService = DI.getNewInstanceApiService();
        mApiService.getReunions().add(reunion);
        notifyDataSetChanged();
    }

}
