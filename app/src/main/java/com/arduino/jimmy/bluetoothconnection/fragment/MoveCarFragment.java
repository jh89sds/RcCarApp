package com.arduino.jimmy.bluetoothconnection.fragment;

import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arduino.jimmy.bluetoothconnection.R;
import com.arduino.jimmy.bluetoothconnection.SocketSingleton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Jimmy on 2017-09-03.
 */

public class MoveCarFragment extends TabFragment{

    private View inflatedView;

    private BluetoothSocket mSocket;

    private OutputStream mOutputStream;
    private InputStream mInputStream;

    private View upButton;
    private View downButton;
    private View rightButton;
    private View leftButton;

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflatedView = inflater.inflate(R.layout.car_move_layout, container, false);

        setBluetoothConnection();

        setButtons();

        this.context = getActivity();

        return inflatedView;
    }

    private void setBluetoothConnection() {
        try {
            // 데이터 송수신을 위한 스트림 얻기.
            // BluetoothSocket 오브젝트는 두개의 Stream을 제공한다.
            // 1. 데이터를 보내기 위한 OutputStrem
            // 2. 데이터를 받기 위한 InputStream
            mSocket = SocketSingleton.getSocket();
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();
        } catch (IOException e) {
            Toast.makeText(context, "블루투스가 연결되지 않았습니다.", Toast.LENGTH_LONG).show();
        }
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

    private void sendData(String msg) {
        try{
            // getBytes() : String을 byte로 변환
            // OutputStream.write : 데이터를 쓸때는 write(byte[]) 메소드를 사용함. byte[] 안에 있는 데이터를 한번에 기록해 준다.
            mOutputStream.write(msg.getBytes());  // 문자열 전송.
        }catch(Exception e) {  // 문자열 전송 도중 오류가 발생한 경우
            Toast.makeText(context, "데이터 전송중 오류가 발생", Toast.LENGTH_LONG).show();
            getActivity().finish();  // App 종료
        }
    }

    public void stopMove() {
        sendData("s");
    }
}
