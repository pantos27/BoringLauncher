package com.pantos27.boringlauncher.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "launcher_items")
data class LauncherItem(
        @ColumnInfo(name = "label") val label: String,
        @ColumnInfo(name = "package_name") val pkg: String,
        @ColumnInfo(name = "main_class") val mainClass: String,
        @ColumnInfo(name = "sort_order") val sortOrder: Short = Short.MAX_VALUE,
        @ColumnInfo(name = "category") val category: Int? = null,
        @ColumnInfo(name = "times_used") val timesUsed: Int = 0,
        @ColumnInfo(name = "last_used") val lastUsed: Calendar = Calendar.getInstance(),
        @ColumnInfo(name = "install_time") val installTime: Calendar = Calendar.getInstance()) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}