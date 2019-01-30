/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pantos27.boringlauncher.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.core.app.ApplicationProvider
import com.pantos27.boringlauncher.BoringApplication
import org.junit.*
import ua.at.tsvetkov.util.Log

class LauncherItemDaoTest {
    private var database: AppDatabase? = null
    private var launcherItemDao: LauncherItemDao? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        println("createDB")
        val context = ApplicationProvider.getApplicationContext<BoringApplication>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        launcherItemDao = database?.launcherItemDao()

        launcherItemDao?.insertAll(SomeTestDataInt.items)
    }

    @After
    fun closeDb() {
        println("closeDB")
        database?.close()
    }

    @Test
    fun testGetGardenPlantings() {
        println("testGetGardenPlantings")
        database ?: return

        val allItemsSortedByLabel = database?.launcherItemDao()?.getAllItemsSortedByLabel()

        val list = SomeTestDataInt.items.sortedBy { it.label }

        allItemsSortedByLabel?.value?.forEachIndexed { index, launcherItem ->
            println("${launcherItem.id} $launcherItem")
            Assert.assertEquals(launcherItem.label, list[index].label)
        } ?: assert(false)
    }
    @Test
    fun testUpdateTimesUsed(){
        val launcherItems = database?.launcherItemDao()?.getAllItems()
        val launcherItem = launcherItems?.get(0)
        launcherItem?.let { item ->


            Log.d("after ${item.id}")
            Log.d(item.toString())

            database?.launcherItemDao()?.updateTimesUsed(item.id)
            Log.d("all")

            database?.launcherItemDao()?.getAllItems()?.find { it.id == item.id }.let {
                Assert.assertNotNull(it)

                Assert.assertEquals(item.timesUsed + 1, it?.timesUsed)
            }
        } ?: assert(false)
    }
    @Test
    fun testUpdateLastUsed(){
        val launcherItems = database?.launcherItemDao()?.getAllItems()
        val launcherItem = launcherItems?.get(0)
        launcherItem?.let { item ->

            Log.d(item.toString())

            database?.launcherItemDao()?.updateLastUsed(item.id)

            database?.launcherItemDao()?.getAllItems()?.find { it.id == item.id }.let {
                Assert.assertNotNull(it)
                Assert.assertTrue(it?.lastUsed!! >item.lastUsed)
            }
        } ?: assert(false)
    }

    @Test
    fun testUpdateMany(){
        val launcherItems = database?.launcherItemDao()?.getAllItems()
        launcherItems?.get(0)?.label = "Zero"
        val launcherItem = launcherItems?.get(0)
        launcherItem?.let { item ->


            Log.d("after ${item.id}")
            Log.d(item.toString())

            database?.launcherItemDao()?.updateTimesUsed(item.id)
            Log.d("all")

            database?.launcherItemDao()?.getAllItems()?.find { it.id == item.id }.let {
                Assert.assertNotNull(it)

                Assert.assertEquals(item.timesUsed + 1, it?.timesUsed)
            }
        } ?: assert(false)
    }
    @Test
    fun testIllegalValue(){
        database?.launcherItemDao()?.updateTimesUsed(10)

    }

}
