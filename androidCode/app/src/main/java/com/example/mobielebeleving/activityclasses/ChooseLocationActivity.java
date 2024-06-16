package com.example.mobielebeleving.activityclasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobielebeleving.RecyclerView.Location.LocationAdapter;
import com.example.mobielebeleving.RecyclerView.Location.LocationManager;
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

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.helpButton) {
            AlertDialog dialog = createDialog();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Uitleg");
        builder.setMessage(R.string.helpDialog);
        builder.setPositiveButton(R.string.oké, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
