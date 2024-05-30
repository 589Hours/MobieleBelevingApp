package com.example.mobielebeleving.RecyclerView.Location;

import android.content.Context;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mobielebeleving.R;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ProjectViewHolder> {
    private static final String LOGTAG = LocationAdapter.class.getName();

    private LayoutInflater inflater;
    private OnItemClickListener clickListener;
    private List<Location> locationList;

    public class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView lijstTextView;
        public final ImageView lijstFotoView;

        public ProjectViewHolder(View itemView) {
            super(itemView);
            lijstTextView = itemView.findViewById(R.id.lijstText);
            lijstFotoView = itemView.findViewById(R.id.lijstFoto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Log.i(LOGTAG, "Item " + clickedPosition + " clicked");
            clickListener.onItemClick(clickedPosition, locationList.get(clickedPosition).getImageResourceId());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition, int imageResourceId);
    }

    public LocationAdapter(Context context, List<Location> location, OnItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        locationList = location;
        clickListener = listener;
    }

    @Override
    public ProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(LOGTAG, "onCreateViewHolder() called");
        View itemView = inflater.inflate(R.layout.item_location, parent, false);
        ProjectViewHolder viewHolder = new ProjectViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProjectViewHolder holder, int position) {
        Log.d(LOGTAG, "onBindViewHolder() called for position " + position);
        Location location = locationList.get(position);
        holder.lijstTextView.setText(location.getLocation());
        holder.lijstFotoView.setImageResource(location.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        Log.d(LOGTAG, "getItemCount() called");
        return locationList.size();
    }
}
