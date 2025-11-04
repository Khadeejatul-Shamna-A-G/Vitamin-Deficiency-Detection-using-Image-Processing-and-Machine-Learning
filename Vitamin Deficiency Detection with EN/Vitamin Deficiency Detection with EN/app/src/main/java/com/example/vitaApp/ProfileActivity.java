package com.example.vitaApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vitaApp.networkcheck.CheckInternetConnection;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    RelativeLayout contentView;
    private TextView namebutton;
    private CircleImageView primage;
    private Button updateDetails;

    //to get user session data
    private UserSession session;
    private TextView tvemail,tvphone;
    private HashMap<String,String> user;
    private String name,email,photo,mobile;

    private RelativeLayout logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        contentView = findViewById(R.id.content_view);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.home:
                                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this,
                                        MainActivity.class));
                                ProfileActivity.this.finish();
                                ProfileActivity.this.overridePendingTransition(0, 0);
                                return true;
                            case R.id.diseases:
                                ProfileActivity.this.startActivity(new Intent(ProfileActivity.this,
                                        DiseasesActivity.class));
                                ProfileActivity.this.finish();
                                ProfileActivity.this.overridePendingTransition(0, 0);
                                return true;
                            case R.id.profile:
                                return true;
                        }
                        return false;
                    }
                });

        initialize();

        //check Internet Connection
        new CheckInternetConnection(this).checkConnection();

        //retrieve session values and display on listviews
        getValues();

        logout = findViewById(R.id.logoutRL);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.logoutUser();
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initialize() {

        primage=findViewById(R.id.profilepic);
        tvemail=findViewById(R.id.emailview);
        tvphone=findViewById(R.id.mobileview);
        namebutton=findViewById(R.id.name_button);
        updateDetails=findViewById(R.id.updatedetails);

        updateDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,UpdateDataActivity.class));
                finish();
            }
        });

    }

    private void getValues() {

        //create new session object by passing application context
        session = new UserSession(getApplicationContext());

        //validating session
        session.isLoggedIn();

        //get User details if logged in
        user = session.getUserDetails();

        name=user.get(UserSession.KEY_NAME);
        email=user.get(UserSession.KEY_EMAIL);
        mobile=user.get(UserSession.KEY_MOBiLE);
        photo=user.get(UserSession.KEY_PHOTO);

        //setting values
        tvemail.setText(email);
        tvphone.setText(mobile);
        namebutton.setText(name);

        Picasso.get().load(photo).into(primage);

    }
}