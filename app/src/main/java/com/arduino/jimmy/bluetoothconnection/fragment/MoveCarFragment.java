package com.arduino.jimmy.bluetoothconnection.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.arduino.jimmy.bluetoothconnection.R;

/**
 * Created by Jimmy on 2017-09-03.
 */

public class MoveCarFragment extends TabFragment{

    private View inflatedView;

    private View upButton;
    private View downButton;
    private View rightButton;
    private View leftButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.car_move_layout, container, false);

        setButtons();

        return inflatedView;
    }

    private void setButtons() {
        upButton =  inflatedView.findViewById(R.id.up_button);
        upButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveAction(event, "f");
            }
        });

        downButton = inflatedView.findViewById(R.id.down_button);
        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveAction(event, "b");
            }
        });
        leftButton = inflatedView.findViewById(R.id.left_button);
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveAction(event, "l");
            }
        });
        rightButton = inflatedView.findViewById(R.id.right_button);
        rightButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveAction(event, "r");
            }
        });
    }

    private boolean moveAction(MotionEvent event, String forward) {
        if (MotionEvent.ACTION_DOWN == event.getAction()) {
            sendData(forward);
            return true;
        }else if(MotionEvent.ACTION_UP == event.getAction()) {
            stopMove();
        }
        return false;
    }

    public void stopMove() {
        sendData("s");
    }
}
