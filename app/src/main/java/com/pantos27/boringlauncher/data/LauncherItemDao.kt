package com.pantos27.boringlauncher.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LauncherItemDao {
    @Query("SELECT * FROM launcher_items")
    fun getAllItems() : List<LauncherItem>

    @Query("SELECT * FROM launcher_items ORDER BY label")
    fun getAllItemsSortedByLabel() : LiveData<List<LauncherItem>>

    @Query("SELECT * FROM launcher_items ORDER BY times_used DESC, last_used DESC")
    fun getAllItemsSortedByLastUsed() : LiveData<List<LauncherItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<LauncherItem>)
}