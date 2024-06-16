package com.example.mobielebeleving.RecyclerView.Story;

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

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.StoryViewHolder> {
    private static final String LOGTAG = StoryAdapter.class.getName();
    private LayoutInflater inflater;
    private OnItemClickListener clickListener;
    private List<Story> storylist;

    public class StoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView lijstTextView;
        public final ImageView lijstFotoView;

        public StoryViewHolder(View itemView) {
            super(itemView);
            lijstTextView = itemView.findViewById(R.id.lijstText);
            lijstFotoView = itemView.findViewById(R.id.lijstFoto);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            Log.i(LOGTAG, "Item " + clickedPosition + " clicked");
            clickListener.onItemClick(clickedPosition, storylist.get(clickedPosition).getImageResourceId());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int clickedPosition, int imageResourceId);
    }

    public StoryAdapter(Context context, List<Story> story, OnItemClickListener listener) {
        inflater = LayoutInflater.from(context);
        storylist = story;
        clickListener = listener;
    }

    public void storyUpdate(List<Story> story){
        storylist = story;
    }

    @Override
    public StoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(LOGTAG, "onCreateViewHolder() called");
        View itemView = inflater.inflate(R.layout.item_location, parent, false);
        StoryViewHolder viewHolder = new StoryViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StoryViewHolder holder, int position) {
        Log.d(LOGTAG, "onBindViewHolder() called for position " + position);
        Story story = storylist.get(position);
        holder.lijstTextView.setText(story.getTitel());
        holder.lijstFotoView.setImageResource(story.getImageResourceId());
    }

    @Override
    public int getItemCount() {
        Log.d(LOGTAG, "getItemCount() called");
        return storylist.size();
    }
}
