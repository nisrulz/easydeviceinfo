/*
 * Copyright (C) 2016 Nishant Srivastava
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

package github.nisrulz.projecteasydeviceinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import github.nisrulz.easydeviceinfo.EasyDIAppMod;
import github.nisrulz.easydeviceinfo.EasyDIBatteryMod;
import github.nisrulz.easydeviceinfo.EasyDIBluetoothMod;
import github.nisrulz.easydeviceinfo.EasyDIConfigMod;
import github.nisrulz.easydeviceinfo.EasyDICpuMod;
import github.nisrulz.easydeviceinfo.EasyDIDeviceMod;
import github.nisrulz.easydeviceinfo.EasyDIDisplayMod;
import github.nisrulz.easydeviceinfo.EasyDIIdMod;
import github.nisrulz.easydeviceinfo.EasyDILocationMod;
import github.nisrulz.easydeviceinfo.EasyDINetworkMod;
import github.nisrulz.easydeviceinfo.EasyDISimMod;
import github.nisrulz.easydeviceinfo.EasyDeviceInfo;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  private ArrayAdapter<String> adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Data Array List of Info Object
    final ArrayList<String> data = new ArrayList<>();

    //Add Data
    HashMap<String, String> deviceDataMap = new HashMap<>();

    // Library Info
    EasyDeviceInfo easyDeviceInfo = new EasyDeviceInfo();
    data.add(easyDeviceInfo.getLibraryVersion());

    // ID Mod
    EasyDIIdMod easyDIIdMod = new EasyDIIdMod(this);

    String[] emailIds = easyDIIdMod.getAccounts();
    StringBuilder emailString = new StringBuilder();
    if (emailIds != null && emailIds.length > 0) {
      for (String e : emailIds) {
        emailString.append(e).append("\n");
      }
    } else {
      emailString.append("-");
    }

    easyDIIdMod.getAndroidAdId(new EasyDIIdMod.AdIdentifierCallback() {
      @Override public void onSuccess(String adIdentifier, boolean adDonotTrack) {
        // Add Data
        data.add("Android Advertiser ID :" + adIdentifier);
        data.add("Ad Do not Track :" + String.valueOf(adDonotTrack));
        adapter.notifyDataSetChanged();
      }
    });

    // Config Mod
    EasyDIConfigMod easyDIConfigMod = new EasyDIConfigMod(this);
    deviceDataMap.put("Time (ms)", String.valueOf(easyDIConfigMod.getTime()));
    deviceDataMap.put("Formatted Time (24Hrs)", easyDIConfigMod.getFormattedTime());
    deviceDataMap.put("Running on emulator", String.valueOf(easyDIConfigMod.isRunningOnEmulator()));
    switch (easyDIConfigMod.getDeviceRingerMode()) {
      case EasyDIConfigMod.RINGER_MODE_NORMAL:
        deviceDataMap.put("Ringer mode", "normal");
        break;
      case EasyDIConfigMod.RINGER_MODE_VIBRATE:
        deviceDataMap.put("Ringer mode", "vibrate");
        break;
      case EasyDIConfigMod.RINGER_MODE_SILENT:
        deviceDataMap.put("Ringer mode", "silent");
        break;
      default:
        //do nothing
        break;
    }

    // Device Mod
    EasyDIDeviceMod easyDIDeviceMod = new EasyDIDeviceMod(this);
    deviceDataMap.put("Language", easyDIDeviceMod.getLanguage());
    deviceDataMap.put("Android ID", easyDIIdMod.getAndroidID());
    deviceDataMap.put("IMEI", easyDIDeviceMod.getIMEI());
    deviceDataMap.put("User-Agent", easyDIIdMod.getUA());
    deviceDataMap.put("GSF ID", easyDIIdMod.getGSFID());
    deviceDataMap.put("Pseudo ID", easyDIIdMod.getPseudoUniqueID());

    // SIM Mod
    EasyDISimMod easyDISimMod = new EasyDISimMod(this);
    deviceDataMap.put("IMSI", easyDISimMod.getIMSI());
    deviceDataMap.put("SIM Serial Number", easyDISimMod.getSIMSerial());
    deviceDataMap.put("Country", easyDISimMod.getCountry());
    deviceDataMap.put("Carrier", easyDISimMod.getCarrier());

    // Device Mod
    deviceDataMap.put("Device Serial", easyDIDeviceMod.getSerial());
    deviceDataMap.put("Manufacturer", easyDIDeviceMod.getManufacturer());
    deviceDataMap.put("Model", easyDIDeviceMod.getModel());
    deviceDataMap.put("OS Codename", easyDIDeviceMod.getOSCodename());
    deviceDataMap.put("OS Version", easyDIDeviceMod.getOSVersion());
    deviceDataMap.put("Display Version", easyDIDeviceMod.getDisplayVersion());
    deviceDataMap.put("Phone Number", easyDIDeviceMod.getPhoneNo());
    deviceDataMap.put("Radio Version", easyDIDeviceMod.getRadioVer());
    deviceDataMap.put("Product ", easyDIDeviceMod.getProduct());
    deviceDataMap.put("Device", easyDIDeviceMod.getDevice());
    deviceDataMap.put("Board", easyDIDeviceMod.getBoard());
    deviceDataMap.put("Hardware", easyDIDeviceMod.getHardware());
    deviceDataMap.put("BootLoader", easyDIDeviceMod.getBootloader());
    deviceDataMap.put("Device Rooted", String.valueOf(easyDIDeviceMod.isDeviceRooted()));
    deviceDataMap.put("Fingerprint", easyDIDeviceMod.getFingerprint());
    deviceDataMap.put("Build Brand", easyDIDeviceMod.getBuildBrand());
    deviceDataMap.put("Build Host", easyDIDeviceMod.getBuildHost());
    deviceDataMap.put("Build Tag", easyDIDeviceMod.getBuildTags());
    deviceDataMap.put("Build Time", String.valueOf(easyDIDeviceMod.getBuildTime()));
    deviceDataMap.put("Build User", easyDIDeviceMod.getBuildUser());
    deviceDataMap.put("Build Version Release", easyDIDeviceMod.getBuildVersionRelease());
    deviceDataMap.put("Screen Display ID", easyDIDeviceMod.getScreenDisplayID());
    deviceDataMap.put("Build Version Codename", easyDIDeviceMod.getBuildVersionCodename());
    deviceDataMap.put("Build Version Increment", easyDIDeviceMod.getBuildVersionIncremental());
    deviceDataMap.put("Build Version SDK", String.valueOf(easyDIDeviceMod.getBuildVersionSDK()));
    deviceDataMap.put("Build ID", easyDIDeviceMod.getBuildID());
    switch (easyDIDeviceMod.getDeviceType(this)) {
      case EasyDIDeviceMod.DEVICE_TYPE_WATCH:
        deviceDataMap.put("Device type", "watch");
        break;
      case EasyDIDeviceMod.DEVICE_TYPE_PHONE:
        deviceDataMap.put("Device type", "phone");
        break;
      case EasyDIDeviceMod.DEVICE_TYPE_PHABLET:
        deviceDataMap.put("Device type", "phablet");
        break;
      case EasyDIDeviceMod.DEVICE_TYPE_TABLET:
        deviceDataMap.put("Device type", "tablet");
        break;
      case EasyDIDeviceMod.DEVICE_TYPE_TV:
        deviceDataMap.put("Device type", "tv");
        break;
      default:
        //do nothing
        break;
    }

    // App Mod
    EasyDIAppMod easyDIAppMod = new EasyDIAppMod(this);
    deviceDataMap.put("Installer Store", easyDIAppMod.getStore());

    //Network Mod
    EasyDINetworkMod easyDINetworkMod = new EasyDINetworkMod(this);
    deviceDataMap.put("WIFI MAC Address", easyDINetworkMod.getWifiMAC());
    deviceDataMap.put("IPv4 Address", easyDINetworkMod.getIPv4Address());
    deviceDataMap.put("IPv6 Address", easyDINetworkMod.getIPv6Address());
    deviceDataMap.put("Network Available", String.valueOf(easyDINetworkMod.isNetworkAvailable()));
    deviceDataMap.put("Wi-Fi enabled", String.valueOf(easyDINetworkMod.isWifiEnabled()));
    switch (easyDINetworkMod.getNetworkType()) {
      case EasyDINetworkMod.CELLULAR_UNKNOWN:
        deviceDataMap.put("Network Type", "Unknown");
        break;
      case EasyDINetworkMod.CELLULAR_UNIDENTIFIED_GEN:
        deviceDataMap.put("Network Type", "Cellular Unidentified Generation");
        break;
      case EasyDINetworkMod.CELLULAR_2G:
        deviceDataMap.put("Network Type", "Cellular 2G");
        break;
      case EasyDINetworkMod.CELLULAR_3G:
        deviceDataMap.put("Network Type", "Cellular 3G");
        break;
      case EasyDINetworkMod.CELLULAR_4G:
        deviceDataMap.put("Network Type", "Cellular 4G");
        break;
      default:
        // Do nothing
        break;
    }

    // Battery Mod
    EasyDIBatteryMod easyDIBatteryMod = new EasyDIBatteryMod(this);
    deviceDataMap.put("Battery Percentage",
        String.valueOf(easyDIBatteryMod.getBatteryPercentage()) + "%");
    deviceDataMap.put("Is device charging", String.valueOf(easyDIBatteryMod.isDeviceCharging()));
    deviceDataMap.put("Battery present", String.valueOf(easyDIBatteryMod.isBatteryPresent()));
    deviceDataMap.put("Battery technology",
        String.valueOf(easyDIBatteryMod.getBatteryTechnology()));
    deviceDataMap.put("Battery temperature",
        String.valueOf(easyDIBatteryMod.getBatteryTemprature()) + " deg C");
    deviceDataMap.put("Battery voltage", String.valueOf(easyDIBatteryMod.getBatteryVoltage())+" mV");
    switch (easyDIBatteryMod.getBatteryHealth()) {
      case EasyDIBatteryMod.HEALTH_GOOD:
        deviceDataMap.put("Battery health", "Good");
        break;
      case EasyDIBatteryMod.HEALTH_HAVING_ISSUES:
        deviceDataMap.put("Battery health", "Having isues");
        break;
      default:
        deviceDataMap.put("Battery health", "Having isues");
        break;
    }

    switch (easyDIBatteryMod.getChargingSource()) {
      case EasyDIBatteryMod.CHARGING_VIA_AC:
        deviceDataMap.put("Device charging via ", "AC");
        break;
      case EasyDIBatteryMod.CHARGING_VIA_USB:
        deviceDataMap.put("Device charging via ", "USB");
        break;
      case EasyDIBatteryMod.CHARGING_VIA_WIRELESS:
        deviceDataMap.put("Device charging via ", "Wireless");
        break;
      case EasyDIBatteryMod.CHARGING_VIA_UNKNOWN_SOURCE:
        deviceDataMap.put("Device charging via ", "Unknown Source");
        break;
      default:
        deviceDataMap.put("Device charging via ", "Unknown Source");
        break;
    }

    //Bluetooth Mod
    EasyDIBluetoothMod easyDIBluetoothMod = new EasyDIBluetoothMod(this);
    deviceDataMap.put("BT MAC Address", easyDIBluetoothMod.getBluetoothMAC());

    // Display Mod
    EasyDIDisplayMod easyDIDisplayMod = new EasyDIDisplayMod(this);
    deviceDataMap.put("Display Resolution", easyDIDisplayMod.getResolution());
    deviceDataMap.put("Screen Density", easyDIDisplayMod.getDensity());

    deviceDataMap.put("Email ID", emailString.toString());

    // Location Mod
    EasyDILocationMod easyDILocationMod = new EasyDILocationMod(this);
    double[] l = easyDILocationMod.getLatLong();
    String lat = String.valueOf(l[0]);
    String lon = String.valueOf(l[1]);
    deviceDataMap.put("Latitude", lat);
    deviceDataMap.put("Longitude", lon);

    // CPU Mod
    EasyDICpuMod easyDICpuMod = new EasyDICpuMod();
    String[] supportedABIS = easyDICpuMod.getSupportedABIS();

    StringBuilder supportedABI = new StringBuilder();
    if (supportedABIS.length > 0) {
      for (String abis : supportedABIS) {
        supportedABI.append(abis).append("\n");
      }
    } else {
      supportedABI.append("-");
    }

    String[] supported32ABIS = easyDICpuMod.getSupported32bitABIS();
    StringBuilder supported32ABI = new StringBuilder();
    if (supported32ABIS.length > 0) {
      for (String abis : supported32ABIS) {
        supported32ABI.append(abis).append("\n");
      }
    } else {
      supported32ABI.append("-");
    }

    String[] supported64ABIS = easyDICpuMod.getSupported64bitABIS();

    StringBuilder supported64ABI = new StringBuilder();
    if (supported32ABIS.length > 0) {
      for (String abis : supported64ABIS) {
        supported64ABI.append(abis).append("\n");
      }
    } else {
      supported64ABI.append("-");
    }

    deviceDataMap.put("Supported ABIS", easyDICpuMod.getStringSupportedABIS());
    deviceDataMap.put("Supported 32 bit ABIS", easyDICpuMod.getStringSupported32bitABIS());
    deviceDataMap.put("Supported 64 bit ABIS", easyDICpuMod.getStringSupported64bitABIS());

    for (String key : deviceDataMap.keySet()) {
      data.add(key + " : " + deviceDataMap.get(key));
    }

    ListView lv = (ListView) findViewById(R.id.listview);
    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
    lv.setAdapter(adapter);
  }
}
