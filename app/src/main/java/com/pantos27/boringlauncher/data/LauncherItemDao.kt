package com.pantos27.boringlauncher.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LauncherItemDao {
    @Query("SELECT * FROM launcher_items")
    fun getAllItems() : List<LauncherItem>

    @Query("SELECT * FROM launcher_items WHERE id=:id")
    fun getItem(id: Long = 0) : LauncherItem

    @Query("SELECT * FROM launcher_items ORDER BY label")
    fun getAllItemsSortedByLabel() : LiveData<List<LauncherItem>>

    @Query("SELECT * FROM launcher_items ORDER BY times_used DESC, last_used DESC")
    fun getAllItemsSortedByLastUsed() : LiveData<List<LauncherItem>>

    @Query("UPDATE launcher_items SET times_used=times_used+1 WHERE id = :id")
    fun updateTimesUsed(id: Long)

    @Query("UPDATE launcher_items SET last_used=:last_used WHERE id = :id")
    fun updateLastUsed(id: Long,last_used : Long = System.currentTimeMillis())


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(items: List<LauncherItem>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItems(vararg items: LauncherItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateItem(items: LauncherItem)
}