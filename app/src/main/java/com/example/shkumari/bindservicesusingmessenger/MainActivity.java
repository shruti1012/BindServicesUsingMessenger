package com.example.shkumari.bindservicesusingmessenger;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.shkumari.bindservicesusingmessenger.Services.MessengerService;

public class MainActivity extends AppCompatActivity {
    Messenger mMessenger = null;
    boolean isBind = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void bindService(View view){
        Intent intent =new Intent(this, MessengerService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);


    }

    public void sayHello(View view){
        if (isBind) {
            // which button is clicked by user
            String button_text;
            button_text = (String) ((Button) view).getText();
            if (button_text.equals("say Hello")) {
                Message message = Message.obtain(null,MessengerService.job_1,0,0,0);
                try {
                    mMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            } else if (button_text.equals("say Hello again")) {
                Message message = Message.obtain(null,MessengerService.job_2,0,0,0);
                try {
                    mMessenger.send(message);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }else{
            Toast.makeText(getApplicationContext(),"bind service first",Toast.LENGTH_LONG).show();
        }
    }
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMessenger = new Messenger(service);
            isBind = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMessenger = null;
            isBind = false;
        }
    };

    @Override
    protected void onStop() {
        unbindService(mConnection);
        isBind = false;
        mMessenger = null;
        super.onStop();
    }
}
