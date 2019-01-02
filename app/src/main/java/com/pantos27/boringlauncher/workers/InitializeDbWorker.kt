package com.pantos27.boringlauncher.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pantos27.boringlauncher.data.AppDatabase
import com.pantos27.boringlauncher.data.LauncherItem
import com.pantos27.boringlauncher.getLauncherActivities
import ua.at.tsvetkov.util.Log

class InitializeDbWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        val packageManager = this.applicationContext.packageManager
        val launcherActivities = getLauncherActivities(packageManager)

        val packages = launcherActivities.map { it.activityInfo.packageName }.toSet()

        val list = launcherActivities.map {
            LauncherItem(it.loadLabel(packageManager).toString(),
                    it.activityInfo.packageName,
                    it.activityInfo.name)
        }


        AppDatabase.getInstance(applicationContext).launcherItemDao().insertAll(list)

        return Result.success()
    }

}