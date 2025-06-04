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

import java.nio.ByteBuffer

/**
 * Hardware status callback interface for monitoring USB camera hardware status changes,
 * including device failures, control value changes, and processing status updates.
 *
 * @author Created by jiangdg on 2023/12/01
 */
interface IHardwareStatusCallback {
    /**
     * Called when hardware status changes occur
     *
     * @param statusClass Status class type:
     *                    - 0x10: CONTROL - General control status
     *                    - 0x11: CONTROL_CAMERA - Camera-specific control status
     *                    - 0x12: CONTROL_PROCESSING - Processing control status
     * @param event Event identifier (device dependent)
     * @param selector Selector identifier (device dependent)
     * @param statusAttribute Status attribute type:
     *                        - 0x00: VALUE_CHANGE - Hardware value changed
     *                        - 0x01: INFO_CHANGE - Information changed
     *                        - 0x02: FAILURE_CHANGE - Hardware failure detected
     * @param data Optional status data (may be null)
     */
    fun onStatus(statusClass: Int, event: Int, selector: Int, statusAttribute: Int, data: ByteBuffer?)
}