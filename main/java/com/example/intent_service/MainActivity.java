package com.example.intent_service;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.service.carrier.CarrierMessagingService;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnSend(View view){
        EditText txt_number = findViewById(R.id.edtxtNumber);
        EditText txt_message = findViewById(R.id.edtxtMessage);
        Button btn_send = findViewById(R.id.btnSend);

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(checkSelfPermission(Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED){
                        SendMessage();;
                    }else{
                        requestPermissions(new String[]{Manifest.permission.SEND_SMS},1);
                    }
                }
            }
        });

    }
    public  void  SendMessage(){
        EditText txt_number = findViewById(R.id.edtxtNumber);
        EditText txt_message = findViewById(R.id.edtxtMessage);
        String number = txt_number.getText().toString().trim();
        String message = txt_message.getText().toString().trim();

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number,null,message,null,null);
            Toast.makeText(this, "Successfully Delivered.", Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Not Sent. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}