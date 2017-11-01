package cn.bgs.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Vincent on 2017/10/31.
 */

public class MyService extends Service {
    private static final String TAG = "MyService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: 服务创建" );
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: 服务开启");
        return super.onStartCommand(intent, flags, startId);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: 服务绑定" );
        MyStub stub=new MyStub();
        return stub;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.e(TAG, "onUnbind: 服务解绑" );
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: 服务销毁" );
    }

    class MyStub extends IMyAidlInterface.Stub{

     @Override
     public String showData(String msg) throws RemoteException {
         msg+="luncher:";
         return msg;
     }
 }
}
