package com.example.shkumari.bindservicesusingmessenger.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MessengerService extends Service {
    public static final int job_1 = 1;
    public static final int job_2 = 2;
Messenger mMessenger = new Messenger(new IncomingHandler());
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Toast.makeText(getApplicationContext(),"Service binding....",Toast.LENGTH_LONG).show();
        return mMessenger.getBinder();
    }
    class IncomingHandler extends Handler{

        //each message from client get handled by handleMessage()
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case job_1:
                   Toast.makeText(getApplicationContext(),"hello from job_1",Toast.LENGTH_LONG).show();
                   break;
               case job_2:
                   Toast.makeText(getApplicationContext(),"hello from job_2",Toast.LENGTH_LONG).show();
                   break;
               default:
                   super.handleMessage(msg);
           }

            super.handleMessage(msg);
        }
    }
}