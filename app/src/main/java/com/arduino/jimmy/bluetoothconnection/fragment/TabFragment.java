package com.arduino.jimmy.bluetoothconnection.fragment;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.arduino.jimmy.bluetoothconnection.SocketSingleton;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TabFragment extends Fragment {

    protected
    BluetoothSocket mSocket;

    protected OutputStream mOutputStream;
    protected InputStream mInputStream;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setBluetoothConnection();
    }

    protected void sendData(String msg) {
        try{
            // getBytes() : String을 byte로 변환
            // OutputStream.write : 데이터를 쓸때는 write(byte[]) 메소드를 사용함. byte[] 안에 있는 데이터를 한번에 기록해 준다.
            mOutputStream.write(msg.getBytes());  // 문자열 전송.
        }catch(Exception e) {  // 문자열 전송 도중 오류가 발생한 경우
            Toast.makeText(getActivity(), "데이터 전송중 오류가 발생", Toast.LENGTH_LONG).show();
            getActivity().finish();  // App 종료
        }
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
            Toast.makeText(getActivity(), "블루투스가 연결되지 않았습니다.", Toast.LENGTH_LONG).show();
        }
    }
}
