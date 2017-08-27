package com.arduino.jimmy.bluetoothconnection;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by USER on 2017-08-27.
 */
public class ButtonControlActivity extends CommonActivity {

    private BluetoothSocket mSocket;

    private OutputStream mOutputStream;
    private InputStream mInputStream;

    private View upButton;
    private View downButton;
    private View rightButton;
    private View leftButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_control);

        try {
            // 데이터 송수신을 위한 스트림 얻기.
            // BluetoothSocket 오브젝트는 두개의 Stream을 제공한다.
            // 1. 데이터를 보내기 위한 OutputStrem
            // 2. 데이터를 받기 위한 InputStream
            mSocket = SocketSingleton.getSocket();
            mOutputStream = mSocket.getOutputStream();
            mInputStream = mSocket.getInputStream();
        } catch (IOException e) {
            Toast.makeText(this, "블루투스가 연결되지 않았습니다.", Toast.LENGTH_LONG).show();
        }

        setButtons();
    }

    private void setButtons() {
        upButton =  findViewById(R.id.up_button);
        upButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveAction(event, "f");
            }
        });

        downButton = findViewById(R.id.down_button);
        downButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveAction(event, "b");
            }
        });
        leftButton = findViewById(R.id.left_button);
        leftButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return moveAction(event, "l");
            }
        });
        rightButton = findViewById(R.id.right_button);
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
            sendData("s");
        }
        return false;
    }

    private void sendData(String msg) {
        try{
            // getBytes() : String을 byte로 변환
            // OutputStream.write : 데이터를 쓸때는 write(byte[]) 메소드를 사용함. byte[] 안에 있는 데이터를 한번에 기록해 준다.
            mOutputStream.write(msg.getBytes());  // 문자열 전송.
        }catch(Exception e) {  // 문자열 전송 도중 오류가 발생한 경우
            Toast.makeText(getApplicationContext(), "데이터 전송중 오류가 발생", Toast.LENGTH_LONG).show();
            finish();  // App 종료
        }
    }
}
