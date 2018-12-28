package com.pantos27.boringlauncher;

import android.os.SystemClock;
import android.util.Log;

import com.pantos27.boringlauncher.utils.AppExecutors;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import static com.googlecode.eyesfree.utils.LogUtils.TAG;

@RunWith(AndroidJUnit4.class)
public class ExecutorTestJava {
    @Test
    void runParallel(){

        for (int i = 1; i < 9; i++) {
            startIoJob(i);
            startNetworkJob(i);
        }
    }

    private void startNetworkJob(int i){
        //with a runnable
        AppExecutors.INSTANCE.getNetworkIO().execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"IO worker "+i+" start");
                SystemClock.sleep(1000L*(10-i));
                Log.d(TAG,"IO worker "+i+" done");
            }
        });
    }

    private void startIoJob(int i){
        //or with a lambda
        AppExecutors.INSTANCE.getDiskIO().execute(() -> {
            Log.d(TAG,"IO worker "+i+" start");
            SystemClock.sleep(1000L*(10-i));
            Log.d(TAG,"IO worker "+i+" done");
        });
    }
}
