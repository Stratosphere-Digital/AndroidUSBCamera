/*
 * Copyright 2017-2023 Jiangdg
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jiangdg.ausbc.callback

/**
 * Hardware button callback interface for detecting physical shutter button presses
 * on USB cameras that support hardware buttons.
 *
 * @author Created by jiangdg on 2023/12/01
 */
interface IHardwareButtonCallback {
    /**
     * Called when a hardware button is pressed or released
     *
     * @param button Button identifier (hardware dependent)
     * @param state Button state:
     *              - 1: Button pressed
     *              - 0: Button released
     */
    fun onButton(button: Int, state: Int)
}