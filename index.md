### Specs

[![API](https://img.shields.io/badge/API-9%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=9)

### Featured in
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-EasyDeviceInfo-green.svg?style=true)](https://android-arsenal.com/details/1/3562) [![Android Weekly](https://img.shields.io/badge/Android%20Weekly-%23209-blue.svg)](http://androidweekly.net/issues/issue-209) [![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%2399-blue.svg)](https://www.androiddevdigest.com/digest-99/) [![awesome-android](https://cdn.rawgit.com/sindresorhus/awesome/d7305f38d29fed78fa85652e3a63e154dd8e8829/media/badge.svg)](https://snowdream.github.io/awesome-android/Other.html#Utility)

### Show some :heart:
[![GitHub stars](https://img.shields.io/github/stars/nisrulz/easydeviceinfo.svg?style=social&label=Star)](https://github.com/nisrulz/easydeviceinfo) [![GitHub forks](https://img.shields.io/github/forks/nisrulz/easydeviceinfo.svg?style=social&label=Fork)](https://github.com/nisrulz/easydeviceinfo/fork) [![GitHub watchers](https://img.shields.io/github/watchers/nisrulz/easydeviceinfo.svg?style=social&label=Watch)](https://github.com/nisrulz/easydeviceinfo) [![GitHub followers](https://img.shields.io/github/followers/nisrulz.svg?style=social&label=Follow)](https://github.com/nisrulz/easydeviceinfo)  
[![Twitter Follow](https://img.shields.io/twitter/follow/nisrulz.svg?style=social)](https://twitter.com/nisrulz) 


Android library to get device information in a super easy way.

The library is built for simplicity and approachability. It not only eliminates most boilerplate code for dealing with device information, but also provides an easy and simple API to retrieve them.

# Changelog

Starting with `1.1.8`, Changes exist in the [releases tab](https://github.com/nisrulz/easydeviceinfo/releases).

# Including in your project
EasyDeviceInfo is available in the Jcenter, so getting it as simple as adding it as a dependency, where `{latest version}` corresponds to published version in Jcenter

+ EasyDeviceInfo [ ![Download](https://api.bintray.com/packages/nisrulz/maven/com.github.nisrulz%3Aeasydeviceinfo/images/download.svg) ](https://bintray.com/nisrulz/maven/com.github.nisrulz%3Aeasydeviceinfo/_latestVersion)
```gradle
compile 'com.github.nisrulz:easydeviceinfo:{latest version}'
```

+	EasyDeviceInfo-Ads   [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/easydeviceinfo-ads/images/download.svg) ](https://bintray.com/nisrulz/maven/easydeviceinfo-ads/_latestVersion)
```gradle
compile 'com.github.nisrulz:easydeviceinfo-ads:{latest version}'
```

+	EasyDeviceInfo-Base   [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/easydeviceinfo-base/images/download.svg) ](https://bintray.com/nisrulz/maven/easydeviceinfo-base/_latestVersion)
```gradle
compile 'com.github.nisrulz:easydeviceinfo-base:{latest version}'
```

The Mods are available through different library as below
+  `easydeviceinfo`  [ ![Download](https://api.bintray.com/packages/nisrulz/maven/com.github.nisrulz%3Aeasydeviceinfo/images/download.svg) ](https://bintray.com/nisrulz/maven/com.github.nisrulz%3Aeasydeviceinfo/_latestVersion) <a href="http://www.methodscount.com/?lib=com.github.nisrulz%3Aeasydeviceinfo%3A2.2.0"><img src="https://img.shields.io/badge/Methods count-core: 2 | deps: 15592-e91e63.svg"/></a>
    - Main library which transitively includes both `easydeviceinfo-ads` and `easydeviceinfo-base`.

+ `easydeviceinfo-ads`  [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/easydeviceinfo-ads/images/download.svg) ](https://bintray.com/nisrulz/maven/easydeviceinfo-ads/_latestVersion) <a href="http://www.methodscount.com/?lib=com.github.nisrulz%3Aeasydeviceinfo-ads%3A2.2.0"><img src="https://img.shields.io/badge/Methods count-core: 14 | deps: 15316-e91e63.svg"/></a>
    -  EasyDeviceInfo Ads, which facilitates information regarding ads. Has a dependency on `play-services-base`.
    -  **Supported Mods**
       + [EasyAdsMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easyadsmod)
+ `easydeviceinfo-base`  [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/easydeviceinfo-base/images/download.svg) ](https://bintray.com/nisrulz/maven/easydeviceinfo-base/_latestVersion) <a href="http://www.methodscount.com/?lib=com.github.nisrulz%3Aeasydeviceinfo-base%3A2.2.0"><img src="https://img.shields.io/badge/Methods count-core: 262 | deps: 10-e91e63.svg"/></a>
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

Now, Create an instance of one of the Mods ( **Easy\*Mod** class ), i.e `EasyConfigMod`
```java
EasyConfigMod easyConfigMod = new EasyConfigMod(context);
```
Next call an available function on the ***easyConfigMod*** instance such as
```java
String time_in_ms= String.valueOf(easyConfigMod.getTime());
```

Now each **Mods** has a certain set of functions you can call on them to retrieve device information.

The list is as follows

#### **EasyAdsMod** 
To get Advertiser's ID

```java
//Get Android Advertiser ID
easyIdMod.getAndroidAdId(MainActivity.this, new EasyIdMod.AdIdentifierCallback() {
    @Override
    public void onSuccess(String adIdentifier, boolean adDonotTrack) {
        // Do something with the advertiser's ID
    }
});
```

#### **EasyIdMod** 
```java
EasyIdMod easyIdMod = new EasyIdMod(context);
```
|Value|functionName|returns
|---|---|---|
|PseudoID|`getPseudoUniqueID()`|String
|Android ID|`getAndroidID()`|String

More Functions

+ To get User-Agent, call it from the UI thread **ONLY**

  ```java
    String ua = easyDeviceMod.getUA()
  ```
  
+ To get GSF ID
  ```java
  String gsf_id = getGSFID();
  ```

  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
  ```

+ To get Email IDs
  ```java
  //Get Google Email ID
  String[] emailIds = easyIdMod.getAccounts();
    StringBuilder emailString = new StringBuilder();
    if (emailIds != null && emailIds.length > 0) {
      for (String e : emailIds) {
        emailString.append(e).append("\n");
      }
    } else {
      emailString.append("-");
    }

  String emailId=emailString.toString();
  ```
  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
  ```

#### **EasyConfigMod**
```java
EasyConfigMod easyConfigMod = new EasyConfigMod(context);
```
|Value|functionName|returns
|---|---|---|
|Is running on emulator|`isRunningOnEmulator()`|boolean
|Time (ms)|`getTime()`|long
|Formatted Time (24Hr)|`getFormattedTime()`|String
|Up Time (ms)|`getUpTime()`|long
|Formatted Up Time (24Hr)|`getFormattedUpTime()`|String
|Get Current Date|`getCurrentDate()`|Date
|Get Formatted Date|`getFormattedDate()`|String
|Has SD Card|`hasSdCard()`|boolean

Functions which return multiple results

+ Device Ringer Mode
  ```java
  int ringer_mode =easyConfigMod.getDeviceRingerMode();
  ```
  
 Then match it against the constants provided
  
  ```java
 switch (ringer_mode) {
      case EasyConfigMod.RINGER_MODE_NORMAL:
        System.out.println("Ringer mode : Normal");
        break;
      case EasyConfigMod.RINGER_MODE_VIBRATE:
        System.out.println("Ringer mode : Vibrate");
        break;
      case EasyConfigMod.RINGER_MODE_SILENT:
        System.out.println("Ringer mode : Silent");
        break;
      default:
        //do nothing
        break;
    }
  ```
  
  where constants available are 
    + `EasyConfigMod.RINGER_MODE_NORMAL`
    + `EasyConfigMod.RINGER_MODE_VIBRATE`
    + `EasyConfigMod.RINGER_MODE_SILENT`

#### **EasyNetworkMod** 

```java
EasyNetworkMod easyNetworkMod = new EasyNetworkMod(context);
```
|Value|functionName|returns
|---|---|---|
|WiFi State|`isNetworkAvailable()`|boolean
|WiFi State|`isWifiEnabled()`|boolean
|IPv4 Address|`getIPv4Address()`|String
|IPv6 Address|`getIPv6Address()`|String


+ To get WiFi MAC Address
  ```java
  String wifi_mac = easyNetworkMod.getWifiMAC();
  ```

  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
  ```

Functions which return multiple results

+ To get network type
  ```java
  int network_type =easyNetworkMod.getNetworkType();
  ```
  
  Then match it against the constants provided
  
  ```java
switch () {
      case EasyNetworkMod.CELLULAR_UNKNOWN:
        System.out.println("Network Type : Unknown");
        break;
      case EasyNetworkMod.CELLULAR_UNIDENTIFIED_GEN:
        System.out.println("Network Type : Cellular Unidentified Generation");
        break;
      case EasyNetworkMod.CELLULAR_2G:
        System.out.println("Network Type : Cellular 2G");
        break;
      case EasyNetworkMod.CELLULAR_3G:
        System.out.println("Network Type : Cellular 3G");
        break;
      case EasyNetworkMod.CELLULAR_4G:
        System.out.println("Network Type : Cellular 4G");
        break;
      case EasyNetworkMod.WIFI_WIFIMAX:
        System.out.println("Network Type : WIFI");
        break;
      default:
        // Do nothing
        break;
    }
  ```
  
  where constants available are 
    + `EasyNetworkMod.CELLULAR_UNKNOWN`
    + `EasyNetworkMod.CELLULAR_UNIDENTIFIED_GEN`
    + `EasyNetworkMod.CELLULAR_2G`
    + `EasyNetworkMod.CELLULAR_3G`
    + `EasyNetworkMod.CELLULAR_4G`
    + `EasyNetworkMod.WIFI_WIFIMAX`

+ Include the required permission in your AndroidManifest.xml
   ```xml
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
  <uses-permission android:name="android.permission.INTERNET"/>
  ```

#### **EasyMemoryMod** 
```java
EasyMemoryMod easyMemoryMod = new EasyMemoryMod(context);
```
|Value|functionName|returns(bytes)
|---|---|---|
|Total RAM|`getTotalRAM()`|long
|Available Internal Memory|`getAvailableInternalMemorySize()`|long
|Available External Memory|`getAvailableExternalMemorySize()`|long
|Total Internal Memory|`getTotalInternalMemorySize()`|long
|Total External Memory|`getTotalExternalMemorySize()`|long

Util functions for conversions

|Value|functionName|returns
|---|---|---|
|Convert to Kb|`convertToKb(long valInBytes)`|float
|Convert to Mb|`convertToMb(long valInBytes)`|float
|Convert to Gb|`convertToGb(long valInBytes)`|float
|Convert to Tb|`convertToTb(long valInBytes)`|float

#### **EasyAppMod** 
```java
EasyAppMod easyAppMod = new EasyAppMod(context);
```
|Value|functionName|returns
|---|---|---|
|Activity Name|`getActivityName()`|String
|Package Name|`getPackageName()`|String
|AppStore|`getStore()`|String
|App Name|`getAppName()`|String
|App Version|`getAppVersion()`|String
|App Version Code|`getAppVersionCode()`|String
|Is App with Packagename Installed|`isAppInstalled(String packageName)`|boolean
|Is Permission Granted|`isPermissionGranted(String permission)`|boolean



#### **EasyBatteryMod** 
```java
EasyBatteryMod easyBatteryMod = new EasyBatteryMod(context);
```
|Value|functionName|returns
|---|---|---|
|Battery Percentage (%)|`getBatteryPercentage()`|int
|Is Device Charging|`isDeviceCharging()`|boolean
|Technology used by battery|`getBatteryTechnology()`|String
|Temperature (Deg Celsius)|`getBatteryTemperature()`|float
|Voltage (mV)|`getBatteryVoltage()`|int
|Is battery present|`isBatteryPresent()`|boolean

Functions which return multiple results

+ Battery Health
  ```java
  int battery_health =easyBatteryMod.getBatteryHealth();
  ```
  
 Then match it against the constants provided
  
  ```java
 switch (battery_health) {
      case EasyBatteryMod.HEALTH_GOOD:
        System.out.println("Battery health : Good");
        break;
      case EasyBatteryMod.HEALTH_HAVING_ISSUES:
        System.out.println("Battery health : Having issues");
        break;
      default:
        System.out.println("Battery health : Having issues");
        break;
    }
  ```
  
  where constants available are 
    + `EasyBatteryMod.HEALTH_GOOD`
    + `EasyBatteryMod.HEALTH_HAVING_ISSUES`
 
+ Charging Source
  ```java
  int charging_source =easyBatteryMod.getChargingSource();
  ```
  
 Then match it against the constants provided
  
  ```java
  switch (charging_source) {
      case EasyBatteryMod.CHARGING_VIA_AC:
        System.out.println("Device charging via AC");
        break;
      case EasyBatteryMod.CHARGING_VIA_USB:
        System.out.println("Device charging via USB");
        break;
      case EasyBatteryMod.CHARGING_VIA_WIRELESS:
        System.out.println("Device charging via Wireless");
        break;
      case EasyBatteryMod.CHARGING_VIA_UNKNOWN_SOURCE:
        System.out.println("Device charging via Unknown Source");
        break;
      default:
        System.out.println("Device charging via Unknown Source");
        break;
    }
  ```
  
  where constants available are 
    + `EasyBatteryMod.CHARGING_VIA_AC:`
    + `EasyBatteryMod.CHARGING_VIA_USB`
    + `EasyBatteryMod.CHARGING_VIA_WIRELESS`
    + `EasyBatteryMod.CHARGING_VIA_UNKNOWN_SOURCE`



#### **EasyBluetoothMod** 
```java
EasyBluetoothMod easyBluetoothMod = new EasyBluetoothMod(context);
```
+ To get Bluetooth MAC Address
  ```java
 
  String bluetooth_mac = easyBluetoothMod.getBluetoothMAC();
  ```

  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.BLUETOOTH"/>
  ```

#### **EasyCpuMod** 
```java
 EasyCpuMod easyCpuMod = new EasyCpuMod();
```
|Value|functionName|returns
|---|---|---|
|Supported ABIS|`getStringSupportedABIS()`|String
|Supported 32 bit ABIS|`getStringSupported32bitABIS()`|String
|Supported 64 bit ABIS|`getStringSupported64bitABIS()`|String

Functions which return multiple results

+ To get Supported ABIS
```java
StringBuilder supportABI = new StringBuilder();
   for (String abis : easyCpuMod.getSupportedABIS()) {
       supportABI.append(abis).append("\n");
   }

String supportedABI=supportABI.toString();
```

+ To get Supported 32 Bit ABIS
```java
StringBuilder support32ABI = new StringBuilder();
   for (String abis : easyCpuMod.getSupported32bitABIS()) {
       support32ABI.append(abis).append("\n");
   }

String supported32ABI=support32ABI.toString();
```

+ To get Supported 64 Bit ABIS
```java
StringBuilder support64ABI = new StringBuilder();
   for (String abis : easyCpuMod.getSupported64bitABIS()) {
       support64ABI.append(abis).append("\n");
   }

String supported64ABI=support64ABI.toString();
```


#### **EasyDeviceMod** 
```java
 EasyDeviceMod easyDeviceMod = new EasyDeviceMod(context);
```
|Value|functionName|returns
|---|---|---|
|IMEI|`getIMEI()`|String
|Screen Display ID|`getScreenDisplayID()`|String
|Build Version Codename|`getBuildVersionCodename()`|String
|Build Version Incremental|`getBuildVersionIncremental()`|String
|Build Version SDK|`getBuildVersionSDK()`|int
|Build ID|`getBuildID()`|String
|Manufacturer|`getManufacturer()`|String
|Model|`getModel()`|String
|OS Codename|`getOSCodename()`|String
|OS Version|`getOSVersion()`|String
|Phone Number|`getPhoneNo()`|String
|Radio Hardware Version|`getRadioVer()`|String
|Product|`getProduct()`|String
|Device|`getDevice()`|String
|Board|`getBoard()`|String
|Hardware|`getHardware()`|String
|Bootloader|`getBootloader()`|String
|Fingerprint|`getFingerprint()`|String
|Is Device rooted|`isDeviceRooted()`|boolean
|Build Brand|`getBuildBrand()`|String
|Build Host|`getBuildHost()`|String
|Build Tags|`getBuildTags()`|String
|Build Time|`getBuildTime()`|long
|Build User|`getBuildUser()`|String
|Build Version Release|`getBuildVersionRelease()`|String

Functions which return multiple results

+ To get device type
  ```java
  int device_type = easyDeviceMod.getDeviceType(activity);
  ```
  
  Then match it against the constants provided
  
  ```java
  switch (device_type) {
     case EasyDeviceMod.DEVICE_TYPE_WATCH:
       System.out.println("watch");
       break;
     case EasyDeviceMod.DEVICE_TYPE_PHONE:
       System.out.println("phone");
       break;
     case EasyDeviceMod.DEVICE_TYPE_PHABLET:
       System.out.println("phablet");
       break;
     case EasyDeviceMod.DEVICE_TYPE_TABLET:
       System.out.println("tablet");
       break;
     case EasyDeviceMod.DEVICE_TYPE_TV:
       System.out.println("tv");
       break;
   }
  ```
  
  where constants available are 
    + `EasyDeviceMod.DEVICE_TYPE_WATCH`
    + `EasyDeviceMod.DEVICE_TYPE_PHONE`
    + `EasyDeviceMod.DEVICE_TYPE_PHABLET`
    + `EasyDeviceMod.DEVICE_TYPE_TABLET`
    + `EasyDeviceMod.DEVICE_TYPE_TV`

+ To get phone type
  ```java
  int phone_type = easyDeviceMod.getPhoneType();
  ```
  
  Then match it against the constants provided
  
  ```java
  switch (phone_type) {
      case EasyDeviceMod.PHONE_TYPE_CDMA:
        System.out.println("Phone Type : CDMA");
        break;
      case EasyDeviceMod.PHONE_TYPE_GSM:
        System.out.println("Phone Type : GSM");
        break;
      case EasyDeviceMod.PHONE_TYPE_NONE:
        System.out.println("Phone Type : None");
        break;
      default:
        System.out.println("Phone Type : Unknown");
        break;
    }
  ```
  
  where constants available are 
    + `EasyDeviceMod.PHONE_TYPE_CDMA`
    + `EasyDeviceMod.PHONE_TYPE_GSM`
    + `EasyDeviceMod.PHONE_TYPE_NONE`

+ To get device orientation
  ```java
  int device_orientation = easyDeviceMod.getOrientation(activity)
  ```
  
  Then match it against the constants provided
  
  ```java
 switch (device_orientation) {
      case EasyDeviceMod.ORIENTATION_LANDSCAPE:
        System.out.println("Orientation : Landscape");
        break;
      case EasyDeviceMod.ORIENTATION_PORTRAIT:
       System.out.println("Orientation : Portrait");
        break;
      case EasyDeviceMod.ORIENTATION_UNKNOWN:
       System.out.println("Orientation : Unknown");
        break;
      default:
        System.out.println("Orientation : Unknown");
        break;
    }
  ```
  
  where constants available are 
    + `EasyDeviceMod.ORIENTATION_LANDSCAPE`
    + `EasyDeviceMod.ORIENTATION_PORTRAIT`
    + `EasyDeviceMod.ORIENTATION_UNKNOWN`
    
#### **EasyDisplayMod** 
```java
 EasyDisplayMod easyDisplayMod = new EasyDisplayMod(context);
```
|Value|functionName|returns
|---|---|---|
|Display Resolution|`getResolution()`|String
|Screen Density|`getDensity()`|String
|Display XY Coordinate|`getDisplayXYCoordinates(motionevent)`|int[]

#### **EasySimMod** 
```java
EasySimMod easySimMod = new EasySimMod(context);
```

|Value|functionName|returns
|---|---|---|
|IMSI|`getIMSI()`|String
|SIM Serial Number|`getSIMSerial()`|String
|Country|`getCountry()`|String
|Carrier|`getCarrier()`|String
|SIM Locked|`isSimNetworkLocked()`|boolean




#### **EasyLocationMod** 
```java
 EasyLocationMod easyLocationMod = new EasyLocationMod(context);
```
+ To get Latitude-Longitude (Geo)
  ```java
  //Get Lat-Long
  double[] l = easyLocationMod.getLatLong();
  String lat = String.valueOf(l[0]);
  String lon = String.valueOf(l[1]);
  ```
  + Include the one of the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
  ```
  Or
  ```xml
  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
  ```


#### **EasyNfcMod** 
```java
EasyNfcMod easyNfcMod = new EasyNfcMod(context);
```
|Value|functionName|returns
|---|---|---|
|Is NFC present|`isNfcPresent()`|boolean
|Is NFC enabled|`isNfcEnabled()`|boolean


#### **EasyDeviceInfo** 
```java
EasyDeviceInfo easyDeviceInfo = new EasyDeviceInfo();
```
|Value|functionName|returns
|---|---|---|
|Library version|`getLibraryVersion()`|String

To modify the value that is returned when a function cannot get the value from device, simply init `EasyDeviceInfo` with the new value before using any **Easy\*Mod**
```java
EasyDeviceInfo easyDeviceInfo = new EasyDeviceInfo("na");
```
this will make the functions return `na` when the value is not found or an error is encountered.

By default if you don't use this, the value returned is `unknown`.

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