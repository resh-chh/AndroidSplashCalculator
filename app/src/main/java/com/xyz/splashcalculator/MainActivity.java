package com.xyz.splashcalculator;

import android.content.Intent;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.annotation.StringDef;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.xyz.splashcalculator.R;

import java.util.Locale;

import static com.xyz.splashcalculator.R.menu.menu_main;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNumber;
    Button btnSquare, btnSquareRoot, btnCube;
    TextView tvAnswer;
    TextToSpeech textToSpeech;
    FloatingActionButton fabSpeak;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumber = (EditText) findViewById(R.id.etNumber);
        btnSquare = (Button) findViewById(R.id.btnSquare);
        btnSquareRoot = (Button) findViewById(R.id.btnSquareRoot);
        btnCube = (Button) findViewById(R.id.btnCube);
        tvAnswer = (TextView) findViewById(R.id.tvAnswer);
        fabSpeak = (FloatingActionButton) findViewById(R.id.fabSpeak);

        btnSquare.setOnClickListener(this);
        btnSquareRoot.setOnClickListener(this);
        btnCube.setOnClickListener(this);


//        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status != TextToSpeech.ERROR) {
//                    textToSpeech.setLanguage(Locale.UK);
//                }
//            }
//        });

        fabSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String toSpeak = tvAnswer.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak, Toast.LENGTH_SHORT).show();
                textToSpeech.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

    }


    @Override
    public void onClick(View v) {

        if (etNumber.getText().toString().length() == 0) {
            Snackbar.make(v, "Please Enter a Number", Snackbar.LENGTH_LONG).show();
            etNumber.requestFocus();
            return;
        }

        double number;
        switch (v.getId()) {

            case R.id.btnSquare:
                number = Double.parseDouble(etNumber.getText().toString());
                double Square = number * number;
                Toast.makeText(getApplicationContext(), "Square of " + number + "is: " + Square, Toast.LENGTH_LONG).show();
                tvAnswer.setText("Square of " + number + "is: " + Square);
                break;

            case R.id.btnSquareRoot:
                number = Double.parseDouble(etNumber.getText().toString());
                double SquareRoot = Math.sqrt(number);
                Toast.makeText(getApplicationContext(), "Square Root of " + number + "is: " + SquareRoot, Toast.LENGTH_LONG).show();
                tvAnswer.setText("Square Root of " + number + "is: " + SquareRoot);
                break;

            case R.id.btnCube:
                number = Double.parseDouble(etNumber.getText().toString());
                double Cube = number * number * number;
                Toast.makeText(getApplicationContext(), "Cube of " + number + "is: " + Cube, Toast.LENGTH_LONG).show();
                tvAnswer.setText("Cube of " + number + "is: " + Cube);
                break;

        }

    }


    protected void onSaveInstanceState(Bundle b1) {
        b1.putString("ans", tvAnswer.getText().toString());
        super.onSaveInstanceState(b1);
    }

    @Override
    protected void onRestoreInstanceState(Bundle b2) {
        String a = b2.getString("ans");
        tvAnswer.setText(a);
        super.onRestoreInstanceState(b2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(menu_main, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mail:
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("mailto:" ));
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"rashmi.chhabria@gmail.com","sonia.chhabria@gmail.com"});
                startActivity(i);
                break;

            case R.id.contact:
                Intent j = new Intent(Intent.ACTION_DIAL);
                j.setData(Uri.parse("tel:" + "8082785714"));
                startActivity(j);
                break;

            case R.id.about:
                Snackbar.make(findViewById(android.R.id.content), "Application made by Rashmi Chhabria", Snackbar.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

    }
}

