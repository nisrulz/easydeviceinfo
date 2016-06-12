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
import github.nisrulz.easydeviceinfo.EasyDeviceInfo;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

  private ArrayAdapter<String> adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    EasyDeviceInfo easyDeviceInfo = new EasyDeviceInfo(this);

    //Data Array List of Info Object
    final ArrayList<String> data = new ArrayList<>();

    //Get Lat-Long
    double[] l = easyDeviceInfo.getLatLong();

    String lat = String.valueOf(l[0]);
    String lon = String.valueOf(l[1]);

    String[] supportedABIS = easyDeviceInfo.getSupportedABIS();

    StringBuilder supportedABI = new StringBuilder();
    if (supportedABIS.length > 0) {
      for (String abis : supportedABIS) {
        supportedABI.append(abis).append("\n");
      }
    } else {
      supportedABI.append("-");
    }

    String[] emailIds = easyDeviceInfo.getAccounts();

    StringBuilder emailString = new StringBuilder();
    if (emailIds != null && emailIds.length > 0) {
      for (String e : emailIds) {
        emailString.append(e).append("\n");
      }
    } else {
      emailString.append("-");
    }

    String[] supported32ABIS = new String[0];
    supported32ABIS = easyDeviceInfo.getSupported32bitABIS();
    StringBuilder supported32ABI = new StringBuilder();
    if (supported32ABIS.length > 0) {
      for (String abis : supported32ABIS) {
        supported32ABI.append(abis).append("\n");
      }
    } else {
      supported32ABI.append("-");
    }

    String[] supported64ABIS = new String[0];
    supported64ABIS = easyDeviceInfo.getSupported64bitABIS();

    StringBuilder supported64ABI = new StringBuilder();
    if (supported32ABIS.length > 0) {
      for (String abis : supported64ABIS) {
        supported64ABI.append(abis).append("\n");
      }
    } else {
      supported64ABI.append("-");
    }

    //Get Android Advertiser ID
    easyDeviceInfo.getAndroidAdId(new EasyDeviceInfo.AdIdentifierCallback() {
      @Override public void onSuccess(String adIdentifier, boolean adDonotTrack) {
        // Add Data
        data.add("Android Advertiser ID :" + adIdentifier);
        data.add("Ad Do not Track :" + String.valueOf(adDonotTrack));
        adapter.notifyDataSetChanged();
      }
    });

    //Add Data
    HashMap<String, String> deviceDataMap = new HashMap<>();
    deviceDataMap.put("Time (ms)", String.valueOf(easyDeviceInfo.getTime()));
    deviceDataMap.put("Formatted Time (24Hrs)", easyDeviceInfo.getFormattedTime());

    deviceDataMap.put("Language", easyDeviceInfo.getLanguage());
    deviceDataMap.put("Android ID", easyDeviceInfo.getAndroidID());
    deviceDataMap.put("IMEI", easyDeviceInfo.getIMEI());
    deviceDataMap.put("User-Agent", easyDeviceInfo.getUA());
    deviceDataMap.put("IMSI", easyDeviceInfo.getIMSI());
    deviceDataMap.put("GSF ID", easyDeviceInfo.getGSFID());
    deviceDataMap.put("Pseudo ID", easyDeviceInfo.getPseudoUniqueID());
    deviceDataMap.put("Device Serial", easyDeviceInfo.getSerial());
    deviceDataMap.put("SIM Serial Number", easyDeviceInfo.getSIMSerial());
    deviceDataMap.put("Manufacturer", easyDeviceInfo.getManufacturer());
    deviceDataMap.put("Model", easyDeviceInfo.getModel());
    deviceDataMap.put("OS Codename", easyDeviceInfo.getOSCodename());
    deviceDataMap.put("OS Version", easyDeviceInfo.getOSVersion());
    deviceDataMap.put("Country", easyDeviceInfo.getCountry());
    deviceDataMap.put("WIFI MAC Address", easyDeviceInfo.getWifiMAC());
    deviceDataMap.put("BT MAC Address", easyDeviceInfo.getBluetoothMAC());
    deviceDataMap.put("Display Resolution", easyDeviceInfo.getResolution());
    deviceDataMap.put("Display Version", easyDeviceInfo.getDisplayVersion());
    deviceDataMap.put("Phone Number", easyDeviceInfo.getPhoneNo());
    deviceDataMap.put("Carrier", easyDeviceInfo.getCarrier());
    deviceDataMap.put("Radio Version", easyDeviceInfo.getRadioVer());
    deviceDataMap.put("Product ", easyDeviceInfo.getProduct());
    deviceDataMap.put("Device", easyDeviceInfo.getDevice());
    deviceDataMap.put("Board", easyDeviceInfo.getBoard());
    deviceDataMap.put("Hardware", easyDeviceInfo.getHardware());
    deviceDataMap.put("BootLoader", easyDeviceInfo.getBootloader());
    deviceDataMap.put("IP Address", easyDeviceInfo.getIPAddress(true));
    deviceDataMap.put("Network Type", easyDeviceInfo.getNetworkType());
    deviceDataMap.put("Device Rooted", String.valueOf(easyDeviceInfo.isDeviceRooted()));
    deviceDataMap.put("Email ID", emailString.toString());
    deviceDataMap.put("Latitude", lat);
    deviceDataMap.put("Longitude", lon);
    deviceDataMap.put("Fingerprint", easyDeviceInfo.getFingerprint());
    deviceDataMap.put("Screen Density", easyDeviceInfo.getDensity());
    deviceDataMap.put("Installer Store", easyDeviceInfo.getStore());
    deviceDataMap.put("Network Available", String.valueOf(easyDeviceInfo.isNetworkAvailable()));
    deviceDataMap.put("Running on emulator", String.valueOf(easyDeviceInfo.isRunningOnEmulator()));
    deviceDataMap.put("Build Brand", easyDeviceInfo.getBuildBrand());
    deviceDataMap.put("Build Host", easyDeviceInfo.getBuildHost());
    deviceDataMap.put("Build Tag", easyDeviceInfo.getBuildTags());
    deviceDataMap.put("Build Time", String.valueOf(easyDeviceInfo.getBuildTime()));
    deviceDataMap.put("Build User", easyDeviceInfo.getBuildUser());
    deviceDataMap.put("Build Version Release", easyDeviceInfo.getBuildVersionRelease());
    deviceDataMap.put("Battery Percentage",
        String.valueOf(easyDeviceInfo.getBatteryPercentage()) + "%");
    deviceDataMap.put("Is device charging", String.valueOf(easyDeviceInfo.isDeviceCharging()));
    deviceDataMap.put("Device charging via USB",
        String.valueOf(easyDeviceInfo.isDeviceChargingUSB()));
    deviceDataMap.put("Device charging via AC",
        String.valueOf(easyDeviceInfo.isDeviceChargingAC()));
    deviceDataMap.put("Wi-Fi enabled", String.valueOf(easyDeviceInfo.isWifiEnabled()));
    deviceDataMap.put("Screen Display ID", easyDeviceInfo.getScreenDisplayID());
    deviceDataMap.put("Build Version Codename", easyDeviceInfo.getBuildVersionCodename());
    deviceDataMap.put("Build Version Increment", easyDeviceInfo.getBuildVersionIncremental());
    deviceDataMap.put("Build Version SDK", String.valueOf(easyDeviceInfo.getBuildVersionSDK()));
    deviceDataMap.put("Build ID", easyDeviceInfo.getBuildID());
    deviceDataMap.put("Supported ABIS", easyDeviceInfo.getStringSupportedABIS());
    deviceDataMap.put("Supported 32 bit ABIS", easyDeviceInfo.getStringSupported32bitABIS());
    deviceDataMap.put("Supported 64 bit ABIS", easyDeviceInfo.getStringSupported64bitABIS());

    switch (easyDeviceInfo.getDeviceRingerMode()) {
      case EasyDeviceInfo.RINGER_MODE_NORMAL:
        deviceDataMap.put("Ringer mode", "normal");
        break;
      case EasyDeviceInfo.RINGER_MODE_VIBRATE:
        deviceDataMap.put("Ringer mode", "vibrate");
        break;
      case EasyDeviceInfo.RINGER_MODE_SILENT:
        deviceDataMap.put("Ringer mode", "silent");
        break;
      default:
        //do nothing
        break;
    }

    switch (easyDeviceInfo.getDeviceType(this)) {
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
      default:
        //do nothing
        break;
    }

    for (String key : deviceDataMap.keySet()) {
      data.add(0, key + " : " + deviceDataMap.get(key));
    }

    ListView lv = (ListView) findViewById(R.id.listview);
    adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data);
    lv.setAdapter(adapter);
  }
}
