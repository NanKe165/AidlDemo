package cn.bgs.aidltest;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import cn.bgs.aidldemo.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @butterknife.BindView(R.id.btn1)
    Button btn1;
    @butterknife.BindView(R.id.btn2)
    Button btn2;
    @butterknife.BindView(R.id.btn3)
    Button btn3;
    @butterknife.BindView(R.id.btn4)
    Button btn4;
    private ServiceConnection serviceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        butterknife.ButterKnife.bind(this);
        serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.e(TAG, "onServiceConnected: 服务绑定" );
                IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder);
                try {
                    String s = iMyAidlInterface.showData("就像阳光穿破黑夜");
                    Log.e(TAG, "onServiceConnected: "+s );
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
            }
        };
    }

    @butterknife.OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Intent intent=new Intent();
                intent.setAction("cn.bgs.aidl");
                intent.setPackage("cn.bgs.aidldemo");
                startService(intent);
                break;
            case R.id.btn2:
                Intent intent1=new Intent();
                intent1.setAction("cn.bgs.aidl");
                intent1.setPackage("cn.bgs.aidldemo");
                bindService(intent1,serviceConnection, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn3:
                unbindService(serviceConnection);
                break;
            case R.id.btn4:
                Intent intent3=new Intent();
                intent3.setAction("cn.bgs.aidl");
                intent3.setPackage("cn.bgs.aidldemo");
                stopService(intent3);
                break;
        }
    }
}
