package com.pantos27.boringlauncher

import android.app.Application
import ua.at.tsvetkov.util.ComponentLog
import ua.at.tsvetkov.util.Log
import ua.at.tsvetkov.util.interceptor.LogCatInterceptor
import ua.at.tsvetkov.util.interceptor.LogInterceptor

class BoringApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG){
            Log.addInterceptor( LogCatInterceptor() )
            ComponentLog.enableComponentsChangesLogging(this)
        }else{
            Log.addInterceptor( BoringLogInterceptor())
        }

        Log.setLogOutlined(false)
    }

    override fun onTerminate() {
        super.onTerminate()
        if (BuildConfig.DEBUG)
            ComponentLog.disableComponentsChangesLogging(this)
    }

    class BoringLogInterceptor: LogInterceptor(){
        override fun log(level: Level?, tag: String?, msg: String?, t: Throwable?) {
            //todo: send somewhere
        }

    }
}