package com.arduino.jimmy.bluetoothconnection;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by USER on 2017-08-28.
 */
public class CommonActivity extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SocketSingleton.disconnectBluetooth();
    }
}
