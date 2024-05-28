package com.example.mobielebeleving;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

import androidx.appcompat.app.AppCompatActivity;

public class FlashLightController {
    private CameraManager cameraManager;
    private String cameraID;
    public FlashLightController(CameraManager cameraManager){
        try {
            this.cameraManager = cameraManager;
            cameraID = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
    public void flash(){
        try{
            cameraManager.setTorchMode(cameraID,true);
            Thread.sleep(1000);
            cameraManager.setTorchMode(cameraID, false);
        } catch (CameraAccessException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
