package com.example.servicelifecycle;
 
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class LocalService extends Service{
    private MyBind myBind=new MyBind();
    
    public IBinder onBind(Intent intent) {
            //System.out.println("Service onBind!!!");
            Log.d("LOG","LocalService ->onBind");
            return myBind;
        } 
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        Log.d("LOG","LocalService ->onCreate");
        super.onCreate();
        
    }
     @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
         Log.d("LOG","LocalService ->onDestory");
        super.onDestroy();
        
    }
    @Override
    public void onStart(Intent intent, int startId) {
        // TODO Auto-generated method stub
        Log.d("LOG","LocalService ->onSatrt");
        super.onStart(intent, startId);
        
    } 
    @Override  
    public boolean onUnbind(Intent intent) {
        // TODO Auto-generated method stub 
        Log.d("LOG","LocalService ->onUnbind");
        return super.onUnbind(intent);
        //return true;
    }
    
    @Override 
    public void onRebind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d("LOG","LocalService ->onRebind"); 
        super.onRebind(intent);
        
    }
    
    

    public class MyBind extends IService.Stub{

		@Override
		public String getName() throws RemoteException {
			// TODO Auto-generated method stub
			return "xbc";
		} 

       
		
     } 
}