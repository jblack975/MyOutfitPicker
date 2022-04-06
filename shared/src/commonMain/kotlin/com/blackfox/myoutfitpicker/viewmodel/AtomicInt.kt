package com.blackfox.myoutfitpicker.viewmodel

expect class AtomicInt(initialValue: Int) {
    fun get(): Int
    fun addAndGet(delta: Int): Int
}
