package com.pantos27.boringlauncher.utils

import android.os.Bundle

val Any.TAG: String
    get() = javaClass.simpleName

fun Bundle.printBundle() : String{
    return this.keySet().joinToString { "\n$it: ${this.get(it)}" }
}