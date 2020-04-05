package com.example.animalcrossingtrading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

public class MainActivity extends AppCompatActivity {
    TextView musername, memail;
    FirebaseAuth fAuth;
    FirebaseFirestore fstore;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        musername = findViewById(R.id.username);
        memail = findViewById(R.id.email);

        fAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = fAuth.getCurrentUser().getUid();

        final DocumentReference documentReference = fstore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                musername.setText(documentSnapshot.getString("userName"));
                memail.setText(documentSnapshot.getString("email"));
            }
        });

    }

    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext (), Login.class));
        finish();
    }

    public void goToListings(View view){
        startActivity(new Intent(getApplicationContext(), listingTest.class));
    }
}
