package com.example.mobielebeleving;

public class Timer implements Runnable{
    private int length;
    public Timer(int length){
        //Length cannot and shall not be below 0 to avoid errors
        this.length = length;
    }
    @Override
    public void run() {
        //just in case it happens our app won't crash
        if (length < 0){
            return;
        }
        for (int i = length; i > 0; i--) {
            try {
                Thread.sleep(750);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
        PlayActivity.setReady();
    }
}
