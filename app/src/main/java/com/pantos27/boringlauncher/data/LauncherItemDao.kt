package com.pantos27.boringlauncher.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface LauncherItemDao {
    @Query("SELECT * FROM launcher_items")
    fun getAllItems() : List<LauncherItem>

    @Query("SELECT * FROM launcher_items ORDER BY label")
    fun getAllItemsAlphabeticallySorted() : List<LauncherItem>
}