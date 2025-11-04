package com.example.vitaApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.vitaApp.adapters.EyeViewHolder;
import com.example.vitaApp.models.EyeModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class DiseasesActivity extends AppCompatActivity {

    RelativeLayout contentView;
    private RecyclerView eyeRecyclerView, nailRecyclerView;
    private DatabaseReference eyeRef, nailRef;
    private LinearLayout cropsProgressBar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diseases);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        eyeRef = FirebaseDatabase.getInstance().getReference().child("EyeDiseases");
        eyeRecyclerView = findViewById(R.id.eye_recyclerView);
        eyeRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        eyeRecyclerView.setLayoutManager(layoutManager);


        nailRef = FirebaseDatabase.getInstance().getReference().child("NailDiseases");
        nailRecyclerView = findViewById(R.id.nail_recyclerView);
        nailRecyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        nailRecyclerView.setLayoutManager(layoutManager1);

        cropsProgressBar = findViewById(R.id.crops_progree_bar);
        cropsProgressBar.setVisibility(View.VISIBLE);

        eyeDetails();
        nailDetails();


        contentView = findViewById(R.id.content_view);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.diseases);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.home:
                                DiseasesActivity.this.startActivity(new Intent(DiseasesActivity.this,
                                        MainActivity.class));
                                DiseasesActivity.this.finish();
                                DiseasesActivity.this.overridePendingTransition(0, 0);
                                return true;
                            case R.id.diseases:
                                return true;
                            case R.id.profile:
                                DiseasesActivity.this.startActivity(new Intent(DiseasesActivity.this,
                                        ProfileActivity.class));
                                DiseasesActivity.this.finish();
                                DiseasesActivity.this.overridePendingTransition(0, 0);
                                return true;
                        }
                        return false;
                    }
                });
    }



    private void nailDetails() {
        FirebaseRecyclerOptions<EyeModel> options = new FirebaseRecyclerOptions.Builder<EyeModel>()
                .setQuery(nailRef, EyeModel.class)
                .build();
        FirebaseRecyclerAdapter<EyeModel, EyeViewHolder> nailAdapter = new FirebaseRecyclerAdapter<EyeModel,
                EyeViewHolder>(options) {
            @NonNull
            @Override
            public EyeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eye_items_layout, parent,
                        false);
                EyeViewHolder holder = new EyeViewHolder(view);
                return holder;
            }

            @Override
            protected void onBindViewHolder(@NonNull EyeViewHolder holder, final int position,
                                            @NonNull final EyeModel model) {

                cropsProgressBar.setVisibility(View.GONE);
                holder.name.setText(model.getTitle());
                Picasso.get().load(model.getImage()).into(holder.image);
                //Glide.with(HomeActivity.this).load(model.getImage()).into(holder.image);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DiseasesActivity.this, NailDetailsActivity.class);
                        intent.putExtra("cId", model.getcId());
                        startActivity(intent);

                    }
                });
            }
        };

        nailRecyclerView.setAdapter(nailAdapter);
        nailAdapter.startListening();
    }

    private void eyeDetails() {
        FirebaseRecyclerOptions<EyeModel> options = new FirebaseRecyclerOptions.Builder<EyeModel>()
                .setQuery(eyeRef, EyeModel.class)
                .build();
        FirebaseRecyclerAdapter<EyeModel, EyeViewHolder> eyeAdapter = new FirebaseRecyclerAdapter<EyeModel,
                EyeViewHolder>(options) {
            @NonNull
            @Override
            public EyeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.eye_items_layout, parent,
                        false);
                EyeViewHolder holder = new EyeViewHolder(view);
                return holder;
            }

            @Override
            protected void onBindViewHolder(@NonNull EyeViewHolder holder, final int position,
                                            @NonNull final EyeModel model) {

                cropsProgressBar.setVisibility(View.GONE);
                holder.name.setText(model.getTitle());
                Picasso.get().load(model.getImage()).into(holder.image);
                //Glide.with(HomeActivity.this).load(model.getImage()).into(holder.image);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DiseasesActivity.this, EyeDetailsActivity.class);
                        intent.putExtra("cId", model.getcId());
                        startActivity(intent);

                    }
                });
            }
        };

        eyeRecyclerView.setAdapter(eyeAdapter);
        eyeAdapter.startListening();
    }
}