package com.pantos27.boringlauncher

import com.pantos27.boringlauncher.data.LauncherItem

object SomeTestData {

    val app1 = LauncherItem("BaseFook", "com.basefook.app","com.basefook.app.MainActivity")
    val app2 = LauncherItem("Zasham", "com.zasham.app","com.zasham.app.MainActivity")
    val app3 = LauncherItem("Cmail", "com.boogle.cmail","com.boogle.cmail.MainActivity")

    val items = listOf(app1, app2, app3)

}