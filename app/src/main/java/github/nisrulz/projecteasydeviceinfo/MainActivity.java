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

import android.Manifest;
import android.Manifest.permission;
import android.R.layout;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.hardware.Sensor;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import github.nisrulz.easydeviceinfo.ads.EasyAdsMod;
import github.nisrulz.easydeviceinfo.ads.EasyAdsMod.AdIdentifierCallback;
import github.nisrulz.easydeviceinfo.base.BatteryHealth;
import github.nisrulz.easydeviceinfo.base.ChargingVia;
import github.nisrulz.easydeviceinfo.base.DeviceType;
import github.nisrulz.easydeviceinfo.base.EasyAppMod;
import github.nisrulz.easydeviceinfo.base.EasyBatteryMod;
import github.nisrulz.easydeviceinfo.base.EasyBluetoothMod;
import github.nisrulz.easydeviceinfo.base.EasyConfigMod;
import github.nisrulz.easydeviceinfo.base.EasyCpuMod;
import github.nisrulz.easydeviceinfo.base.EasyDeviceMod;
import github.nisrulz.easydeviceinfo.base.EasyDisplayMod;
import github.nisrulz.easydeviceinfo.base.EasyFingerprintMod;
import github.nisrulz.easydeviceinfo.base.EasyIdMod;
import github.nisrulz.easydeviceinfo.base.EasyLocationMod;
import github.nisrulz.easydeviceinfo.base.EasyMemoryMod;
import github.nisrulz.easydeviceinfo.base.EasyNetworkMod;
import github.nisrulz.easydeviceinfo.base.EasyNfcMod;
import github.nisrulz.easydeviceinfo.base.EasySensorMod;
import github.nisrulz.easydeviceinfo.base.EasySimMod;
import github.nisrulz.easydeviceinfo.base.NetworkType;
import github.nisrulz.easydeviceinfo.base.OrientationType;
import github.nisrulz.easydeviceinfo.base.PhoneType;
import github.nisrulz.easydeviceinfo.base.RingerMode;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;
import github.nisrulz.projecteasydeviceinfo.R.id;
import github.nisrulz.projecteasydeviceinfo.R.string;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter = null;

    @SuppressLint("MissingPermission")
    @TargetApi(VERSION_CODES.LOLLIPOP_MR1)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        //Data Array List of Info Object
        final ArrayList<String> data = new ArrayList<>();

        //Add Data
        final ArrayMap<String, String> deviceDataMap = new ArrayMap<>();

        // Setup the value to be returned when result is either not found or invalid/null
        EasyDeviceInfo.setNotFoundVal("na");
        // Enable Debugging when in Debug build
        if (BuildConfig.DEBUG) {
            EasyDeviceInfo.debug();
        }

        // Library Info
        data.add(EasyDeviceInfo.getLibraryVersion());

        // ID Mod
        final EasyIdMod easyIdMod = new EasyIdMod(this);

        final String[] emailIds = easyIdMod.getAccounts();
        final StringBuilder emailString = new StringBuilder();
        if ((emailIds != null) && (emailIds.length > 0)) {
            for (final String e : emailIds) {
                emailString.append(e).append('\n');
            }
        } else {
            emailString.append('-');
        }

        final EasyAdsMod easyAdsMod = new EasyAdsMod(this);
        easyAdsMod.getAndroidAdId(new AdIdentifierCallback() {
            @Override
            public void onSuccess(final String adIdentifier, final boolean adDonotTrack) {
                // Add Data
                data.add("Android Advertiser ID :" + adIdentifier);
                data.add("Ad Do not Track :" + adDonotTrack);
                MainActivity.this.adapter.notifyDataSetChanged();
            }
        });

        // Config Mod
        final EasyConfigMod easyConfigMod = new EasyConfigMod(this);
        deviceDataMap.put("Time (ms)", String.valueOf(easyConfigMod.getTime()));
        deviceDataMap.put("Formatted Time (24Hrs)", easyConfigMod.getFormattedTime());
        deviceDataMap.put("UpTime (ms)", String.valueOf(easyConfigMod.getUpTime()));
        deviceDataMap.put("Formatted Up Time (24Hrs)", easyConfigMod.getFormattedUpTime());
        deviceDataMap.put("Date", String.valueOf(easyConfigMod.getCurrentDate()));
        deviceDataMap.put("Formatted Date", easyConfigMod.getFormattedDate());
        deviceDataMap.put("SD Card available", String.valueOf(easyConfigMod.hasSdCard()));
        deviceDataMap.put("Running on emulator", String.valueOf(easyConfigMod.isRunningOnEmulator()));

        @RingerMode final int ringermode = easyConfigMod.getDeviceRingerMode();
        switch (ringermode) {
            case RingerMode.NORMAL:
                deviceDataMap.put(this.getString(string.ringer_mode), "normal");
                break;
            case RingerMode.VIBRATE:
                deviceDataMap.put(this.getString(string.ringer_mode), "vibrate");
                break;
            case RingerMode.SILENT:
            default:
                deviceDataMap.put(this.getString(string.ringer_mode), "silent");
                break;
        }

        // Fingerprint Mod
        final EasyFingerprintMod easyFingerprintMod = new EasyFingerprintMod(this);
        deviceDataMap.put("Is Fingerprint Sensor present?",
                String.valueOf(easyFingerprintMod.isFingerprintSensorPresent()));
        deviceDataMap.put("Are fingerprints enrolled",
                String.valueOf(easyFingerprintMod.areFingerprintsEnrolled()));

        // Sensor Mod
        final EasySensorMod easySensorMod = new EasySensorMod(this);
        final List<Sensor> list = easySensorMod.getAllSensors();
        for (final Sensor s : list) {
            if (s != null) {
                final String stringBuilder = "\nVendor : "
                        + s.getVendor()
                        + '\n'
                        + "Version : "
                        + s.getVersion()
                        + '\n'
                        + "Power : "
                        + s.getPower()
                        + '\n'
                        + "Resolution : "
                        + s.getResolution()
                        + '\n'
                        + "Max Range : "
                        + s.getMaximumRange();
                deviceDataMap.put("Sensor Name - " + s.getName(), stringBuilder);
            } else {
                deviceDataMap.put("Sensor", "N/A");
            }
        }

        // SIM Mod
        final EasySimMod easySimMod = new EasySimMod(this);
        deviceDataMap.put("IMSI", easySimMod.getIMSI());
        deviceDataMap.put("SIM Serial Number", easySimMod.getSIMSerial());
        deviceDataMap.put("Country", easySimMod.getCountry());
        deviceDataMap.put("Carrier", easySimMod.getCarrier());
        deviceDataMap.put("SIM Network Locked", String.valueOf(easySimMod.isSimNetworkLocked()));
        deviceDataMap.put("Is Multi SIM", String.valueOf(easySimMod.isMultiSim()));
        deviceDataMap.put("Number of active SIM", String.valueOf(easySimMod.getNumberOfActiveSim()));

        if (easySimMod.isMultiSim()) {
            final List<SubscriptionInfo> activeMultiSimInfo = easySimMod.getActiveMultiSimInfo();
            if (activeMultiSimInfo != null) {
                final StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < activeMultiSimInfo.size(); i++) {
                    stringBuilder.append("\nSIM ")
                            .append(i)
                            .append(" Info")
                            .append("\nPhone Number :")
                            .append(activeMultiSimInfo.get(i).getNumber())
                            .append('\n')
                            .append("Carrier Name :")
                            .append(activeMultiSimInfo.get(i).getCarrierName())
                            .append('\n')
                            .append("Country :")
                            .append(activeMultiSimInfo.get(i).getCountryIso())
                            .append('\n')
                            .append("Roaming :")
                            .append(activeMultiSimInfo.get(i).getDataRoaming()
                                    == SubscriptionManager.DATA_ROAMING_ENABLE)
                            .append('\n')
                            .append("Display Name :")
                            .append(activeMultiSimInfo.get(i).getDisplayName())
                            .append('\n')
                            .append("Sim Slot  :")
                            .append(activeMultiSimInfo.get(i).getSimSlotIndex())
                            .append('\n');
                }
                deviceDataMap.put("Multi SIM Info", stringBuilder.toString());
            }
        }

        // Device Mod
        final EasyDeviceMod easyDeviceMod = new EasyDeviceMod(this);
        deviceDataMap.put("Language", easyDeviceMod.getLanguage());
        deviceDataMap.put("Android ID", easyIdMod.getAndroidID());
        deviceDataMap.put("IMEI", easyDeviceMod.getIMEI());
        deviceDataMap.put("User-Agent", easyIdMod.getUA());
        deviceDataMap.put("GSF ID", easyIdMod.getGSFID());
        deviceDataMap.put("Pseudo ID", easyIdMod.getPseudoUniqueID());
        deviceDataMap.put("Device Serial", easyDeviceMod.getSerial());
        deviceDataMap.put("Manufacturer", easyDeviceMod.getManufacturer());
        deviceDataMap.put("Model", easyDeviceMod.getModel());
        deviceDataMap.put("OS Codename", easyDeviceMod.getOSCodename());
        deviceDataMap.put("OS Version", easyDeviceMod.getOSVersion());
        deviceDataMap.put("Display Version", easyDeviceMod.getDisplayVersion());
        deviceDataMap.put("Phone Number", easyDeviceMod.getPhoneNo());
        deviceDataMap.put("Radio Version", easyDeviceMod.getRadioVer());
        deviceDataMap.put("Product ", easyDeviceMod.getProduct());
        deviceDataMap.put("Device", easyDeviceMod.getDevice());
        deviceDataMap.put("Board", easyDeviceMod.getBoard());
        deviceDataMap.put("Hardware", easyDeviceMod.getHardware());
        deviceDataMap.put("BootLoader", easyDeviceMod.getBootloader());
        deviceDataMap.put("Device Rooted", String.valueOf(easyDeviceMod.isDeviceRooted()));
        deviceDataMap.put("Fingerprint", easyDeviceMod.getFingerprint());
        deviceDataMap.put("Build Brand", easyDeviceMod.getBuildBrand());
        deviceDataMap.put("Build Host", easyDeviceMod.getBuildHost());
        deviceDataMap.put("Build Tag", easyDeviceMod.getBuildTags());
        deviceDataMap.put("Build Time", String.valueOf(easyDeviceMod.getBuildTime()));
        deviceDataMap.put("Build User", easyDeviceMod.getBuildUser());
        deviceDataMap.put("Build Version Release", easyDeviceMod.getBuildVersionRelease());
        deviceDataMap.put("Screen Display ID", easyDeviceMod.getScreenDisplayID());
        deviceDataMap.put("Build Version Codename", easyDeviceMod.getBuildVersionCodename());
        deviceDataMap.put("Build Version Increment", easyDeviceMod.getBuildVersionIncremental());
        deviceDataMap.put("Build Version SDK", String.valueOf(easyDeviceMod.getBuildVersionSDK()));
        deviceDataMap.put("Build ID", easyDeviceMod.getBuildID());

        @DeviceType final int deviceType = easyDeviceMod.getDeviceType(this);
        switch (deviceType) {
            case DeviceType.WATCH:
                deviceDataMap.put(this.getString(string.device_type), "watch");
                break;
            case DeviceType.PHONE:
                deviceDataMap.put(this.getString(string.device_type), "phone");
                break;
            case DeviceType.PHABLET:
                deviceDataMap.put(this.getString(string.device_type), "phablet");
                break;
            case DeviceType.TABLET:
                deviceDataMap.put(this.getString(string.device_type), "tablet");
                break;
            case DeviceType.TV:
                deviceDataMap.put(this.getString(string.device_type), "tv");
                break;
            default:
                //do nothing
                break;
        }

        @PhoneType final int phoneType = easyDeviceMod.getPhoneType();
        switch (phoneType) {

            case PhoneType.CDMA:
                deviceDataMap.put(this.getString(string.phone_type), "CDMA");
                break;
            case PhoneType.GSM:
                deviceDataMap.put(this.getString(string.phone_type), "GSM");
                break;
            case PhoneType.NONE:
                deviceDataMap.put(this.getString(string.phone_type), "None");
                break;
            default:
                deviceDataMap.put(this.getString(string.phone_type), "Unknown");
                break;
        }

        @OrientationType final int orientationType = easyDeviceMod.getOrientation(this);
        switch (orientationType) {
            case OrientationType.LANDSCAPE:
                deviceDataMap.put(this.getString(string.orientation), "Landscape");
                break;
            case OrientationType.PORTRAIT:
                deviceDataMap.put(this.getString(string.orientation), "Portrait");
                break;
            case OrientationType.UNKNOWN:
            default:
                deviceDataMap.put(this.getString(string.orientation), "Unknown");
                break;
        }

        // App Mod
        final EasyAppMod easyAppMod = new EasyAppMod(this);
        deviceDataMap.put("Installer Store", easyAppMod.getStore());
        deviceDataMap.put("App Name", easyAppMod.getAppName());
        deviceDataMap.put("Package Name", easyAppMod.getPackageName());
        deviceDataMap.put("Activity Name", easyAppMod.getActivityName());
        deviceDataMap.put("App version", easyAppMod.getAppVersion());
        deviceDataMap.put("App versioncode", easyAppMod.getAppVersionCode());
        deviceDataMap.put("Does app have Camera permission?",
                String.valueOf(easyAppMod.isPermissionGranted(permission.CAMERA)));

        //Network Mod
        final EasyNetworkMod easyNetworkMod = new EasyNetworkMod(this);
        deviceDataMap.put("WIFI MAC Address", easyNetworkMod.getWifiMAC());
        deviceDataMap.put("WIFI LinkSpeed", easyNetworkMod.getWifiLinkSpeed());
        deviceDataMap.put("WIFI SSID", easyNetworkMod.getWifiSSID());
        deviceDataMap.put("WIFI BSSID", easyNetworkMod.getWifiBSSID());
        deviceDataMap.put("IPv4 Address", easyNetworkMod.getIPv4Address());
        deviceDataMap.put("IPv6 Address", easyNetworkMod.getIPv6Address());
        deviceDataMap.put("Network Available", String.valueOf(easyNetworkMod.isNetworkAvailable()));
        deviceDataMap.put("Wi-Fi enabled", String.valueOf(easyNetworkMod.isWifiEnabled()));

        @NetworkType final int networkType = easyNetworkMod.getNetworkType();

        switch (networkType) {
            case NetworkType.CELLULAR_UNKNOWN:
                deviceDataMap.put(this.getString(string.network_type), "Cellular Unknown");
                break;
            case NetworkType.CELLULAR_UNIDENTIFIED_GEN:
                deviceDataMap.put(this.getString(string.network_type), "Cellular Unidentified Generation");
                break;
            case NetworkType.CELLULAR_2G:
                deviceDataMap.put(this.getString(string.network_type), "Cellular 2G");
                break;
            case NetworkType.CELLULAR_3G:
                deviceDataMap.put(this.getString(string.network_type), "Cellular 3G");
                break;
            case NetworkType.CELLULAR_4G:
                deviceDataMap.put(this.getString(string.network_type), "Cellular 4G");
                break;

            case NetworkType.WIFI_WIFIMAX:
                deviceDataMap.put(this.getString(string.network_type), "Wifi/WifiMax");
                break;
            case NetworkType.UNKNOWN:
            default:
                deviceDataMap.put(this.getString(string.network_type), "Unknown");
                break;
        }

        // Battery Mod
        final EasyBatteryMod easyBatteryMod = new EasyBatteryMod(this);
        deviceDataMap.put("Battery Percentage", String.valueOf(easyBatteryMod.getBatteryPercentage()) + '%');
        deviceDataMap.put("Is device charging", String.valueOf(easyBatteryMod.isDeviceCharging()));
        deviceDataMap.put("Battery present", String.valueOf(easyBatteryMod.isBatteryPresent()));
        deviceDataMap.put("Battery technology", String.valueOf(easyBatteryMod.getBatteryTechnology()));
        deviceDataMap.put("Battery temperature",
                easyBatteryMod.getBatteryTemperature() + " deg C");
        deviceDataMap.put("Battery voltage",
                easyBatteryMod.getBatteryVoltage() + " mV");

        @BatteryHealth final int batteryHealth = easyBatteryMod.getBatteryHealth();
        switch (batteryHealth) {
            case BatteryHealth.GOOD:
                deviceDataMap.put("Battery health", "Good");
                break;
            case BatteryHealth.HAVING_ISSUES:
            default:
                deviceDataMap.put("Battery health", "Having issues");
                break;
        }

        @ChargingVia final int isChargingVia = easyBatteryMod.getChargingSource();
        switch (isChargingVia) {
            case ChargingVia.AC:
                deviceDataMap.put(this.getString(string.device_charging_via), "AC");
                break;
            case ChargingVia.USB:
                deviceDataMap.put(this.getString(string.device_charging_via), "USB");
                break;
            case ChargingVia.WIRELESS:
                deviceDataMap.put(this.getString(string.device_charging_via), "Wireless");
                break;
            case ChargingVia.UNKNOWN_SOURCE:
            default:
                deviceDataMap.put(this.getString(R.string.device_charging_via), "Unknown Source");
                break;
        }

        //Bluetooth Mod
        final EasyBluetoothMod easyBluetoothMod = new EasyBluetoothMod(this);
        deviceDataMap.put("BT MAC Address", easyBluetoothMod.getBluetoothMAC());

        // Display Mod
        final EasyDisplayMod easyDisplayMod = new EasyDisplayMod(this);
        deviceDataMap.put("Display Resolution", easyDisplayMod.getResolution());
        deviceDataMap.put("Screen Density", easyDisplayMod.getDensity());
        deviceDataMap.put("Screen Size", String.valueOf(easyDisplayMod.getPhysicalSize()));
        deviceDataMap.put("Screen Refresh rate",
                easyDisplayMod.getRefreshRate() + " Hz");

        deviceDataMap.put("Email ID", emailString.toString());

        // Location Mod
        final EasyLocationMod easyLocationMod = new EasyLocationMod(this);
        final double[] l = easyLocationMod.getLatLong();
        final String lat = String.valueOf(l[0]);
        final String lon = String.valueOf(l[1]);
        deviceDataMap.put("Latitude", lat);
        deviceDataMap.put("Longitude", lon);

        // Memory Mod
        final EasyMemoryMod easyMemoryMod = new EasyMemoryMod(this);
        deviceDataMap.put("Total RAM",
                easyMemoryMod.convertToGb(easyMemoryMod.getTotalRAM()) + " Gb");
        deviceDataMap.put("Available Internal Memory",
                easyMemoryMod.convertToGb(easyMemoryMod.getAvailableInternalMemorySize())
                        + " Gb");
        deviceDataMap.put("Available External Memory",
                easyMemoryMod.convertToGb(easyMemoryMod.getAvailableExternalMemorySize())
                        + " Gb");
        deviceDataMap.put("Total Internal Memory",
                easyMemoryMod.convertToGb(easyMemoryMod.getTotalInternalMemorySize())
                        + " Gb");
        deviceDataMap.put("Total External memory",
                easyMemoryMod.convertToGb(easyMemoryMod.getTotalExternalMemorySize())
                        + " Gb");

        // CPU Mod
        final EasyCpuMod easyCpuMod = new EasyCpuMod();
        deviceDataMap.put("Supported ABIS", easyCpuMod.getStringSupportedABIS());
        deviceDataMap.put("Supported 32 bit ABIS", easyCpuMod.getStringSupported32bitABIS());
        deviceDataMap.put("Supported 64 bit ABIS", easyCpuMod.getStringSupported64bitABIS());

        // NFC Mod
        final EasyNfcMod easyNfcMod = new EasyNfcMod(this);
        deviceDataMap.put("is NFC present", String.valueOf(easyNfcMod.isNfcPresent()));
        deviceDataMap.put("is NFC enabled", String.valueOf(easyNfcMod.isNfcEnabled()));

        for (final String key : deviceDataMap.keySet()) {
            data.add(key + " : " + deviceDataMap.get(key));
        }

        final ListView lv = this.findViewById(R.id.listview);
        this.adapter = new ArrayAdapter<>(this, layout.simple_list_item_1, data);
        lv.setAdapter(this.adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
