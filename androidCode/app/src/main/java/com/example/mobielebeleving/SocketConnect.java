package com.example.mobielebeleving;

import android.os.Handler;
import android.os.Looper;

import com.example.mobielebeleving.RecyclerView.Story.StoryManager;
import com.example.mobielebeleving.activityclasses.ChooseStoryActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketConnect {
    private ChooseStoryActivity chooseStoryActivity;

    public SocketConnect (ChooseStoryActivity chooseStoryActivity) {
        this.chooseStoryActivity = chooseStoryActivity;
    }

    public void createSocket() {

        final Handler handler = new Handler();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("145.49.17.66", 12345);

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                    String line = reader.readLine();
                    System.out.println(line);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (line.equals("story completed 1")) {
                                StoryManager.test = "1";
                            } else if (line.equals("story completed 2")) {
                                StoryManager.test = "2";
                            } else if (line.equals("story completed 3")) {
                                StoryManager.test = "3";
                            }

                            StoryManager.createStory();
                            chooseStoryActivity.refreshStoryList();
                        }
                    });
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}