package com.n.aidladditionexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

public class AdditionService extends Service {
    public AdditionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {

        return myStub; // Return Stub Object
    }

    IAdditionService.Stub myStub= new IAdditionService.Stub() {     // Implementing aidl interface
        @Override
        public int add(int x, int y) throws RemoteException {
            return x+y;
        }
    };

}


