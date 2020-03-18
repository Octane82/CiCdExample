package com.everlapp.cicdexample;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;

import java.util.Random;

public class ServiceForTest extends Service {

    public final static String SEED_KEY = "seed_key";

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();

    // Random number generator
    private final Random mGenerator = new Random();


    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        ServiceForTest getService() {
            // Return this instance of ServiceForTest so clients can call public methods
            return ServiceForTest.this;
        }
    }


    public int getRandomInt() {
        return mGenerator.nextInt(100);
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
