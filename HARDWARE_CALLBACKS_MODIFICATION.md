# AndroidUSBCamera Library Modifications for Hardware Callbacks

## Summary
Modified the AndroidUSBCamera library to expose hardware button detection and status monitoring capabilities through public APIs.

## Changes Made

### 1. Module Dependency Exposure
**File:** `libausbc/build.gradle`
```gradle
// Changed from 'implementation' to 'api' to expose libuvc interfaces
api project(path: ':libuvc')
```

### 2. New Public Callback Interfaces
**Files Created:**
- `libausbc/src/main/java/com/jiangdg/ausbc/callback/IHardwareButtonCallback.kt`
- `libausbc/src/main/java/com/jiangdg/ausbc/callback/IHardwareStatusCallback.kt`

These provide clean, well-documented wrapper interfaces for the underlying UVC callbacks.

### 3. Enhanced CameraUVC Class
**File:** `libausbc/src/main/java/com/jiangdg/ausbc/camera/CameraUVC.kt`

Added four new public methods:

```kotlin
// Direct UVC interface access
fun setButtonCallback(callback: com.jiangdg.uvc.IButtonCallback?)
fun setStatusCallback(callback: com.jiangdg.uvc.IStatusCallback?)

// Wrapper interface access (recommended)
fun setHardwareButtonCallback(callback: IHardwareButtonCallback?)
fun setHardwareStatusCallback(callback: IHardwareStatusCallback?)
```

## Interface Usage

### IHardwareButtonCallback
Detects hardware button presses on USB cameras:
- `onButton(button: Int, state: Int)` - Called when hardware buttons are pressed/released
- `state == 1` - Button pressed
- `state == 0` - Button released

### IHardwareStatusCallback  
Monitors hardware status changes:
- `onStatus(statusClass: Int, event: Int, selector: Int, statusAttribute: Int, data: ByteBuffer?)` - Called on hardware status changes
- Status classes: `0x10` (CONTROL), `0x11` (CONTROL_CAMERA), `0x12` (CONTROL_PROCESSING)
- Status attributes: `0x00` (VALUE_CHANGE), `0x01` (INFO_CHANGE), `0x02` (FAILURE_CHANGE)

## Public API Access

After these modifications, consumers of the library can now access:

1. **Direct UVC interfaces:** `com.jiangdg.uvc.IButtonCallback` and `com.jiangdg.uvc.IStatusCallback`
2. **Wrapper interfaces:** `com.jiangdg.ausbc.callback.IHardwareButtonCallback` and `com.jiangdg.ausbc.callback.IHardwareStatusCallback`
3. **CameraUVC methods:** Both direct and wrapper callback setter methods

## Integration in Calla Camera Module

The modified library enables the Calla Camera module to:

1. **Hardware Shutter Button Detection:**
   - Automatically detect physical shutter button presses
   - Trigger image capture when button is pressed
   - Send events to React Native for UI updates

2. **Hardware Status Monitoring:**
   - Monitor camera hardware for failures and changes
   - Log hardware status events for debugging
   - Handle critical failures automatically

## Build Instructions

After making these modifications:

1. Build the AndroidUSBCamera library:
   ```bash
   cd /path/to/AndroidUSBCamera
   ./gradlew clean build publishToMavenLocal
   ```

2. Update the Calla Camera module to use the modified library
3. Import the new interfaces: `com.jiangdg.ausbc.callback.IHardwareButtonCallback` and `com.jiangdg.ausbc.callback.IHardwareStatusCallback`

## Compatibility

- All modifications are backward compatible
- No existing functionality is broken
- Original UVC interfaces remain accessible
- New wrapper interfaces provide additional convenience and documentation