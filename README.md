# EasyDeviceInfo    [![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/easydeviceinfo/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.nisrulz/easydeviceinfo) [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-EasyDeviceInfo-green.svg?style=true)](https://android-arsenal.com/details/1/3562)

[![Twitter](https://img.shields.io/badge/Twitter-@nisrulz-blue.svg?style=flat)](http://twitter.com/nisrulz)

Android library to get device information in a super easy way.

Listed on [awesome-android](https://github.com/snowdream/awesome-android#utility)

Checkout the app using the same

[![playstore](https://github.com/nisrulz/easydeviceinfo/raw/master/img/google-play-store.png)](https://play.google.com/store/apps/details?id=in.excogitation.deviceinfo)


#Integration
- EasyDeviceInfo is available in the MavenCentral, so getting it as simple as adding it as a dependency
```gradle
compile 'com.github.nisrulz:easydeviceinfo:1.1.9'
```


#Usage
+ Create an instance of ***EasyDeviceInfo*** class
```java
EasyDeviceInfo easyDeviceInfo=new EasyDeviceInfo(context);
```
+ Next call the required function on the ***easyDeviceInfo*** instance such as
```java
String value=easyDeviceInfo.functionName();
```

|Value|functionName|returns
|---|---|---|
|Formatted Time (24Hr)|`getFormatedTime()`|String
|Time (ms)|`getTime()`|long
|Language|`getLanguage()`|String
|Android ID|`getAndroidID()`|String
|IMSI|`getIMSI()`|String
|Pseudo ID|`getPsuedoUniqueID()`|String
|Device Serial Number|`getSerial()`|String
|SIM Serial Number|`getSIMSerial()`|String
|Manufacturer|`getManufacturer()`|String
|Model|`getModel()`|String
|OS Codename|`getOSCodename()`|String
|OS Version|`getOSVersion()`|String
|Country|`getCountry()`|String
|Display Resolution|`getResolution()`|String
|Phone Number|`getPhoneNo()`|String
|ISP/Carrier|`getCarrier()`|String
|Radio Hardware Version|`getRadioVer()`|String
|Product|`getProduct()`|String
|Device|`getDevice()`|String
|Board|`getBoard()`|String
|Hardware|`getHardware()`|String
|Bootloader|`getBootloader()`|String
|IP Address|`getIPAddress(true)`|String
|Fingerprint|`getFingerprint()`|String
|Screen Density|`getDensity()`|String
|Installer Store|`getStore()`|String
|Is running on emulator|`isRunningOnEmulator()`|boolean
|Is Device rooted|`isDeviceRooted()`|boolean
|Build Brand|`getBuildBrand()`|String
|Build Host|`getBuildHost()`|String
|Build Tags|`getBuildTags()`|String
|Build Time|`getBuildTime()`|long
|Build User|`getBuildUser()`|String
|Build Version Release|`getBuildVersionRelease()`|String
|Battery Percentage|`getBatteryPercentage()`|int
|Is Device charging|`isDeviceCharging()`|boolean
|Is Device charging via USB|`isDeviceChargingUSB()`|boolean
|Is Device charging via AC|`isDeviceChargingAC()`|boolean
|WiFi State|`isWifiEnabled()`|boolean
|Screen Display ID|`getScreenDisplayID()`|String
|Build Version Codename|`getBuildVersionCodename()`|String
|Build Version Incremental|`getBuildVersionIncremental()`|String
|Build Version SDK|`getBuildVersionSDK()`|int
|Build ID|`getBuildID()`|String
|Supported ABIS|`getStringSupportedABIS()`|String
|Supported 32 bit ABIS|`getStringSupported32bitABIS()`|String
|Supported 64 bit ABIS|`getStringSupported64bitABIS()`|String


-


###Methods that need you to call it from the UI thread only

+ To get User-Agent

  ```java
    String ua = getUA()
  ```


###Methods that need you to declare permission in `AndroidManifest.xml`

+ To get IMEI
  ```java
    String imei = getIMEI()
  ```

  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
  ```


+ To check the type of network or checking is internet available
 ```java
  String network_type = getNetworkType();
  boolean is_network_available = isNetworkAvailable();
  ```

  + Include the required permission in your AndroidManifest.xml
   ```xml
  <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
  <uses-permission android:name="android.permission.INTERNET"/>
  ```

+ To get GSF ID
  ```java
  String gsf_id = getGSFID();
  ```

  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
  ```

+ To get WiFi MAC Address
 ```java
  String wifi_mac = getWifiMAC();
  ```

  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
  ```

+ To get Bluetooth MAC Address
  ```java
  String bluetooth_mac = getBluetoothMAC();
  ```

  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.BLUETOOTH"/>
  ```


+ To get Latitude-Longitude (Geo)
  ```java
  //Get Lat-Long
  double[] l = easyDeviceInfo.getLatLong();
  String lat = String.valueOf(l[0]);
  String lon = String.valueOf(l[1]);
  ```
  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
  ```


+ To get Email IDs
  ```java
  //Get Google Email ID
  StringBuilder emailIDs = new StringBuilder();
      for (String eid : easyDeviceInfo.getAccounts(this)) {
              emailIDs.append(eid).append("\n");
          };

  String emailId=emailIDs.toString();
  ```
  + Include the required permission in your AndroidManifest.xml

  ```xml
  <uses-permission android:name="android.permission.GET_ACCOUNTS"/>
  ```

###Other multi-value details can be retrieved as below

+ To get Supported ABIS
```java
StringBuilder supportABI = new StringBuilder();
   for (String abis : easyDeviceInfo.getSupportedABIS()) {
       supportABI.append(abis).append("\n");
   }

String supportedABI=supportABI.toString();
```

+ To get Supported 32 Bit ABIS
```java
StringBuilder support32ABI = new StringBuilder();
   for (String abis : easyDeviceInfo.getSupported32bitABIS()) {
       support32ABI.append(abis).append("\n");
   }

String supported32ABI=support32ABI.toString();
```

+ To get Supported 64 Bit ABIS
```java
StringBuilder support64ABI = new StringBuilder();
   for (String abis : easyDeviceInfo.getSupported64bitABIS()) {
       support64ABI.append(abis).append("\n");
   }

String supported64ABI=support64ABI.toString();
```


+ To get Advertiser's ID
```java
//Get Android Advertiser ID
easyDeviceInfo.getAndroidAdId(MainActivity.this, new EasyDeviceInfo.AdIdentifierCallback() {
    @Override
    public void onSuccess(String adIdentifier, boolean adDonotTrack) {
        // Do something with the advertiser's ID
    }
});
```

+ To get device ringer mode
  ```java
  int device_ringer_mode = easyDeviceInfo.getDeviceRingerMode();
  ```
  
  Then match it against the constants provided
  
  ```java
  switch (device_ringer_mode) {
    case EasyDeviceInfo.RINGER_MODE_NORMAL:
      System.out.println("normal");
      break;
    case EasyDeviceInfo.RINGER_MODE_VIBRATE:
      System.out.println("vibrate");
      break;
    case EasyDeviceInfo.RINGER_MODE_SILENT:
      System.out.println("silent");
      break;
  }
  ```
  
  where constants available are 
    + `EasyDeviceInfo.RINGER_MODE_NORMAL`
    + `EasyDeviceInfo.RINGER_MODE_VIBRATE`
    + `EasyDeviceInfo.RINGER_MODE_SILENT`
    
+ To get device type
  ```java
  int device_type =easyDeviceInfo.getDeviceType(activity)
  ```
  
  Then match it against the constants provided
  
  ```java
  switch (device_type) {
     case EasyDeviceInfo.DEVICE_TYPE_WATCH:
       deviceDataMap.put("Device type", "watch");
       break;
     case EasyDeviceInfo.DEVICE_TYPE_PHONE:
       deviceDataMap.put("Device type", "phone");
       break;
     case EasyDeviceInfo.DEVICE_TYPE_PHABLET:
       deviceDataMap.put("Device type", "phablet");
       break;
     case EasyDeviceInfo.DEVICE_TYPE_TABLET:
       deviceDataMap.put("Device type", "tablet");
       break;
     case EasyDeviceInfo.DEVICE_TYPE_TV:
       deviceDataMap.put("Device type", "tv");
       break;
   }
  ```
  
  where constants available are 
    + `EasyDeviceInfo.DEVICE_TYPE_WATCH`
    + `EasyDeviceInfo.DEVICE_TYPE_PHONE`
    + `EasyDeviceInfo.DEVICE_TYPE_PHABLET`
    + `EasyDeviceInfo.DEVICE_TYPE_TABLET`
    + `EasyDeviceInfo.DEVICE_TYPE_TV`

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
