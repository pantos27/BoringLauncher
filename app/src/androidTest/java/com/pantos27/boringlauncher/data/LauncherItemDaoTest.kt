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
import org.junit.*

class LauncherItemDaoTest {
    private var database: AppDatabase? = null
    private var launcherItemDao: LauncherItemDao? = null

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before fun createDb() {
        println("createDB")
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        launcherItemDao = database?.launcherItemDao()

        launcherItemDao?.insertAll(SomeTestDataInt.items)
    }

    @After fun closeDb() {
        println("closeDB")
        database?.close()
    }

    @Test fun testGetGardenPlantings() {
        println("testGetGardenPlantings")
        database ?: return

        val allItemsSortedByLabel = database?.launcherItemDao()?.getAllItemsSortedByLabel()

        val list = SomeTestDataInt.items.sortedBy { it.label }

//        allItemsSortedByLabel?.forEachIndexed { index, launcherItem ->
//            println("${launcherItem.id} $launcherItem")
//            Assert.assertEquals(launcherItem.label, list[index].label)
//        }
    }

//    @Test
//    fun testGetGardenPlanting() {
//        assertThat(
//            getValue(gardenPlantingDao.getGardenPlanting(testGardenPlantingId)),
//            equalTo(testGardenPlanting)
//        )
//    }
//
//    @Test fun testDeleteGardenPlanting() {
//        val gardenPlanting2 = GardenPlanting(
//                testPlants[1].plantId,
//                testCalendar,
//                testCalendar
//        ).also { it.gardenPlantingId = 2 }
//        gardenPlantingDao.insertGardenPlanting(gardenPlanting2)
//        assertThat(getValue(gardenPlantingDao.getGardenPlantings()).size, equalTo(2))
//        gardenPlantingDao.deleteGardenPlanting(gardenPlanting2)
//        assertThat(getValue(gardenPlantingDao.getGardenPlantings()).size, equalTo(1))
//    }
//
//    @Test fun testGetGardenPlantingForPlant() {
//        assertThat(getValue(gardenPlantingDao.getGardenPlantingForPlant(testPlant.plantId)),
//                equalTo(testGardenPlanting))
//    }
//
//    @Test fun testGetGardenPlantingForPlant_notFound() {
//        assertNull(getValue(gardenPlantingDao.getGardenPlantingForPlant(testPlants[2].plantId)))
//    }
//
//    @Test fun testGetPlantAndGardenPlantings() {
//        val plantAndGardenPlantings = getValue(gardenPlantingDao.getPlantAndGardenPlantings())
//        assertThat(plantAndGardenPlantings.size, equalTo(3))
//
//        /**
//         * Only the [testPlant] has been planted, and thus has an associated [GardenPlanting]
//         */
//        assertThat(plantAndGardenPlantings[0].plant, equalTo(testPlant))
//        assertThat(plantAndGardenPlantings[0].gardenPlantings.size, equalTo(1))
//        assertThat(plantAndGardenPlantings[0].gardenPlantings[0], equalTo(testGardenPlanting))
//
//        // The other plants in the database have not been planted and thus have no GardenPlantings
//        assertThat(plantAndGardenPlantings[1].gardenPlantings.size, equalTo(0))
//        assertThat(plantAndGardenPlantings[2].gardenPlantings.size, equalTo(0))
//    }
}