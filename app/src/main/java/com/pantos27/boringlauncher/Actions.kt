package com.pantos27.boringlauncher

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.pantos27.boringlauncher.data.AppInfo
import android.provider.Settings
import ua.at.tsvetkov.util.Log


fun getActivityIcon(context: Context, packageName: String, activityName: String): Drawable {
    val pm = context.packageManager
    val intent = Intent()
    intent.component = ComponentName(packageName, activityName)
    val resolveInfo = pm.resolveActivity(intent, 0)

    return resolveInfo.loadIcon(pm)
}

fun startMainActivityForPackage(context: Context,pkg: String,bundle: Bundle? = null) {
    val launchIntent = context.packageManager.getLaunchIntentForPackage(pkg)
    startActivity(context,launchIntent,bundle)
}

fun getLauncherActivities(pm: PackageManager) : List<AppInfo>{
    val i = Intent(Intent.ACTION_MAIN, null)
    i.addCategory(Intent.CATEGORY_LAUNCHER)

    return pm.queryIntentActivities(i, 0)
            .map { AppInfo(it.loadLabel(pm), it.activityInfo.packageName, it.activityInfo.loadIcon(pm)) }

}

fun gotToAppSettingsActivity(context: Context,pkg: String){
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", pkg, null))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(context,intent,null)
}

fun indexNewApp(context: Context){
    Intent.ACTION_PACKAGE_ADDED
}