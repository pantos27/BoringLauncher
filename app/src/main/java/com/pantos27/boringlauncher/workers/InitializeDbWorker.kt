package com.pantos27.boringlauncher.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.pantos27.boringlauncher.getLauncherActivities
import ua.at.tsvetkov.util.Log

class InitializeDbWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {
    override fun doWork(): Result {
        getLauncherActivities(applicationContext.packageManager)

        return Result.success()
    }

}