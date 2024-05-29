package com.example.mobielebeleving.activityclasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobielebeleving.RecyclerView.LocationAdapter;
import com.example.mobielebeleving.RecyclerView.LocationManager;
import com.example.mobielebeleving.R;

public class ChooseLocationActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, LocationAdapter.OnItemClickListener {
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
        navigateToEnterCodeActivity(position, LocationManager.getLocations().get(position).getImageResourceId());
    }

    @Override
    public void onItemClick(int clickedPosition, int imageResourceId) {
        navigateToEnterCodeActivity(clickedPosition, imageResourceId);
    }

    private void navigateToEnterCodeActivity(int position, int imageResourceId) {
        Intent intent = new Intent(this, EnterCodeActivity.class);
        intent.putExtra(EnterCodeActivity.EXTRA_INFO_ID, position);
        startActivity(intent);
    }
}
