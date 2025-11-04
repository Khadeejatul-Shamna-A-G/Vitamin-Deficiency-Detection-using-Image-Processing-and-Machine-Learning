package com.example.vitaApp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.vitaApp.models.EyeModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class EyeDetailsActivity extends AppCompatActivity {

    private ImageView mainImageView;
    private TextView mainTitle, mainTitle1, symHint, mainSymptoms, digHint, mainCause, treatHint, mainTreatment, mainDescription;
    private String refID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eye_details);

        refID = getIntent().getStringExtra("cId");

        mainImageView = (ImageView) findViewById(R.id.mainImage);
        mainTitle = (TextView) findViewById(R.id.title);
        mainTitle1 = (TextView) findViewById(R.id.title1);
        symHint = (TextView) findViewById(R.id.symptomsHint);
        mainSymptoms = (TextView) findViewById(R.id.symptoms);
        digHint = (TextView) findViewById(R.id.diagnosisHint);
        mainCause = (TextView) findViewById(R.id.cause);
        treatHint = (TextView) findViewById(R.id.treatmentHint);
        mainTreatment = (TextView) findViewById(R.id.treatments);
        mainDescription = (TextView) findViewById(R.id.description);

        getDetails(refID);
        
    }

    private void getDetails(String refID) {

        DatabaseReference cropRef = FirebaseDatabase.getInstance().getReference().child("EyeDiseases");

        cropRef.child(refID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    EyeModel cropsModel = dataSnapshot.getValue(EyeModel.class);

                    mainTitle.setText(cropsModel.getTitle());
                    mainTitle1.setText(cropsModel.getTitle());
                    symHint.setText(cropsModel.getSymptomHint());
                    mainSymptoms.setText(cropsModel.getSymptoms());
                    digHint.setText(cropsModel.getDiagnosisHint());
                    mainCause.setText(cropsModel.getCause());
                    treatHint.setText(cropsModel.getTreatmentHint());
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
        startActivity(new Intent(EyeDetailsActivity.this, DiseasesActivity.class));
        finish();
    }
}