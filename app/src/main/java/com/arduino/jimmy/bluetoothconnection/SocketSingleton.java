package com.arduino.jimmy.bluetoothconnection;

import android.bluetooth.BluetoothSocket;
import android.widget.Toast;

import java.io.IOException;

public class SocketSingleton {
    private static BluetoothSocket socket;

    public static void setSocket(BluetoothSocket socketpass) {
        SocketSingleton.socket = socketpass;
    }

    public static BluetoothSocket getSocket() {
        return SocketSingleton.socket;
    }

    public static void disconnectBluetooth() {
        if(socket != null){
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
