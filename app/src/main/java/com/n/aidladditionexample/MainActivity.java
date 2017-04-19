package com.n.aidladditionexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    IAdditionService myAdilService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        try {
            Intent intentServcie=new Intent(this,AdditionService.class);
            bindService(intentServcie,serviceConnection, Context.BIND_AUTO_CREATE); // start bind service
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


   public  void sumValue(View v){
        EditText e1=(EditText) findViewById(R.id.e1);
        EditText e2=(EditText) findViewById(R.id.e2);

//        int sum= Integer.parseInt(e1.getText().toString())+Integer.parseInt(e2.getText().toString());
        int sum= 0;
        try {
            sum = myAdilService.add(Integer.parseInt(e1.getText().toString()),Integer.parseInt(e2.getText().toString()));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.err.println("Ressss:"+sum);
        TextView res =(TextView) findViewById(R.id.res);
        res.setText(String.valueOf(sum));

    }


    /*
        Aidl Connection Establishment
     */
    ServiceConnection serviceConnection =new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myAdilService=IAdditionService.Stub.asInterface(service); // Setting the Bind service result
         }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };





}
