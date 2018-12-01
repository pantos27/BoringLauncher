package com.pantos27.boringlauncher


import android.content.Intent
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ua.at.tsvetkov.util.Log

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java,true,true)

    @Test
    fun mainActivityTest() {
        Log.d("mainActivityTest")

        mActivityTestRule.finishActivity()
    }
}
