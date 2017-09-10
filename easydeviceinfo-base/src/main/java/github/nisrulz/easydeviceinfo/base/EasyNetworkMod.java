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

package github.nisrulz.easydeviceinfo.base;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

/**
 * EasyNetwork Mod Class
 */
public class EasyNetworkMod {
  private static final String SOCKET_EXCEPTION = "Socket Exception";
  private final Context context;

  /**
   * Instantiates a new Easy  network mod.
   *
   * @param context
   *     the context
   */
  public EasyNetworkMod(final Context context) {
    this.context = context;
  }

  /**
   * Is wifi enabled.
   *
   * @return the boolean
   */
  public final boolean isWifiEnabled() {
    boolean wifiState = false;

    WifiManager wifiManager =
        (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
    if (wifiManager != null) {
      wifiState = wifiManager.isWifiEnabled();
    }
    return wifiState;
  }

  /**
   * Is network available boolean.
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
   * <uses-permission android:name="android.permission.INTERNET"/>
   *
   * @return the boolean
   */
  @RequiresPermission(allOf = {
      Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET
  })
  public final boolean isNetworkAvailable() {
    if (PermissionUtil.hasPermission(context, Manifest.permission.INTERNET)
        && PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_NETWORK_STATE)) {
      ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext()
          .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo netInfo = cm.getActiveNetworkInfo();
      return netInfo != null && netInfo.isConnected();
    }
    return false;
  }

