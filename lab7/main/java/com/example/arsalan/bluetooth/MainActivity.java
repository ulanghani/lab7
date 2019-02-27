package com.example.arsalan.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private Set<BluetoothDevice> pairedDevices;
    private BluetoothAdapter BA;
    Button b1,b2,b3,b4;
    ListView lv;
    String list[];
    private ArrayAdapter aAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1= findViewById(R.id.b1);
        b2= findViewById(R.id.b2);
        b3= findViewById(R.id.b3);
        b4= findViewById(R.id.b4);
        lv= findViewById(R.id.listview);
        BA= BluetoothAdapter.getDefaultAdapter();
        pairedDevices = BA.getBondedDevices();
    }
    public void btclick(View v){
        Intent in=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(in, 0);
    }
    public void spclick(View v){
        Intent in=new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        startActivityForResult(in, 0);
    }
    public void mtclick(View v){
        if(BA==null){
            Toast.makeText(getApplicationContext(),"Bluetooth Not Supported",Toast.LENGTH_SHORT).show();
        }
        else{
            Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
            ArrayList list = new ArrayList();
            if(pairedDevices.size()>0){
                for(BluetoothDevice device: pairedDevices){
                    String devicename = device.getName();
                    String macAddress = device.getAddress();
                    list.add("Name: "+devicename+"MAC Address: "+macAddress);
                }
                aAdapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, list);
                lv.setAdapter(aAdapter);
            }

    }
    }
    public void stclick(View v){
        BA.disable();
    }

}
