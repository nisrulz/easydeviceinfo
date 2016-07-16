![Image](https://github.com/nisrulz/easydeviceinfo/blob/master/img/github_banner.png)


### Specs
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/easydeviceinfo/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/easydeviceinfo) [![API](https://img.shields.io/badge/API-9%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=9) <a href="http://www.methodscount.com/?lib=com.github.nisrulz%3Aeasydeviceinfo%3A2.0.1"><img src="https://img.shields.io/badge/Size-27 KB-e91e63.svg"/></a>

### Featured in
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-EasyDeviceInfo-green.svg?style=true)](https://android-arsenal.com/details/1/3562) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23209-blue.svg)](http://androidweekly.net/issues/issue-209) [![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%2399-blue.svg)](https://www.androiddevdigest.com/digest-99/) [![awesome-android](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://snowdream.github.io/awesome-android/Other.html#Utility)

### Show some :heart:
[![GitHub stars](https://img.shields.io/github/stars/nisrulz/easydeviceinfo.svg?style=social&label=Star)](https://github.com/nisrulz/easydeviceinfo) [![GitHub forks](https://img.shields.io/github/forks/nisrulz/easydeviceinfo.svg?style=social&label=Fork)](https://github.com/nisrulz/easydeviceinfo/fork) [![GitHub watchers](https://img.shields.io/github/watchers/nisrulz/easydeviceinfo.svg?style=social&label=Watch)](https://github.com/nisrulz/easydeviceinfo) [![GitHub followers](https://img.shields.io/github/followers/nisrulz.svg?style=social&label=Follow)](https://github.com/nisrulz/easydeviceinfo)  
[![Twitter Follow](https://img.shields.io/twitter/follow/nisrulz.svg?style=social)](https://twitter.com/nisrulz) 


Android library to get device information in a super easy way.

The library is built for simplicity and approachability. It not only eliminates most boilerplate code for dealing with device information, but also provides an easy and simple API to retrieve them.

Demo App

[![playstore](https://github.com/nisrulz/easydeviceinfo/raw/master/img/google-play-store.png)](https://play.google.com/store/apps/details?id=in.excogitation.deviceinfo)

# Changelog

Starting with `1.1.8`, Changes exist in the [releases tab](https://github.com/nisrulz/easydeviceinfo/releases).

# Including in your project
- EasyDeviceInfo is available in the MavenCentral, so getting it as simple as adding it as a dependency
```gradle
compile 'com.github.nisrulz:easydeviceinfo:2.1.0'
```

# Usage Docs/Wiki

Introducing **Mods** in **EasyDeviceInfo v2**!

 ***Mods*** or ***Modules*** are the new way to retrieve information. They let you get information in a very segmented manner and the best part is you only initialize the Mods you need in your project. Pretty rad , eh ?  I know.

> The whole api has been reworked in v2.x.x , however the functions remain the same, hence now you need to migrate to v2.x.x from 1.x.x.


### Supported Mods 
+ EasyAppMod
+ EasyBatteryMod
+ EasyBluetoothMod
+ EasyConfigMod
+ EasyCpuMod
+ EasyDeviceMod
+ EasyDisplayMod
+ EasyIdMod
+ EasyLocationMod
+ EasyMemoryMod
+ EasyNetworkMod
+ EasyNfcMod
+ EasySimMod

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

### <center> :page_with_curl: For more info , check the **[Wiki Docs](https://github.com/nisrulz/easydeviceinfo/wiki)** </center>

# Pull Requests
I welcome and encourage all pull requests. It usually will take me within 24-48 hours to respond to any issue or request. Here are some basic rules to follow to ensure timely addition of your request:
  1. Match coding style (braces, spacing, etc.) This is best achieved using `CMD`+`Option`+`L` (Reformat code) on Mac (not sure for Windows) with Android Studio defaults.
  2. If its a feature, bugfix, or anything please only change code to what you specify.
  3. Please keep PR titles easy to read and descriptive of changes, this will make them easier to merge :)
  4. Pull requests _must_ be made against `develop` branch. Any other branch (unless specified by the maintainers) will get rejected.
  5. Check for existing [issues](https://github.com/nisrulz/easydeviceinfo/issues) first, before filing an issue.  
  6. Have fun!

### Created & Maintained By
[Nishant Srivastava](https://github.com/nisrulz) ([@nisrulz](https://www.twitter.com/nisrulz))