  /**
   * Gets ip address v4.
   *
   * @return the ip address
   */
  public final String getIPv4Address() {
    String result = null;
    try {
      List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
      for (NetworkInterface intf : interfaces) {
        List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
        for (InetAddress addr : addrs) {
          if (!addr.isLoopbackAddress()) {
            String sAddr = addr.getHostAddress().toUpperCase(Locale.getDefault());
            boolean isIPv4 = addr instanceof Inet4Address;
            if (isIPv4) {
              result = sAddr;
            }
          }
        }
      }
    } catch (SocketException e) {
      if (EasyDeviceInfo.debuggable) {
        Log.e(EasyDeviceInfo.nameOfLib, SOCKET_EXCEPTION, e);
      }
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets ip address v6.
   *
   * @return the ip address
   */
  public final String getIPv6Address() {
    String result = null;
    try {
      List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
      for (NetworkInterface intf : interfaces) {
        List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
        for (InetAddress addr : addrs) {
          if (!addr.isLoopbackAddress()) {
            String sAddr = addr.getHostAddress().toUpperCase(Locale.getDefault());
            boolean isIPv4 = addr instanceof Inet4Address;
            if (!isIPv4) {
              int delim = sAddr.indexOf('%'); // drop ip6 port suffix
              result = delim < 0 ? sAddr : sAddr.substring(0, delim);
            }
          }
        }
      }
    } catch (SocketException e) {
      if (EasyDeviceInfo.debuggable) {
        Log.e(EasyDeviceInfo.nameOfLib, SOCKET_EXCEPTION, e);
      }
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets network type.
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
   * <uses-permission android:name="android.permission.INTERNET"/>
   *
   * @return the network type
   */
  @RequiresPermission(allOf = {
      Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET
  })
  @NetworkType
  public final int getNetworkType() {
    int result = NetworkType.UNKNOWN;
    if (PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_NETWORK_STATE)) {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
      if (activeNetwork == null) {
        result = NetworkType.UNKNOWN;
      }
      else if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
          || activeNetwork.getType() == ConnectivityManager.TYPE_WIMAX) {
        result = NetworkType.WIFI_WIFIMAX;
      }
      else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
        TelephonyManager manager =
            (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager.getSimState() == TelephonyManager.SIM_STATE_READY) {
          switch (manager.getNetworkType()) {

            // Unknown
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
              result = NetworkType.CELLULAR_UNKNOWN;
              break;
            // Cellular Data 2G
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_IDEN:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
              result = NetworkType.CELLULAR_2G;
              break;
            // Cellular Data 3G
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
              result = NetworkType.CELLULAR_3G;
              break;
            // Cellular Data 4G
            case TelephonyManager.NETWORK_TYPE_LTE:
              result = NetworkType.CELLULAR_4G;
              break;
            // Cellular Data Unknown Generation
            default:
              result = NetworkType.CELLULAR_UNIDENTIFIED_GEN;
              break;
          }
        }
      }
    }
    return result;
  }

  /**
   * Gets WiFi MAC Address
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
   *
   * @return the wifi mac
   */
  @SuppressLint("HardwareIds")
  @RequiresPermission(Manifest.permission.ACCESS_WIFI_STATE)
  public final String getWifiMAC() {
    String result = "02:00:00:00:00:00";
    if (PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_WIFI_STATE)) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        // Hardware ID are restricted in Android 6+
        // https://developer.android.com/about/versions/marshmallow/android-6.0-changes.html#behavior-hardware-id
        Enumeration<NetworkInterface> interfaces = null;
        try {
          interfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
          if (EasyDeviceInfo.debuggable) {
            Log.e(EasyDeviceInfo.nameOfLib, SOCKET_EXCEPTION, e);
          }
        }
        while (interfaces != null && interfaces.hasMoreElements()) {
          NetworkInterface networkInterface = interfaces.nextElement();

          byte[] addr = new byte[0];
          try {
            addr = networkInterface.getHardwareAddress();
          } catch (SocketException e) {
            if (EasyDeviceInfo.debuggable) {
              Log.e(EasyDeviceInfo.nameOfLib, SOCKET_EXCEPTION, e);
            }
          }
          if (addr == null || addr.length == 0) {
            continue;
          }

          StringBuilder buf = new StringBuilder();
          for (byte b : addr) {
            buf.append(String.format("%02X:", b));
          }
          if (buf.length() > 0) {
            buf.deleteCharAt(buf.length() - 1);
          }
          String mac = buf.toString();
          String wifiInterfaceName = "wlan0";
          result = wifiInterfaceName.equals(networkInterface.getName()) ? mac : result;
        }
      }
      else {
        WifiManager wm =
            (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        result = wm.getConnectionInfo().getMacAddress();
      }
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets SSID of Connected WiFi
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
   * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
   *
   * @return Returns the service set identifier (SSID) of the current 802.11 network
   */
  @RequiresPermission(allOf = {
      Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE
  })
  public final String getWifiSSID() {
    String result = null;
    if (PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_WIFI_STATE)) {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = cm.getActiveNetworkInfo();
      if (networkInfo == null) {
        result = null;
      }

      if (networkInfo != null && networkInfo.isConnected()) {
        final WifiManager wifiManager =
            (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
          result = connectionInfo.getSSID();
        }
      }
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets BSSID of Connected WiFi
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
   * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
   *
   * @return Return the basic service set identifier (BSSID) of the current access point.
   */
  @RequiresPermission(allOf = {
      Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE
  })
  public final String getWifiBSSID() {
    String result = null;
    if (PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_WIFI_STATE)) {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = cm.getActiveNetworkInfo();
      if (networkInfo == null) {
        result = null;
      }

      if (networkInfo != null && networkInfo.isConnected()) {
        final WifiManager wifiManager =
            (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
          result = connectionInfo.getBSSID();
        }
      }
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets Link Speed of Connected WiFi
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.ACCESS_WIFI_STATE"/>
   * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
   *
   * @return link speed
   */
  @RequiresPermission(allOf = {
      Manifest.permission.ACCESS_WIFI_STATE, Manifest.permission.ACCESS_NETWORK_STATE
  })
  public final String getWifiLinkSpeed() {
    String result = null;
    if (PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_WIFI_STATE)) {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo networkInfo = cm.getActiveNetworkInfo();
      if (networkInfo == null) {
        result = null;
      }

      if (networkInfo != null && networkInfo.isConnected()) {
        final WifiManager wifiManager =
            (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null && !TextUtils.isEmpty(connectionInfo.getSSID())) {
          result = connectionInfo.getLinkSpeed() + " Mbps";
        }
      }
    }
    return CheckValidityUtil.checkValidData(result);
  }
}
