package com.example.vitaApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vitaApp.models.EyeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class NailDetailsActivity extends AppCompatActivity {

    private ImageView mainImageView;
    private TextView mainTitle, mainTitle1, mainSymptoms, mainCause, mainTreatment, mainDescription;
    private String refID = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nail_details);

        refID = getIntent().getStringExtra("cId");

        mainImageView = (ImageView) findViewById(R.id.mainImage);
        mainTitle = (TextView) findViewById(R.id.title);
        mainTitle1 = (TextView) findViewById(R.id.title1);
        mainSymptoms = (TextView) findViewById(R.id.symptoms);
        mainCause = (TextView) findViewById(R.id.cause);
        mainTreatment = (TextView) findViewById(R.id.treatments);
        mainDescription = (TextView) findViewById(R.id.description);

        getDetails(refID);

    }

    private void getDetails(String refID) {

        DatabaseReference cropRef = FirebaseDatabase.getInstance().getReference().child("NailDiseases");

        cropRef.child(refID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    EyeModel cropsModel = dataSnapshot.getValue(EyeModel.class);

                    mainTitle.setText(cropsModel.getTitle());
                    mainTitle1.setText(cropsModel.getTitle());
                    mainSymptoms.setText(cropsModel.getSymptoms());
                    mainCause.setText(cropsModel.getCause());
                    mainTreatment.setText(cropsModel.getTreatments());
                    mainDescription.setText(cropsModel.getDescription());
                    Picasso.get().load(cropsModel.getImage()).into(mainImageView);
                    //Glide.with(CropsDetailsActivity.this).load(cropsModel.getmImg()).into(mainImageView);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void gotoDiseasePage(View view) {
        startActivity(new Intent(NailDetailsActivity.this, DiseasesActivity.class));
        finish();
    }
}