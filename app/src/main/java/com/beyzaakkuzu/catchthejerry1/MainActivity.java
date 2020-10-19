package com.beyzaakkuzu.catchthejerry1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView timeView, scoreView;
    ImageView imageView;
    int score;
    int screenX, screenY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenX = size.x;
        screenY = size.y;
        score= 0;

        imageView= findViewById(R.id.imageView);
        timeView= findViewById(R.id.timeView);
        scoreView= findViewById(R.id.scoreView);
        startGame();
    }
    public void startGame(){
        scoreView.setText("Score: "+ (score=0));
        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long l) {
                timeView.setText(" Time remaining : "+ l/1000);
                float rndmX = new Random().nextInt(screenX - imageView.getMeasuredWidth());
                float rndmY = new Random().nextInt(screenY - 2 * imageView.getMeasuredHeight());
                imageView.setX(rndmX);
                imageView.setY(rndmY);
            }
            @Override
            public void onFinish() {
                timeView.setText("Time Finished!");
                imageView.setVisibility(View.INVISIBLE); //resim oyun bittiğinde görünmez.
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart");
                alert.setMessage("Do you want try again?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startGame();
                        imageView.setVisibility(View.VISIBLE);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Game Over.", Toast.LENGTH_LONG);
                    }
                });
                alert.show();
            }
        }.start();
    }
    public void onclick(View view){
        score++;
        scoreView.setText("Score: "+ score);
    }
}