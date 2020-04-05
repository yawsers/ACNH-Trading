package com.example.animalcrossingtrading;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class listingTest extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView mFirestoreList;

    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing_test);

        mFirestoreList = findViewById(R.id.firestore_list);
        firebaseFirestore = FirebaseFirestore.getInstance();

        //Query
        Query query = firebaseFirestore.collection("furniture");
        //recycler
        FirestoreRecyclerOptions<ListingModel> options = new FirestoreRecyclerOptions.Builder<ListingModel>()
                .setQuery(query, ListingModel.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<ListingModel, ListingViewModel>(options) {
            @NonNull
            @Override
            public ListingViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new ListingViewModel(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ListingViewModel holder, int position, @NonNull ListingModel model) {
                holder.list_listing.setText(model.getListing());
                holder.list_count.setText(model.getCount() + "");
            }
        };

        mFirestoreList.setHasFixedSize(true);
        mFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        mFirestoreList.setAdapter(adapter);

    }

    private class ListingViewModel extends RecyclerView.ViewHolder {
        private TextView list_count;
        private TextView list_listing;


        public ListingViewModel(@NonNull View itemView) {
            super(itemView);

            list_listing = itemView.findViewById(R.id.list_listing);
            list_count = itemView.findViewById(R.id.list_count);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
}
