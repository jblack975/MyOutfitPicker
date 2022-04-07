/*
 * Copyright 2020 Russell Wolf
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blackfox.myoutfitpicker.viewmodel

import com.russhwolf.settings.coroutines.FlowSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

// TODO: copied from https://github.com/russhwolf/multiplatform-settings/blob/master/multiplatform-settings-datastore/src/androidMain/kotlin/com/russhwolf/settings/datastore/DataStoreSettings.kt
//  because of collision between multiplatform-settings-coroutines-native-mt
//  and multiplatform-settings-coroutines modules
public class DataStoreSettings() : FlowSettings {
    override suspend fun clear() {
        TODO("Not yet implemented")
    }

    override fun getBooleanFlow(key: String, defaultValue: Boolean): Flow<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getBooleanOrNullFlow(key: String): Flow<Boolean?> {
        TODO("Not yet implemented")
    }

    override fun getDoubleFlow(key: String, defaultValue: Double): Flow<Double> {
        TODO("Not yet implemented")
    }

    override fun getDoubleOrNullFlow(key: String): Flow<Double?> {
        TODO("Not yet implemented")
    }

    override fun getFloatFlow(key: String, defaultValue: Float): Flow<Float> {
        TODO("Not yet implemented")
    }

    override fun getFloatOrNullFlow(key: String): Flow<Float?> {
        TODO("Not yet implemented")
    }

    override fun getIntFlow(key: String, defaultValue: Int): Flow<Int> {
        TODO("Not yet implemented")
    }

    override fun getIntOrNullFlow(key: String): Flow<Int?> {
        TODO("Not yet implemented")
    }

    override fun getLongFlow(key: String, defaultValue: Long): Flow<Long> {
        TODO("Not yet implemented")
    }

    override fun getLongOrNullFlow(key: String): Flow<Long?> {
        TODO("Not yet implemented")
    }

    override fun getStringFlow(key: String, defaultValue: String): Flow<String> {
        TODO("Not yet implemented")
    }

    override fun getStringOrNullFlow(key: String): Flow<String?> {
        TODO("Not yet implemented")
    }

    override suspend fun hasKey(key: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun keys(): Set<String> {
        TODO("Not yet implemented")
    }

    override suspend fun putBoolean(key: String, value: Boolean) {
        TODO("Not yet implemented")
    }

    override suspend fun putDouble(key: String, value: Double) {
        TODO("Not yet implemented")
    }

    override suspend fun putFloat(key: String, value: Float) {
        TODO("Not yet implemented")
    }

    override suspend fun putInt(key: String, value: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun putLong(key: String, value: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun putString(key: String, value: String) {
        TODO("Not yet implemented")
    }

    override suspend fun remove(key: String) {
        TODO("Not yet implemented")
    }

    override suspend fun size(): Int {
        TODO("Not yet implemented")
    }
}
