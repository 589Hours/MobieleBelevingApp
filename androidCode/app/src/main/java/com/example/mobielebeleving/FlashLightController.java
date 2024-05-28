package com.example.mobielebeleving;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

import androidx.appcompat.app.AppCompatActivity;

public class FlashLightController {
    private CameraManager cameraManager;
    private String cameraID;
    public FlashLightController(AppCompatActivity appCompatActivity){
        //TODO order the code, its now just experimenting results
        cameraManager = (CameraManager) appCompatActivity.getSystemService(Context.CAMERA_SERVICE);

        try{
            cameraID = cameraManager.getCameraIdList()[0];
            cameraManager.setTorchMode(cameraID,true);
            Thread.sleep(1000);
            cameraManager.setTorchMode(cameraID, false);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
