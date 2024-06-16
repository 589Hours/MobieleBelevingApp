package com.example.mobielebeleving.activityclasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobielebeleving.LanguageManager;
import com.example.mobielebeleving.R;
import com.example.mobielebeleving.RecyclerView.Story.StoryManager;
import com.example.mobielebeleving.SocketConnect;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = EnterCodeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageButton dutch = findViewById(R.id.dutchButton);
        ImageButton english = findViewById(R.id.englishButton);

        LanguageManager lm = new LanguageManager(this);

        dutch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.updateResources("nl");
                Toast toast = Toast.makeText(MainActivity.this,"Geselecteerde taal: Nederlands!", Toast.LENGTH_LONG);
                toast.show();
                welcomeTextView();
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.updateResources("en");
                Toast toast = Toast.makeText(MainActivity.this,"Selected language: English!",Toast.LENGTH_LONG);
                toast.show();
                welcomeTextView();
            }
        });

        //idk storyManager setup? (Fedde can explain more)
        StoryManager.run();
        StoryManager.setApplicationContext(this);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.helpButton) {
//            AlertDialog dialog = createDialog();
//            dialog.show();
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

//    AlertDialog createDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(R.string.helpTitel);
//        builder.setMessage(R.string.helpDialog);
//        builder.setPositiveButton(R.string.roger, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//            }
//        });
//        return builder.create();
//    }
    public void welcomeTextView(){
        Intent intent = new Intent(this, WelcomeSpeechActivity.class);
        startActivity(intent);
    }
}
