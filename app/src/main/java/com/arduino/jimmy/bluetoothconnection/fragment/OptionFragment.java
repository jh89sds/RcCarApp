package com.arduino.jimmy.bluetoothconnection.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.arduino.jimmy.bluetoothconnection.R;

/**
 * Created by Jimmy on 2017-09-03.
 */

public class OptionFragment extends TabFragment {

    private View inflatedView;
    private ImageButton brightnessOnOff;
    private boolean autoOn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.option_layout, container, false);

        autoOn = false;

        setButtons();

        return inflatedView;
    }

    private void setButtons() {
        brightnessOnOff = ((ImageButton) inflatedView.findViewById(R.id.brightness_auto_on_off));

        brightnessOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(autoOn) {
                    brightnessOnOff.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_brightness_auto));
                    autoOn = false;
                    sendData("6");
                }else {
                    brightnessOnOff.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.ic_brightness_auto_off));
                    autoOn = true;
                    sendData("5");
                }
            }
        });
    }


}
