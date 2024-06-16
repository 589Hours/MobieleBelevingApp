package com.example.mobielebeleving;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mobielebeleving.activityclasses.EnterCodeActivity;

public class CodeChecker extends AppCompatActivity {
    private TextView invaledCode;
    private final static String tag = EnterCodeActivity.class.getSimpleName();
    private String code;

    public CodeChecker () {
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Boolean checkCode (Context context){
        Log.d(tag, code);
        invaledCode = findViewById(R.id.wrongcode);
        if ("123".equals(code)) {
            invaledCode.setVisibility(View.GONE);
            return true;
        } else {
            Log.d(tag, "Invalid code");
            invaledCode.setVisibility(View.VISIBLE);
            return false;
        }
    }
}
