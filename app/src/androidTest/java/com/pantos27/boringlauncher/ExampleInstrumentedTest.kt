package com.pantos27.boringlauncher

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pantos27.boringlauncher.data.AppDatabase
import com.pantos27.boringlauncher.data.LauncherItemDao
import com.pantos27.boringlauncher.data.SomeTestDataInt
import org.junit.After

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var database: AppDatabase
    private lateinit var launcherItemDao: LauncherItemDao
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        println("createDB")
        val context : Context = getApplicationContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        launcherItemDao = database.launcherItemDao()

        // Insert plants in non-alphabetical order to test that results are sorted by name
        launcherItemDao.insertAll(SomeTestDataInt.items)
    }

    @After
    fun closeDb() {
        println("closeDb")
        database.close()
    }


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val context : Context = getApplicationContext()
        assertEquals("com.pantos27.boringlauncher", context.packageName)
    }

    @Test fun testSortOrder(){
        val allItemsSortedByLabel = database.launcherItemDao().getAllItemsSortedByLabel()

        val list = SomeTestDataInt.items.sortedBy { it.label }
//
//        allItemsSortedByLabel.forEachIndexed { index, launcherItem ->
//            println(launcherItem)
//            assertEquals(launcherItem.label,list[index].label)
//        }
    }
}
