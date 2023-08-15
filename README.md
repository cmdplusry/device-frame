Device Frame Generator
=======================

[![Build Status](https://travis-ci.org/f2prateek/android-device-frame-generator.png)](https://travis-ci.org/f2prateek/android-device-frame-generator)

Wrap your app screenshots in real device artwork. For more information, see the [website](http://f2prateek.com/android-device-frame-generator).

Adding Devices
--------------

To add new devices, you'll need to add 7 images in the [`app/src/main/res/drawable-nodpi`](https://github.com/f2prateek/android-device-frame-generator/tree/master/app/src/main/res/drawable-nodpi):
* `device_land_back`
* `device_land_glare`
* `device_land_shadow`
* `device_port_back`
* `device_port_glare`
* `device_port_shadow`
* `device_thumb`

All these should be in the png format and named as above - with `device` replaced with a key that will identify this set of frames.
Once that's done, simply add the metadata in [`DeviceModule.java`](https://github.com/f2prateek/android-device-frame-generator/blob/master/app/src/main/java/com/f2prateek/dfg/DeviceModule.java) located at `app/src/main/java/com/f2prateek/dfg/DeviceModule.java`.
Here's what an example for the Nexus 5 would look like.

```java
@Provides(type = SET) Device provideNexus5() {
    return new Device.Builder().setId("nexus_5")
        .setName("Nexus 5")
        .setUrl("http://www.google.com/nexus/5/")
        .setPhysicalSize(5.43f)
        .setDensity("XXHDPI")
        .setLandOffset(436, 306)
        .setPortOffset(306, 436)
        .setPortSize(1080, 1920)
        .setRealSize(1080, 1920)
        .addProductId("hammerhead")
        .build();
  }
```

You can see what the metadata fields mean at [`Device.java`](https://github.com/f2prateek/android-device-frame-generator/blob/master/app/src/main/java/com/f2prateek/dfg/model/Device.java#L27)

Building
---------
Simply execute `./gradlew clean assembleDebug checkDebug`.

To run a full build (including release), you'll need to fill in some missing files.

First add a `AndroidManifest.xml` under `app/src/release` with this template, replacing `YOUR_CRASHLYTICS_KEY_HERE` with the appropriate field.
```xml
<?xml version="1.0" encoding="utf-8"?>

<manifest xmlns:android="http://schemas.android.com/apk/res/android">

  <application>
    <meta-data android:name="com.crashlytics.ApiKey"
        android:value="YOUR_CRASHLYTICS_KEY_HERE"/>
  </application>

</manifest>
```
You'll also need to add a `signing.properties` file at the root of the project, with the correct values

```
STORE_FILE=[keystore file]
STORE_PASSWORD=[keystore password]
KEY_ALIAS=[keystore alias]
KEY_PASSWORD=[alias password]
```

Then execute `./gradlew clean build`