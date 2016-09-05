package com.example.servicelifecycle;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class CLient extends Activity{
    private IService iService2=null;
    private Button unbind2;
    private Button mStart2;
    private EditText medit2;
    private Button mBind2; 
    private Button mStop2;
    ServiceConnection connection2=new ServiceConnection() {
         public void onServiceDisconnected(ComponentName name) {
            Log.d("LOG","Client ->Disconnected the LocalService");
        }
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
             //获取连接的服务对象
              //System.out.println("Connection!!!");
              iService2=(IService)service;
               try {
				medit2.setText(iService2.getName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
               Log.d("LOG","Client ->Connected the Service");
        } 
     };  
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a);
        Log.d("LOG","Client ->OnCreate");
        unbind2 = (Button)findViewById(R.id.unbind2);
        mStart2 = (Button)findViewById(R.id.start2);
        mStop2 = (Button )findViewById(R.id.stop2);
        medit2 = (EditText)findViewById(R.id.edit2);
        mBind2 = (Button)findViewById(R.id.connection2);
        mBind2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 Intent intent=new Intent(CLient.this,LocalService.class);
                 bindService(intent,connection2, BIND_AUTO_CREATE);    
            }    
        });    
        mStart2.setOnClickListener(new OnClickListener() { 
            public void onClick(View v) {  
                // TODO Auto-generated method stub
                Intent intent=new Intent(CLient.this,LocalService.class);    
                startService(intent);
            }
        });
        mStop2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(CLient.this,LocalService.class);
                stopService(intent);
            }
        });
        unbind2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.e("LOG", "Unbind");
                unbindService(connection2);
                Log.e("LOG", "Unbind");
            }  
        }); 
    }     
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        //unbindService(connection2);
        Log.d("LOG","Client ->OnDestory");
        super.onDestroy();
        
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        Log.d("LOG","Client ->OnPause");
    } 
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.d("LOG","Client ->OnRestart");
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        Log.d("LOG","Client ->OnResume");
    }
    @Override 
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d("LOG","Client ->OnSatrt");
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.d("LOG","Client ->OnStop");
    }
    
}