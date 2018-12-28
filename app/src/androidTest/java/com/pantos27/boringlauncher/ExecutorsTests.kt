package com.pantos27.boringlauncher

import android.os.SystemClock
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pantos27.boringlauncher.utils.AppExecutors
import com.pantos27.boringlauncher.utils.TAG
import com.pantos27.boringlauncher.utils.runOnNetworkThread
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExecutorsTests{
    @Test
    fun runParallel(){

        (1..9).forEach {
            startIOJob(it)
            startNetworkJob(it)
        }
        SystemClock.sleep(40_000)
        Log.d(TAG,"main done")


    }
    //can be used this way
    private fun startNetworkJob(i: Int){
        runOnNetworkThread {
            Log.d(TAG,"net worker $i start")
            SystemClock.sleep(1000L*(10-i))
            Log.d(TAG,"net worker $i done")
        }
    }
    //or this way
    private fun startIOJob(i: Int){
        AppExecutors.diskIO.execute {
            Log.d(TAG,"IO worker $i start")
            SystemClock.sleep(1000L*(10-i))
            Log.d(TAG,"IO worker $i done")
        }
    }
}