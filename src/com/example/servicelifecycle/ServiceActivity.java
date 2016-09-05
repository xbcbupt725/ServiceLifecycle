package com.example.servicelifecycle;

import org.apache.http.conn.ClientConnectionManager;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences.Editor;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder; 
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceActivity extends Activity {
//    private final String TAG = "ServiceActivity";
    private Button unbind;
    public static IService iService=null;
    private Button mSkiptoClient;
    private Button mStart;
    public static EditText medit;
    private Button mBind; 
    private Button mStop;
    public static ServiceConnection connection=new ServiceConnection() {
        public void onServiceDisconnected(ComponentName name) {
            Log.d("LOG","Activity ->Disconnected the LocalService");
        }
        public void onServiceConnected(ComponentName name, IBinder service) {
            // TODO Auto-generated method stub
            //获取连接的服务对象
              //System.out.println("Connection!!!");
              iService=IService.Stub.asInterface(service);
              try {
				medit.setText(iService.getName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              Log.d("LOG","Activity ->Connected the Service");
        } 
    };  
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b);
        Log.d("LOG","Activity ->OnCreate");
        unbind = (Button)findViewById(R.id.unbind); 
        mSkiptoClient = (Button)findViewById(R.id.skip);
        mStart = (Button)findViewById(R.id.start);
        mStop = (Button )findViewById(R.id.stop);
        medit = (EditText)findViewById(R.id.edit);
        mBind = (Button)findViewById(R.id.connection);
        mBind.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                 Intent intent=new Intent(ServiceActivity.this,LocalService.class);
                 bindService(intent,connection, BIND_AUTO_CREATE);    
            }
        });
        mStart.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(ServiceActivity.this,LocalService.class);    
                startService(intent);
            }
        });
        mStop.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent=new Intent(ServiceActivity.this,LocalService.class);
                stopService(intent);
            }
        });
        mSkiptoClient.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent("android.intent.action.YClient");
                startActivity(intent);
            }
        });
        unbind.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                unbindService(connection);
            }
        });
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        Log.d("LOG","Activity ->OnDestory");
    }
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
//        unbindService(connection);
        Log.d("LOG","Activity ->OnPause");
        super.onPause();
        
    }
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
        Log.d("LOG","Activity ->OnRestart");
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        Log.d("LOG","Activity ->OnResume");
//         Intent intent=new Intent(ServiceActivity.this,LocalService.class);
//         bindService(intent,connection, BIND_AUTO_CREATE);    
        super.onResume();
        
    }
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Log.d("LOG","Activity ->OnSatrt");
    }
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        Log.d("LOG","Activity ->OnStop");
    }
    
}