package com.example.mobielebeleving;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

public class ChooseLocation extends AppCompatActivity
        implements AdapterView.OnItemClickListener, LocationAdapter.OnItemClickListener{
    private RecyclerView locationRecyclerView;
    private LocationAdapter locationRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.choose_location);

        LocationManager.setApplicationContext(getApplicationContext());

        locationRecyclerView = findViewById(R.id.LocationRecyclerView);
    locationRecyclerViewAdapter = new LocationAdapter(this,
                                                    LocationManager.getLocations(),
                this);
        locationRecyclerView.setAdapter(locationRecyclerViewAdapter);
        locationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        navigateToDetailActivity(position, LocationManager.getLocations().get(position).getImageResourceId());
    }

    @Override
    public void onItemClick(int clickedPosition, int imageResourceId) {
        navigateToDetailActivity(clickedPosition, imageResourceId);
    }

    private void navigateToDetailActivity(int position, int imageResourceId) {
        Intent intent = new Intent(this, EnterCode.class);
        intent.putExtra(EnterCode.EXTRA_INFO_ID, position);
        startActivity(intent);
    }
}
