package com.pantos27.boringlauncher

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat.startActivity
import com.pantos27.boringlauncher.data.AppInfo
import android.provider.Settings
import android.widget.Toast
import androidx.core.content.IntentCompat
import com.pantos27.boringlauncher.data.LauncherItem
import com.pantos27.boringlauncher.utils.printBundle
import ua.at.tsvetkov.util.Log
import java.lang.Exception
import java.util.*


fun getActivityIcon(context: Context, packageName: String, activityName: String): Drawable {
    Log.d("getActivityIcon: pkg: $packageName activity: $activityName")
    val pm = context.packageManager
    val intent = Intent()
    intent.component = ComponentName(packageName, activityName)
    val resolveInfo = pm.resolveActivity(intent, 0)
    return resolveInfo.loadIcon(pm)
}

fun startMainActivityForPackage(context: Context,pkg: String,bundle: Bundle? = null) {
    Log.d("startMainActivityForPackage $pkg")
    val launchIntent = context.packageManager.getLaunchIntentForPackage(pkg)
    launchIntent?.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    try {
        launchIntent?.let { startActivity(context,it,bundle) } ?: showToast(context)
    }catch (e: Exception){
        Log.e(e)
        showToast(context)
    }
}

fun startLauncherItem(context: Context,item: LauncherItem){
    val intent = Intent(Intent.ACTION_MAIN)
    intent.addCategory(Intent.CATEGORY_LAUNCHER)
    intent.setClassName(item.pkg,item.mainClass)

    context.startActivity(intent)
}
fun showToast(context: Context){
    Toast.makeText(context,R.string.unable_to_launch_application,Toast.LENGTH_SHORT).show()
}
fun getLauncherActivities(pm: PackageManager) : List<ResolveInfo>{
    Log.d("getLauncherActivities")
    val i = Intent(Intent.ACTION_MAIN, null)
    i.addCategory(Intent.CATEGORY_LAUNCHER)

    return pm.queryIntentActivities(i, PackageManager.MATCH_ALL).asSequence()
//            .onEach { printAppInfo(pm.getPackageInfo(it.activityInfo.packageName,PackageManager.GET_META_DATA)) }
            .filterNot { it.activityInfo.packageName==BuildConfig.APPLICATION_ID }
//            .map { AppInfo(it.loadLabel(pm), it.activityInfo.packageName, it.activityInfo.loadIcon(pm)) }
            .toList()
}

fun printAppInfo(packageInfo: PackageInfo){
    Log.d("printAppInfo ${packageInfo.applicationInfo.name} pkg: ${packageInfo.packageName}")
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Log.d("printAppInfo: category: ${packageInfo.applicationInfo.category}")
    }
    if (packageInfo.applicationInfo.metaData!=null)
        Log.d("metadata: ${packageInfo.applicationInfo.metaData.printBundle()}")
    Log.d("first install ${Date(packageInfo.firstInstallTime)}")

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