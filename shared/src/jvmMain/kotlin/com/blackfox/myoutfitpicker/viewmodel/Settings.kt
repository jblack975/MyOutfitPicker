package com.blackfox.myoutfitpicker.viewmodel

import com.russhwolf.settings.coroutines.FlowSettings

actual fun settings(): FlowSettings =
    DataStoreSettings()
