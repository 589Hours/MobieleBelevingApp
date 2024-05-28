package com.example.mobielebeleving;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CodeChecker extends AppCompatActivity {
    private final static String tag = MainActivity.class.getSimpleName();
    private String code;

    public CodeChecker () {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean checkCode (Context context){
        Log.d(tag, code);
        if ("123".equals(code)) {
            return true;
        } else {
            Log.d(tag, "Invalid code");
            Toast toast = Toast.makeText(context, R.string.toast, Toast.LENGTH_SHORT);
            toast.show();
            return false;
        }
    }
}
