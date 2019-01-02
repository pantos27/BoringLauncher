package com.pantos27.boringlauncher

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.pantos27.boringlauncher.utils.TAG
import com.pantos27.boringlauncher.utils.printBundle
import ua.at.tsvetkov.util.Log

/**
 * Handles updating, removing and adding triggers from the package manager
 * checks if an update to the currently installed app table is due
 */
object PackageActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG,"onReceive: action: ${intent.action}")
        Log.d(TAG,"onReceive: extras: ${intent.extras?.printBundle() ?: "no extras"}")
        val replacing = intent.getBooleanExtra(Intent.EXTRA_REPLACING,false)
        when (intent.action){
            Intent.ACTION_PACKAGE_REPLACED -> update(context,intent) //updating
            Intent.ACTION_PACKAGE_REMOVED -> if (!replacing) update(context) //uninstalling and not updating
            Intent.ACTION_PACKAGE_ADDED -> if (!replacing) update(context) //installing and not updating
            else -> Log.d("I don't know how I got ${intent.action}")
        }

    }

    private fun update(context: Context,intent: Intent?=null){
        Log.d(intent?.action)
        val uid = intent?.getIntExtra(Intent.EXTRA_UID,-1) ?: -1

        val pkg = context.packageManager.getNameForUid(uid)
        Log.d(pkg)



    }
}
