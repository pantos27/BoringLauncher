package com.pantos27.boringlauncher.data

import android.graphics.drawable.Drawable
import androidx.room.Entity

@Entity
data class AppInfo(val label: CharSequence, val packageName: String, val drawable: Drawable)