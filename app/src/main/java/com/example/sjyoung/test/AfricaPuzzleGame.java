package com.example.sjyoung.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by sjyoung on 2017. 5. 13..
 */

public class AfricaPuzzleGame extends AppCompatActivity implements View.OnTouchListener {

    ImageView congo;
    ImageView angola;
    ImageView congoTarget;
    ImageView angolaTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.america_game_map);

        congo = (ImageView) findViewById(R.id.congo);
        angola = (ImageView) findViewById(R.id.angola);

        congo.setOnTouchListener(this);
        angola.setOnTouchListener(this);

        congoTarget = (ImageView) findViewById(R.id.congo_target);
        angolaTarget = (ImageView) findViewById(R.id.angola_target);
    }

    float oldXvalue;
    float oldYvalue;

    public boolean onTouch(View v, MotionEvent event) {

        int width = ((ViewGroup) v.getParent()).getWidth() - v.getWidth();
        int height = ((ViewGroup) v.getParent()).getHeight() - v.getHeight();

        // switch 문으로 변경하기
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            oldXvalue = event.getX();
            oldYvalue = event.getY();
            Log.i("Tag1", "Action Down rX " + event.getRawX() + "," + event.getRawY());

        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            v.setX(event.getRawX() - oldXvalue);
            v.setY(event.getRawY() - (oldYvalue + v.getHeight()));
            Log.i("Tag1", "Action Down rX " + event.getRawX() + "," + event.getRawY());

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            // 제대로 영역 안에 들어왔는지 체크해야 함
            // 영역 안에 들어오면 이미지 변환
            // .setImageDrawable(getResources().getDrawable(R.drawable.monkey, getApplicationContext().getTheme()))
            
            if (v.getX() > width && v.getY() > height) {
                v.setX(width);
                v.setY(height);
            } else if (v.getX() < 0 && v.getY() > height) {
                v.setX(0);
                v.setY(height);
            } else if (v.getX() > width && v.getY() < 0) {
                v.setX(width);
                v.setY(0);
            } else if (v.getX() < 0 && v.getY() < 0) {
                v.setX(0);
                v.setY(0);
            } else if (v.getX() < 0 || v.getX() > width) {
                if (v.getX() < 0) {
                    v.setX(0);
                    v.setY(event.getRawY() - oldYvalue - v.getHeight());
                } else {
                    v.setX(width);
                    v.setY(event.getRawY() - oldYvalue - v.getHeight());
                }
            } else if (v.getY() < 0 || v.getY() > height) {
                if (v.getY() < 0) {
                    v.setX(event.getRawX() - oldXvalue);
                    v.setY(0);
                } else {
                    v.setX(event.getRawX() - oldXvalue);
                    v.setY(height);
                }
            }
        }
        return true;
    }

}


