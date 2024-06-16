package com.example.mobielebeleving;

import android.content.Context;
import android.widget.TextView;

import com.example.mobielebeleving.activityclasses.PlayActivity;

public class Timer implements Runnable{
    private int length;
    private Context context;
    private TextView countdownView;
    private final String countdownText;
    private final String seconds;
    private final String readyText;
    private PlayActivity playActivity;
  
    public Timer(int length, Context context, TextView view, PlayActivity playActivity){
        //Length cannot and shall not be below 0 to avoid errors
        this.length = length;
        this.context = context;
        this.playActivity = playActivity;
        this.countdownView = view;
        this.countdownText = context.getString(R.string.aftellen);
        this.seconds = context.getString(R.string.seconden);
        this.readyText = context.getString(R.string.klaar);
    }
    @Override
    public void run() {
        //just in case it happens our app won't crash
        if (length < 0){
            return;
        }
        for (int i = length; i > 0; i--) {
            try {
                String countdownToShow = countdownText + " " +  i + " " +  seconds;
                playActivity.runOnUiThread(() -> countdownView.setText(countdownToShow));
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
            playActivity.runOnUiThread(()-> countdownView.setText(readyText));
        }
        PlayActivity.setReady();
    }
}
