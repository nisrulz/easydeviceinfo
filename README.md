![Image](/img/github_banner.png)

### Specs

[![API](https://img.shields.io/badge/API-9%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=9)

### Badges/Featured In
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-EasyDeviceInfo-green.svg?style=true)](https://android-arsenal.com/details/1/3562) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23209-blue.svg)](http://androidweekly.net/issues/issue-209) [![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%2399-blue.svg)](https://www.androiddevdigest.com/digest-99/) [![awesome-android](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://snowdream.github.io/awesome-android/Other.html#Utility)

**Also included in**
+ [Awesome Android Newsletter #Issue 9](https://android.libhunt.com/newsletter/9)

### Show some :heart:
[![GitHub stars](https://img.shields.io/github/stars/nisrulz/easydeviceinfo.svg?style=social&label=Star)](https://github.com/nisrulz/easydeviceinfo) [![GitHub forks](https://img.shields.io/github/forks/nisrulz/easydeviceinfo.svg?style=social&label=Fork)](https://github.com/nisrulz/easydeviceinfo/fork) [![GitHub watchers](https://img.shields.io/github/watchers/nisrulz/easydeviceinfo.svg?style=social&label=Watch)](https://github.com/nisrulz/easydeviceinfo) [![GitHub followers](https://img.shields.io/github/followers/nisrulz.svg?style=social&label=Follow)](https://github.com/nisrulz/easydeviceinfo)  
[![Twitter Follow](https://img.shields.io/twitter/follow/nisrulz.svg?style=social)](https://twitter.com/nisrulz) 


Android library to get device information in a super easy way.

The library is built for simplicity and approachability. It not only eliminates most boilerplate code for dealing with device information, but also provides an easy and simple API to retrieve them.

#### **Note**
EasyDeviceInfo is split into multiple module-libraries , v2.2.0 onwards. Use the appropriate one as per your requirement.

+  `easydeviceinfo`
    - Main library which transitively includes both `easydeviceinfo-ads` and `easydeviceinfo-base`.

+ `easydeviceinfo-ads`
    -  EasyDeviceInfo Ads, which facilitates information regarding ads. Has a dependency on `play-services-base`.
    -  **Supported Mods**
       + [EasyAdsMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easyadsmod)
+ `easydeviceinfo-base`
    -  EasyDeviceInfo Base, which facilitates information regarding the device.
    -  **Supported Mods**
      + [EasyAppMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easyappmod)
      + [EasyBatteryMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easybatterymod)
      + [EasyBluetoothMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easybluetoothmod)
      + [EasyConfigMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easyconfigmod)
      + [EasyCpuMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easycpumod)
      + [EasyDeviceMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easydevicemod)
      + [EasyDisplayMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easydisplaymod)
      + [EasyIdMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easyidmod)
      + [EasyLocationMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easylocationmod)
      + [EasyMemoryMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easymemorymod)
      + [EasyNetworkMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easynetworkmod)
      + [EasyNfcMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easynfcmod)
      + [EasySimMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easysimmod)

# Changelog

Starting with `1.1.8`, Changes exist in the [releases tab](https://github.com/nisrulz/easydeviceinfo/releases).

# Including in your project
EasyDeviceInfo is available in the Jcenter, so getting it as simple as adding it as a dependency.

```gradle
dependencies {
    def easyDeviceInfoVersion = {latest version}

    // Base + Ads Bundled Library
    compile "com.github.nisrulz:easydeviceinfo:$easyDeviceInfoVersion"

    // Base Composite
    compile "com.github.nisrulz:easydeviceinfo-base:$easyDeviceInfoVersion"

    // Ads Composite
    compile "com.github.nisrulz:easydeviceinfo-ads:$easyDeviceInfoVersion"
}

```

where `{latest version}` corresponds to published version in Jcenter
+ `easydeviceinfo` - [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/com.github.nisrulz%3Aeasydeviceinfo/images/download.svg) ](https://bintray.com/nisrulz/maven/com.github.nisrulz%3Aeasydeviceinfo/_latestVersion)
+	`easydeviceinfo-ads` - [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/easydeviceinfo-ads/images/download.svg) ](https://bintray.com/nisrulz/maven/easydeviceinfo-ads/_latestVersion)
+	`easydeviceinfo-base` - [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/easydeviceinfo-base/images/download.svg) ](https://bintray.com/nisrulz/maven/easydeviceinfo-base/_latestVersion)

# Usage Docs/Wiki

Introducing **Mods** in **EasyDeviceInfo v2**!

 ***Mods*** or ***Modules*** are the new way to retrieve information. They let you get information in a very segmented manner and the best part is you only initialize the Mods you need in your project. Pretty rad , eh ?  I know.

> The whole api has been reworked in v2.x.x , however the functions remain the same, hence now you need to migrate to v2.x.x from 1.x.x.

### Simple example

Now to use them, create an instance of one of the Mods ( **Easy\*Mod** class ), i.e `EasyConfigMod`
```java
EasyConfigMod easyConfigMod = new EasyConfigMod(context);
```
Next call an available function on the ***easyConfigMod*** instance such as
```java
String time_in_ms= String.valueOf(easyConfigMod.getTime());
```

Now each **Mods** has a certain set of functions you can call on them to retrieve device information. i.e for  **EasyConfigMod**

|Value|functionName|returns
|---|---|---|
|Is running on emulator|`isRunningOnEmulator()`|boolean
|Time (ms)|`getTime()`|long
|Formatted Time (24Hr)|`getFormattedTime()`|String
|Up Time (ms)|`getUpTime()`|long
|Formatted Up Time (24Hr)|`getFormattedUpTime()`|String

#### Android Studio support
+ Include a required permission check
  
  ![PermissionCheck](https://github.com/nisrulz/easydeviceinfo/blob/develop/img/permissioncheck.gif)

+ Setup all constants returned in a switch statement
  
  ![SetupSwitch](https://github.com/nisrulz/easydeviceinfo/blob/develop/img/usingintedefs.gif)
  
  This applies to all annotations bundled with easydeviceinfo. Checkout the wiki to see where these annotations can be applied.
  + `@RingerMode`
  + `@DeviceType`
  + `@PhoneType`
  + `@OrientationType`
  + `@NetworkType`
  + `@BatteryHealth`
  + `@ChargingVia`

### <center> :page_with_curl: For more info , check the **[Wiki Docs](https://github.com/nisrulz/easydeviceinfo/wiki/Usage)** </center>

# Pull Requests
I welcome and encourage all pull requests. It usually will take me within 24-48 hours to respond to any issue or request. Here are some basic rules to follow to ensure timely addition of your request:
  1. Match coding style (braces, spacing, etc.) This is best achieved using `CMD`+`Option`+`L` (Reformat code) on Mac (not sure for Windows) with Android Studio defaults.
  2. If its a feature, bugfix, or anything please only change code to what you specify.
  3. Please keep PR titles easy to read and descriptive of changes, this will make them easier to merge :)
  4. Pull requests _must_ be made against `develop` branch. Any other branch (unless specified by the maintainers) will get rejected.
  5. Check for existing [issues](https://github.com/nisrulz/easydeviceinfo/issues) first, before filing an issue.  
  6. Have fun!

## Apps using EasyDeviceInfo
If you are using EasyDeviceInfo in your app and would like to be listed here, please let me know by opening a [new issue](https://github.com/nisrulz/easydeviceinfo/issues/new)!

 * [EasyDeviceInfo](https://play.google.com/store/apps/details?id=in.excogitation.deviceinfo)


### Created & Maintained By
[Nishant Srivastava](https://github.com/nisrulz) ([@nisrulz](https://www.twitter.com/nisrulz))


> If you found this library helpful or you learned something from the source code and want to thank me, consider [buying me a cup of](https://www.paypal.me/nisrulz) :coffee:

License
=======

    Copyright 2016 Nishant Srivastava

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

