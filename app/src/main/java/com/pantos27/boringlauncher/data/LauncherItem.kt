package com.pantos27.boringlauncher.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "launcher_items")
data class LauncherItem(
        @ColumnInfo(name = "label") val label: String,
        @ColumnInfo(name = "package_name") val pkg: String,
        @ColumnInfo(name = "main_class") val mainClass: String,
        @ColumnInfo(name = "sort_order") val sortOrder: Short = Short.MAX_VALUE,
        @ColumnInfo(name = "category") val category: Int? = null,
        @ColumnInfo(name = "times_used") val timesUsed: Int = 0,
        @ColumnInfo(name = "last_used") val lastUsed: Long = System.currentTimeMillis(),
        @ColumnInfo(name = "install_time") val installTime: Long = System.currentTimeMillis()) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    override operator fun equals(other: Any?): Boolean {
        return other is LauncherItem && other.pkg==this.pkg && other.mainClass == this.mainClass
    }

    override fun hashCode(): Int {
        var result = label.hashCode()
        result = 31 * result + pkg.hashCode()
        result = 31 * result + mainClass.hashCode()
        result = 31 * result + sortOrder
        result = 31 * result + (category ?: 0)
        result = 31 * result + timesUsed
        result = 31 * result + lastUsed.hashCode()
        result = 31 * result + installTime.hashCode()
        return result
    }
}