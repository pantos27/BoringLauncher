package com.pantos27.boringlauncher

import android.os.Bundle
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
}
