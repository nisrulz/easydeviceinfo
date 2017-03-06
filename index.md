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
+  `easydeviceinfo`  [ ![Download](https://api.bintray.com/packages/nisrulz/maven/com.github.nisrulz%3Aeasydeviceinfo/images/download.svg) ](https://bintray.com/nisrulz/maven/com.github.nisrulz%3Aeasydeviceinfo/_latestVersion)
    - Main library which transitively includes both `easydeviceinfo-ads` and `easydeviceinfo-base`.

+ `easydeviceinfo-ads`  [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/easydeviceinfo-ads/images/download.svg) ](https://bintray.com/nisrulz/maven/easydeviceinfo-ads/_latestVersion)
    -  EasyDeviceInfo Ads, which facilitates information regarding ads. Has a dependency on `play-services-base`.
    -  **Supported Mods**
       + [EasyAdsMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easyadsmod)
+ `easydeviceinfo-base`  [ ![Jcenter](https://api.bintray.com/packages/nisrulz/maven/easydeviceinfo-base/images/download.svg) ](https://bintray.com/nisrulz/maven/easydeviceinfo-base/_latestVersion)
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
      + [EasySensorMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easysensormod)
      + [EasyFingerprintMod](https://github.com/nisrulz/easydeviceinfo/wiki/Usage#easyfingerprintmod)

Now, Create an instance of one of the Mods ( **Easy\*Mod** class ), i.e `EasyConfigMod`
```java
EasyConfigMod easyConfigMod = new EasyConfigMod(context);
```
Next call an available function on the ***easyConfigMod*** instance such as
```java
String time_in_ms= String.valueOf(easyConfigMod.getTime());
```

#### There is some Android Studio support available for you to code faster when using easydeviceinfo.
+ Include a required permission check
  
  ![PermissionCheck](https://github.com/nisrulz/easydeviceinfo/blob/develop/img/permissioncheck.gif)

+ Setup all constants returned in a switch statement.
  
  ![SetupSwitch](https://github.com/nisrulz/easydeviceinfo/blob/develop/img/usingintedefs.gif)
  
  This applies to all annotations bundled with easydeviceinfo. Checkout the wiki to see where these annotations can be applied.
  + `@RingerMode`
  + `@DeviceType`
  + `@PhoneType`
  + `@OrientationType`
  + `@NetworkType`
  + `@BatteryHealth`
  + `@ChargingVia`

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

#### **EasySensorMod**
```java
EasySensorMod easySensorMod = new EasySensorMod(context);

// Get list of sensors installed in the device
List<Sensor> list = easySensorMod.getAllSensors();
```

Each Sensor element in the list has the following functions

|Value|functionName|returns
|---|---|---|
|Vendor|`getVendor()`|String
|Version|`getVersion()`|String
|Power|`getPower()`|String
|Resolution|`getResolution()`|String
|Max Range|`getMaximumRange()`|String
|Name|`getName()`|String

#### **EasyFingerprintMod**
```java
EasyFingerprintMod easyFingerprintMod = new EasyFingerprintMod(context);
```

|Value|functionName|returns
|---|---|---|
|Is Fingerprint Sensor present|`isFingerprintSensorPresent()`|boolean
|Are fingerprints enrolled|`areFingerprintsEnrolled()`|boolean

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
   @RingerMode 
   int ringermode = easyConfigMod.getDeviceRingerMode();
  ```
  
 Then match it against the constants provided
  
  ```java
 switch (ringermode) {
      case RingerMode.NORMAL:
        System.out.println("Ringer mode : Normal");
        break;
      case RingerMode.VIBRATE:
        System.out.println("Ringer mode : Vibrate");
        break;
      case RingerMode.SILENT:
        System.out.println("Ringer mode : Silent");
        break;
      default:
        //do nothing
        break;
    }
  ```
  
  where constants available are 
    + `RingerMode.NORMAL`
    + `RingerMode.VIBRATE`
    + `RingerMode.SILENT`

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
|WiFi SSID|`getWifiSSID()`|String
|Wifi Link Speed|`getWifiLinkSpeed()`|String
|WiFi BSSID|`getWifiBSSID()`|String


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
   @NetworkType
   int networkType = easyNetworkMod.getNetworkType();
  ```
  
  Then match it against the constants provided
  
  ```java
  switch (networkType) {
      case NetworkType.CELLULAR_UNKNOWN:
        System.out.println("Network Type : Unknown");
        break;
      case NetworkType.CELLULAR_UNIDENTIFIED_GEN:
        System.out.println("Network Type : Cellular Unidentified Generation");
        break;
      case NetworkType.CELLULAR_2G:
        System.out.println("Network Type : Cellular 2G");
        break;
      case NetworkType.CELLULAR_3G:
        System.out.println("Network Type : Cellular 3G");
        break;
      case NetworkType.CELLULAR_4G:
        System.out.println("Network Type : Cellular 4G");
        break;
      case NetworkType.WIFI_WIFIMAX:
        System.out.println("Network Type : WIFI/WIFIMAX");
        break;
      case NetworkType.UNKNOWN:
      default:
        System.out.println("Network Type : Unknown");
        break;
    }
  ```
  
  where constants available are 
    + `NetworkType.CELLULAR_UNKNOWN`
    + `NetworkType.CELLULAR_UNIDENTIFIED_GEN`
    + `NetworkType.CELLULAR_2G`
    + `NetworkType.CELLULAR_3G`
    + `NetworkType.CELLULAR_4G`
    + `NetworkType.WIFI_WIFIMAX`
    + `NetworkType.UNKNOWN`

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
  @BatteryHealth
  int batteryHealth = easyBatteryMod.getBatteryHealth();
  ```
  
 Then match it against the constants provided
  
  ```java
 switch (batteryHealth) {
      case BatteryHealth.GOOD:
        System.out.println("Battery health : Good");
        break;
      case BatteryHealth.HAVING_ISSUES:
        System.out.println("Battery health : Having issues");
        break;
      default:
        System.out.println("Battery health : Having issues");
        break;
    }
  ```
  
  where constants available are 
    + `BatteryHealth.GOOD`
    + `BatteryHealth.HAVING_ISSUES`
 
+ Charging Source
  ```java
  @ChargingVia
  int isChargingVia = easyBatteryMod.getChargingSource();
  ```
  
 Then match it against the constants provided
  
  ```java
  switch (isChargingVia) {
      case ChargingVia.AC:
        System.out.println("Device charging via AC");
        break;
      case ChargingVia.USB:
        System.out.println("Device charging via USB");
        break;
      case ChargingVia.WIRELESS:
        System.out.println("Device charging via Wireless");
        break;
      case ChargingVia.UNKNOWN_SOURCE:
        System.out.println("Device charging via Unknown Source");
        break;
      default:
        System.out.println("Device charging via Unknown Source");
        break;
    }
  ```
  
  where constants available are 
    + `ChargingVia.AC`
    + `ChargingVia.USB`
    + `ChargingVia.WIRELESS`
    + `ChargingVia.UNKNOWN_SOURCE`



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
  @DeviceType 
  int deviceType = easyDeviceMod.getDeviceType(activity);
  ```
  
  Then match it against the constants provided
  
  ```java
  switch (deviceType) {
     case DeviceType.WATCH:
       System.out.println("watch");
       break;
     case DeviceType.PHONE:
       System.out.println("phone");
       break;
     case DeviceType.PHABLET:
       System.out.println("phablet");
       break;
     case DeviceType.TABLET:
       System.out.println("tablet");
       break;
     case DeviceType.TV:
       System.out.println("tv");
       break;
   }
  ```
  
  where constants available are 
    + `DeviceType.WATCH`
    + `DeviceType.PHONE`
    + `DeviceType.PHABLET`
    + `DeviceType.TABLET`
    + `DeviceType.TV`

+ To get phone type
  ```java
   @PhoneType 
   int phoneType = easyDeviceMod.getPhoneType();
  ```
  
  Then match it against the constants provided
  
  ```java
  switch (phoneType) {
      case PhoneType.CDMA:
        System.out.println("Phone Type : CDMA");
        break;
      case PhoneType.GSM:
        System.out.println("Phone Type : GSM");
        break;
      case PhoneType.NONE:
        System.out.println("Phone Type : None");
        break;
      default:
        System.out.println("Phone Type : Unknown");
        break;
    }
  ```
  
  where constants available are 
    + `PhoneType.CDMA`
    + `PhoneType.GSM`
    + `PhoneType.NONE`

+ To get device orientation
  ```java
  @OrientationType 
  int orientationType = easyDeviceMod.getOrientation(this);
  ```
  
  Then match it against the constants provided
  
  ```java
 switch (orientationType) {
      case OrientationType.LANDSCAPE:
        System.out.println("Orientation : Landscape");
        break;
      case OrientationType.PORTRAIT:
       System.out.println("Orientation : Portrait");
        break;
      case OrientationType.UNKNOWN:
       System.out.println("Orientation : Unknown");
        break;
      default:
        System.out.println("Orientation : Unknown");
        break;
    }
  ```
  
  where constants available are 
    + `OrientationType.LANDSCAPE`
    + `OrientationType.PORTRAIT`
    + `OrientationType.UNKNOWN`
    
#### **EasyDisplayMod** 
```java
 EasyDisplayMod easyDisplayMod = new EasyDisplayMod(context);
```
|Value|functionName|returns
|---|---|---|
|Display Resolution|`getResolution()`|String
|Screen Density|`getDensity()`|String
|Display XY Coordinate|`getDisplayXYCoordinates(motionevent)`|int[]
|Refresh Rate|`getRefreshRate()`|float
|Screen Orientation|`getDefaultOrientation()`|float
|Screen Physical Size|`getPhysicalSize()`|float

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
|Get Active SimInfo|`getActiveMultiSimInfo()`|List<SubscriptionInfo>
|Is MultiSim|`isMultiSim()`|boolean
|Get number of active SIM|`getNumberOfActiveSim()`|int

> NOTE : Multi SIM info works for devices running API 21 and above i.e Lollipop +

[Check the sample app for working example](https://github.com/nisrulz/easydeviceinfo/blob/master/app/src/main/java/github/nisrulz/projecteasydeviceinfo/MainActivity.java#L137-L168)

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
|Value|functionName|returns
|---|---|---|
|Library version|`EasyDeviceInfo.getLibraryVersion()`|String
|Enable Debugging|`EasyDeviceInfo.debug()`|void

To modify the value that is returned when a function cannot get the value from device, simply init `EasyDeviceInfo` with the new value before using any **Easy\*Mod**
```java
EasyDeviceInfo.setNotFoundVal("na");
```
this will make the functions return `na` when the value is not found or an error is encountered.

By default if you don't use this, the value returned is `unknown`.

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
