package com.arduino.jimmy.bluetoothconnection.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arduino.jimmy.bluetoothconnection.R;

/**
 * Created by Jimmy on 2017-09-03.
 */

public class LightFragment extends TabFragment {

    private View inflatedView;

    private View frontLightOn;
    private View frontLightOff;
    private View rearLightOn;
    private View rearLightOff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.light_layout, container, false);

        setButtons();

        return inflatedView;
    }

    private void setButtons() {
        frontLightOn = inflatedView.findViewById(R.id.front_light_on);
        frontLightOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData("1");
            }
        });

        frontLightOff = inflatedView.findViewById(R.id.front_light_off);
        frontLightOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData("3");
            }
        });

        rearLightOn = inflatedView.findViewById(R.id.rear_light_on);
        rearLightOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData("2");
            }
        });

        rearLightOff = inflatedView.findViewById(R.id.rear_light_off);
        rearLightOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData("4");
            }
        });
    }
}
