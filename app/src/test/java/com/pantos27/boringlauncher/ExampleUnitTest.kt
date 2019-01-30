package com.pantos27.boringlauncher

import android.os.Bundle
import com.pantos27.boringlauncher.data.LauncherItem
import org.junit.Test

import org.junit.Assert.*
import java.lang.NullPointerException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    val KEY = "Key"
    @Test
    fun bundleEnumTest() {
        val bundle = Bundle(2)
        bundle.putSerializable(KEY, AppInfoListFragment.Mode.Recent)
        bundle.putInt("Inttt",3)

        val int = bundle.getInt("Inttt")
        assertEquals(3,int)
        val mode = bundle.getSerializable(KEY)
        mode?.let {

            assertEquals(it, AppInfoListFragment.Mode.Recent)
        } ?: throw NullPointerException("mode is null")
    }

    @Test fun equalsTest(){
        val app = LauncherItem(SomeTestData.app2.label, SomeTestData.app2.pkg, SomeTestData.app2.mainClass)
        assert(SomeTestData.app2 == app)
        assertFalse(SomeTestData.app1.equals(null))
        assertFalse(SomeTestData.app1.equals("something"))
    }

    object SomeTestData {

        val app1 = LauncherItem("BaseFook", "com.basefook.app","com.basefook.app.MainActivity")
        val app2 = LauncherItem("Zasham", "com.zasham.app","com.zasham.app.MainActivity")
        val app3 = LauncherItem("Cmail", "com.boogle.cmail","com.boogle.cmail.MainActivity")

        val items = listOf(app1, app2, app3)

    }
}
