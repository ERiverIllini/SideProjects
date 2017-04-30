package com.ninja.emilior.password;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText editNumLen;
    EditText editPassLen;
    EditText editSymLen;
    TextView passwordView;
    NotificationCompat.Builder mBuilder;
    Button getPass;
    Button copyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        passwordView = (TextView) findViewById(R.id.passText);
        copyBtn = (Button) findViewById(R.id.clipCopy);
        getPass = (Button) findViewById(R.id.introBtn);
        editNumLen = (EditText) findViewById(R.id.numLen);
        editPassLen = (EditText) findViewById(R.id.passLength);
        editSymLen = (EditText) findViewById(R.id.symLen);

        // Generate password + make copy button/password text visible.
        getPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mBuilder = new NotificationCompat.Builder(getApplicationContext());
                    mBuilder.setSmallIcon(R.drawable.ic_launcher);
                    mBuilder.setContentTitle("TESTING APP");
                    mBuilder.setContentText("Hey we have successfully done it.");

                    Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                    TaskStackBuilder stackBuilder = TaskStackBuilder.create(getApplicationContext());
                    stackBuilder.addParentStack(MainActivity.class);
                    stackBuilder.addNextIntent(resultIntent);
                    PendingIntent resultPendingIntent =
                            stackBuilder.getPendingIntent(
                                    0,
                                    PendingIntent.FLAG_UPDATE_CURRENT
                            );
                    mBuilder.setContentIntent(resultPendingIntent);
                    NotificationManager mNotificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                    mNotificationManager.notify(1, mBuilder.build());
                    int passLen = Integer.parseInt(editPassLen.getText().toString());
                    int numLen = Integer.parseInt(editNumLen.getText().toString());
                    int symLen = Integer.parseInt(editSymLen.getText().toString());

                    if (passLen >= (numLen+symLen)) {
                        RandomPass rand = new RandomPass(passLen,symLen,numLen);
                        String output = rand.generatePass();
                        passwordView.setText(output);
                        passwordView.setVisibility(View.VISIBLE);
                        copyBtn.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "Password Generated!", Toast.LENGTH_SHORT).show();

                        mBuilder.setContentTitle("Notification Alert, Click Me!");
                        mBuilder.setContentText("Hi, This is Android Notification Detail!");
                    } else {
                        Toast.makeText(getApplicationContext(), "Error with your password parameters.", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Missin' some numbers there bud.", Toast.LENGTH_SHORT).show();
                }


            }
        });

        // Copy to clipboard button.
        copyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("pass", passwordView.getText());
                clipboardManager.setPrimaryClip(clip);
            }
        });

    }




}
