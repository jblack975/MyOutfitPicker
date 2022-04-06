package com.blackfox.myoutfitpicker.viewmodel

expect class AtomicReference<V> constructor(initialValue: V) {
    fun set(value: V)
    fun get(): V
}
