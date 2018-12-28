package com.pantos27.boringlauncher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pantos27.boringlauncher.utils.TAG
import com.pantos27.boringlauncher.utils.printBundle
import ua.at.tsvetkov.util.Log

object PackageActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG,"onReceive: action: ${intent.action}")
        Log.d(TAG,"onReceive: extras: ${intent.extras?.printBundle() ?: "no extras"}")


    }
}
