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

package github.nisrulz.easydeviceinfo;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/**
 * Easy device info class
 */
public class EasyDeviceInfo {

  private final Context context;
  private final TelephonyManager tm;

  /**
   * The constant RINGER_MODE_SILENT.
   */
  public static final int RINGER_MODE_SILENT = 0;
  /**
   * The constant RINGER_MODE_NORMAL.
   */
  public static final int RINGER_MODE_NORMAL = 1;
  /**
   * The constant RINGER_MODE_VIBRATE.
   */
  public static final int RINGER_MODE_VIBRATE = 2;

  /**
   * The constant DEVICE_TYPE_WATCH.
   */
  public static final int DEVICE_TYPE_WATCH = 0;
  /**
   * The constant DEVICE_TYPE_PHONE.
   */
  public static final int DEVICE_TYPE_PHONE = 1;
  /**
   * The constant DEVICE_TYPE_PHABLET.
   */
  public static final int DEVICE_TYPE_PHABLET = 2;
  /**
   * The constant DEVICE_TYPE_TABLET.
   */
  public static final int DEVICE_TYPE_TABLET = 3;
  /**
   * The constant DEVICE_TYPE_TV.
   */
  public static final int DEVICE_TYPE_TV = 4;

  /**
   * Instantiates a new Easy device info.
   *
   * @param context the context
   */
  public EasyDeviceInfo(Context context) {
    this.context = context;
    tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
  }

  /**
   * Gets library version.
   *
   * @return the library version
   */
  public String getLibraryVersion() {
    String version = "1.1.9";
    int versionCode = 11;
    return version + "-" + versionCode;
  }

  /**
   * Gets android id.
   *
   * @return the android id
   */
  public String getAndroidID() {
    return checkValidData(
        Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
  }

  /**
   * Gets model.
   *
   * @return the model
   */
  public String getModel() {
    return checkValidData(handleIllegalCharacterInResult(Build.MODEL));
  }

  /**
   * Gets build brand.
   *
   * @return the build brand
   */
  public String getBuildBrand() {
    return checkValidData(handleIllegalCharacterInResult(Build.BRAND));
  }

  /**
   * Gets build host.
   *
   * @return the build host
   */
  public String getBuildHost() {
    return checkValidData(Build.HOST);
  }

  /**
   * Gets build tags.
   *
   * @return the build tags
   */
  public String getBuildTags() {
    return checkValidData(Build.TAGS);
  }

  /**
   * Gets build time.
   *
   * @return the build time
   */
  public long getBuildTime() {
    return Build.TIME;
  }

  /**
   * Gets build user.
   *
   * @return the build user
   */
  public String getBuildUser() {
    return checkValidData(Build.USER);
  }

  /**
   * Gets build version release.
   *
   * @return the build version release
   */
  public String getBuildVersionRelease() {
    return checkValidData(Build.VERSION.RELEASE);
  }

  /**
   * Gets screen display id.
   *
   * @return the screen display id
   */
  public String getScreenDisplayID() {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    return checkValidData(String.valueOf(display.getDisplayId()));
  }

  /**
   * Gets build version codename.
   *
   * @return the build version codename
   */
  public String getBuildVersionCodename() {
    return checkValidData(Build.VERSION.CODENAME);
  }

  /**
   * Gets build version incremental.
   *
   * @return the build version incremental
   */
  public String getBuildVersionIncremental() {
    return checkValidData(Build.VERSION.INCREMENTAL);
  }

  /**
   * Gets build version sdk.
   *
   * @return the build version sdk
   */
  public int getBuildVersionSDK() {
    return Build.VERSION.SDK_INT;
  }

  /**
   * Gets build id.
   *
   * @return the build id
   */
  public String getBuildID() {
    return checkValidData(Build.ID);
  }

  /**
   * Is Device rooted boolean
   *
   * @return the boolean
   */
  public boolean isDeviceRooted() {
    String su = "su";
    String[] locations = {
        "/sbin/", "/system/bin/", "/system/xbin/", "/system/sd/xbin/", "/system/bin/failsafe/",
        "/data/local/xbin/", "/data/local/bin/", "/data/local/"
    };
    for (String location : locations) {
      if (new File(location + su).exists()) {
        return true;
      }
    }
    return false;
  }

  /**
   * Get supported abis string [ ].
   *
   * @return the string [ ]
   */
  public String[] getSupportedABIS() {
    String[] result = new String[] { "-" };
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      result = Build.SUPPORTED_ABIS;
    }
    return checkValidData(result);
  }

  /**
   * Gets string supported abis.
   *
   * @return the string supported abis
   */
  public String getStringSupportedABIS() {
    String result = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      String[] supportedABIS = Build.SUPPORTED_ABIS;
      StringBuilder supportedABIString = new StringBuilder();
      if (supportedABIS.length > 0) {
        for (String abis : supportedABIS) {
          supportedABIString.append(abis).append("_");
        }
        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"));
      } else {
        supportedABIString.append("");
      }
      result = supportedABIString.toString();
    }
    return checkValidData(handleIllegalCharacterInResult(result));
  }

  /**
   * Gets string supported 32 bit abis.
   *
   * @return the string supported 32 bit abis
   */
  public String getStringSupported32bitABIS() {
    String result = null;

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      String[] supportedABIS = Build.SUPPORTED_32_BIT_ABIS;

      StringBuilder supportedABIString = new StringBuilder();
      if (supportedABIS.length > 0) {
        for (String abis : supportedABIS) {
          supportedABIString.append(abis).append("_");
        }
        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"));
      } else {
        supportedABIString.append("");
      }

      result = supportedABIString.toString();
    }

    return checkValidData(handleIllegalCharacterInResult(result));
  }

  /**
   * Gets string supported 64 bit abis.
   *
   * @return the string supported 64 bit abis
   */
  public String getStringSupported64bitABIS() {
    String result = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      String[] supportedABIS = Build.SUPPORTED_64_BIT_ABIS;

      StringBuilder supportedABIString = new StringBuilder();
      if (supportedABIS.length > 0) {
        for (String abis : supportedABIS) {
          supportedABIString.append(abis).append("_");
        }
        supportedABIString.deleteCharAt(supportedABIString.lastIndexOf("_"));
      } else {
        supportedABIString.append("");
      }
      result = supportedABIString.toString();
    }
    return checkValidData(handleIllegalCharacterInResult(result));
  }

  /**
   * Get supported 32 bit abis string [ ].
   *
   * @return the string [ ]
   */
  public String[] getSupported32bitABIS() {
    String[] result = new String[] { "-" };
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      result = Build.SUPPORTED_32_BIT_ABIS;
    }
    return checkValidData(result);
  }

  /**
   * Get supported 64 bit abis string [ ].
   *
   * @return the string [ ]
   */
  public String[] getSupported64bitABIS() {
    String[] result = new String[] { "-" };
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      result = Build.SUPPORTED_64_BIT_ABIS;
    }
    return checkValidData(result);
  }

  /**
   * Gets manufacturer.
   *
   * @return the manufacturer
   */
  public String getManufacturer() {
    return checkValidData(handleIllegalCharacterInResult(Build.MANUFACTURER));
  }

  /**
   * Gets resolution.
   *
   * @return the resolution
   */
  public String getResolution() {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    DisplayMetrics metrics = new DisplayMetrics();
    display.getMetrics(metrics);
    return checkValidData(metrics.heightPixels + "x" + metrics.widthPixels);
  }

  /**
   * Device type int.
   * Based on metric : https://design.google.com/devices/
   *
   * @param activity the activity
   * @return the int
   */
  public int getDeviceType(Activity activity) {
    DisplayMetrics metrics = new DisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);

    float yInches = metrics.heightPixels / metrics.ydpi;
    float xInches = metrics.widthPixels / metrics.xdpi;
    double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
    if (diagonalInches > 10.1) {
      return DEVICE_TYPE_TV;
    } else if (diagonalInches <= 10.1 && diagonalInches > 7) {
      return DEVICE_TYPE_TABLET;
    } else if (diagonalInches <= 7 && diagonalInches > 6.5) {
      return DEVICE_TYPE_PHABLET;
    } else if (diagonalInches <= 6.5 && diagonalInches >= 2) {
      return DEVICE_TYPE_PHONE;
    } else {
      return DEVICE_TYPE_WATCH;
    }
  }

  /**
   * Gets carrier.
   *
   * @return the carrier
   */
  public String getCarrier() {
    String result = null;
    if (tm != null && tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) {
      result = tm.getNetworkOperatorName().toLowerCase(Locale.getDefault());
    }
    return checkValidData(handleIllegalCharacterInResult(result));
  }

  /**
   * Gets device.
   *
   * @return the device
   */
  public String getDevice() {
    return checkValidData(Build.DEVICE);
  }

  /**
   * Gets bootloader.
   *
   * @return the bootloader
   */
  public String getBootloader() {
    return checkValidData(Build.BOOTLOADER);
  }

  /**
   * Gets board.
   *
   * @return the board
   */
  public String getBoard() {
    return checkValidData(Build.BOARD);
  }

  /**
   * Gets display version.
   *
   * @return the display version
   */
  public String getDisplayVersion() {
    return checkValidData(Build.DISPLAY);
  }

  /**
   * Gets language.
   *
   * @return the language
   */
  public String getLanguage() {
    return checkValidData(Locale.getDefault().getLanguage());
  }

  /**
   * Gets country.
   *
   * @return the country
   */
  public String getCountry() {
    String result;
    if (tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
      result = tm.getSimCountryIso().toLowerCase(Locale.getDefault());
    } else {
      Locale locale = Locale.getDefault();
      result = locale.getCountry().toLowerCase(locale);
    }
    return checkValidData(handleIllegalCharacterInResult(result));
  }

  /**
   * Gets battery percentage
   *
   * @return the battery percentage
   */
  public int getBatteryPercentage() {
    int percentage = 0;
    Intent batteryStatus = getBatteryStatusIntent();
    if (batteryStatus != null) {
      int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
      int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
      percentage = (int) ((level / (float) scale) * 100);
    }

    return percentage;
  }

  /**
   * Is device charging boolean.
   *
   * @return is battery charging boolean
   */
  public boolean isDeviceCharging() {
    Intent batteryStatus = getBatteryStatusIntent();
    int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
    return (status == BatteryManager.BATTERY_STATUS_CHARGING
        || status == BatteryManager.BATTERY_STATUS_FULL);
  }

  /**
   * Is device charging usb boolean.
   *
   * @return is battery charging via USB boolean
   */
  public boolean isDeviceChargingUSB() {
    Intent batteryStatus = getBatteryStatusIntent();
    int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
    return (chargePlug == BatteryManager.BATTERY_PLUGGED_USB);
  }

  /**
   * Is device charging ac boolean.
   *
   * @return is battery charging via AC boolean
   */
  public boolean isDeviceChargingAC() {
    Intent batteryStatus = getBatteryStatusIntent();
    int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
    return (chargePlug == BatteryManager.BATTERY_PLUGGED_AC);
  }

  /**
   * Gets network type.
   *
   * @return the network type
   */
  @SuppressWarnings("MissingPermission") public String getNetworkType() {
    int networkStatePermission =
        context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE);

    String result = null;

    if (networkStatePermission == PackageManager.PERMISSION_GRANTED) {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
      if (activeNetwork == null) {
        result = "Unknown";
      } else if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
          || activeNetwork.getType() == ConnectivityManager.TYPE_WIMAX) {
        result = "Wifi/WifiMax";
      } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
        TelephonyManager manager =
            (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager.getSimState() == TelephonyManager.SIM_STATE_READY) {
          switch (manager.getNetworkType()) {

            // Unknown
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
              result = "Cellular - Unknown";
              break;
            // Cellular Data–2G
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_IDEN:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
              result = "Cellular - 2G";
              break;
            // Cellular Data–3G
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
              result = "Cellular - 3G";
              break;
            // Cellular Data–4G
            case TelephonyManager.NETWORK_TYPE_LTE:
              result = "Cellular - 4G";
              break;
            // Cellular Data–Unknown Generation
            default:
              result = "Cellular - Unknown Generation";
              break;
          }
        }
      }
    }
    return checkValidData(handleIllegalCharacterInResult(result));
  }

  /**
   * Gets os codename.
   *
   * @return the os codename
   */
  public String getOSCodename() {
    String codename;
    switch (Build.VERSION.SDK_INT) {
      case Build.VERSION_CODES.BASE:
        codename = "First Android Version. Yay !";
        break;
      case Build.VERSION_CODES.BASE_1_1:
        codename = "Base Android 1.1";
        break;
      case Build.VERSION_CODES.CUPCAKE:
        codename = "Cupcake";
        break;
      case Build.VERSION_CODES.DONUT:
        codename = "Donut";
        break;
      case Build.VERSION_CODES.ECLAIR:
      case Build.VERSION_CODES.ECLAIR_0_1:
      case Build.VERSION_CODES.ECLAIR_MR1:

        codename = "Eclair";
        break;
      case Build.VERSION_CODES.FROYO:
        codename = "Froyo";
        break;
      case Build.VERSION_CODES.GINGERBREAD:
      case Build.VERSION_CODES.GINGERBREAD_MR1:
        codename = "Gingerbread";
        break;
      case Build.VERSION_CODES.HONEYCOMB:
      case Build.VERSION_CODES.HONEYCOMB_MR1:
      case Build.VERSION_CODES.HONEYCOMB_MR2:
        codename = "Honeycomb";
        break;
      case Build.VERSION_CODES.ICE_CREAM_SANDWICH:
      case Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1:
        codename = "Ice Cream Sandwich";
        break;
      case Build.VERSION_CODES.JELLY_BEAN:
      case Build.VERSION_CODES.JELLY_BEAN_MR1:
      case Build.VERSION_CODES.JELLY_BEAN_MR2:
        codename = "Jelly Bean";
        break;
      case Build.VERSION_CODES.KITKAT:
        codename = "Kitkat";
        break;
      case Build.VERSION_CODES.KITKAT_WATCH:
        codename = "Kitkat Watch";
        break;
      case Build.VERSION_CODES.LOLLIPOP:
      case Build.VERSION_CODES.LOLLIPOP_MR1:
        codename = "Lollipop";
        break;
      case Build.VERSION_CODES.M:
        codename = "Marshmallow";
        break;
      default:
        codename = "NA";
        break;
    }
    return codename;
  }

  /**
   * Gets os version.
   *
   * @return the os version
   */
  public String getOSVersion() {
    return checkValidData(Build.VERSION.RELEASE);
  }

  /**
   * Gets wifi mac.
   *
   * @return the wifi mac
   */
  @SuppressWarnings("MissingPermission") public String getWifiMAC() {
    String result = null;
    if (context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_WIFI_STATE)
        == PackageManager.PERMISSION_GRANTED) {
      WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
      result = wm.getConnectionInfo().getMacAddress();
    }
    return checkValidData(result);
  }

  /**
   * Gets imei.
   *
   * @return the imei
   */
  @SuppressWarnings("MissingPermission") public String getIMEI() {
    String result = null;
    boolean hasReadPhoneStatePermission =
        context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE)
            == PackageManager.PERMISSION_GRANTED;
    if (hasReadPhoneStatePermission) result = tm.getDeviceId();

    return checkValidData(result);
  }

  /**
   * Gets imsi.
   *
   * @return the imsi
   */
  @SuppressWarnings("MissingPermission") public String getIMSI() {
    String result = null;
    boolean hasReadPhoneStatePermission =
        context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE)
            == PackageManager.PERMISSION_GRANTED;
    if (hasReadPhoneStatePermission) result = tm.getSubscriberId();

    return checkValidData(result);
  }

  /**
   * Gets serial.
   *
   * @return the serial
   */
  public String getSerial() {
    return checkValidData(Build.SERIAL);
  }

  /**
   * Gets sim serial.
   *
   * @return the sim serial
   */
  @SuppressWarnings("MissingPermission") public String getSIMSerial() {
    String result = null;
    boolean hasReadPhoneStatePermission =
        context.checkCallingOrSelfPermission(Manifest.permission.READ_PHONE_STATE)
            == PackageManager.PERMISSION_GRANTED;
    if (hasReadPhoneStatePermission) result = tm.getSimSerialNumber();
    return checkValidData(result);
  }

  /**
   * Gets gsfid.
   *
   * @return the gsfid
   */
  @SuppressWarnings("MissingPermission") public String getGSFID() {
    final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
    final String ID_KEY = "android_id";

    String[] params = { ID_KEY };
    Cursor c = context.getContentResolver().query(URI, null, null, params, null);

    if (c == null) {
      return "NA";
    } else if (!c.moveToFirst() || c.getColumnCount() < 2) {
      c.close();
      return "NA";
    }

    try {
      String gsfID = Long.toHexString(Long.parseLong(c.getString(1)));
      c.close();
      return gsfID;
    } catch (NumberFormatException e) {
      c.close();
      return "NA";
    }
  }

  /**
   * Gets bluetooth mac.
   *
   * @return the bluetooth mac
   */
  @SuppressWarnings("MissingPermission") public String getBluetoothMAC() {
    String result = null;
    if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH)
        == PackageManager.PERMISSION_GRANTED) {
      BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
      result = bta.getAddress();
    }
    return checkValidData(result);
  }

  /**
   * Gets psuedo unique id.
   *
   * @return the psuedo unique id
   */
  public String getPseudoUniqueID() {
    // If all else fails, if the user does have lower than API 9 (lower
    // than Gingerbread), has reset their phone or 'Secure.ANDROID_ID'
    // returns 'null', then simply the ID returned will be solely based
    // off their Android device information. This is where the collisions
    // can happen.
    // Try not to use DISPLAY, HOST or ID - these items could change.
    // If there are collisions, there will be overlapping data
    String devIDShort = "35" +
        (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      devIDShort += (Build.SUPPORTED_ABIS[0].length() % 10);
    } else {
      devIDShort += (Build.CPU_ABI.length() % 10);
    }

    devIDShort +=
        (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length()
            % 10) + (Build.PRODUCT.length() % 10);

    // Only devices with API >= 9 have android.os.Build.SERIAL
    // http://developer.android.com/reference/android/os/Build.html#SERIAL
    // If a user upgrades software or roots their phone, there will be a duplicate entry
    String serial;
    try {
      serial = Build.class.getField("SERIAL").get(null).toString();

      // Go ahead and return the serial for api => 9
      return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
    } catch (Exception e) {
      // String needs to be initialized
      serial = "ESYDV000"; // some value
    }

    // Finally, combine the values we have found by using the UUID class to create a unique identifier
    return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
  }

  /**
   * Gets phone no.
   *
   * @return the phone no
   */
  @SuppressWarnings("MissingPermission") public String getPhoneNo() {
    String result = null;
    boolean hasReadPhoneStatePermission =
        context.checkCallingOrSelfPermission(Manifest.permission.READ_SMS)
            == PackageManager.PERMISSION_GRANTED;
    if (hasReadPhoneStatePermission && tm.getLine1Number() != null) {
      result = tm.getLine1Number();
    }

    return checkValidData(result);
  }

  /**
   * Gets product.
   *
   * @return the product
   */
  public String getProduct() {
    return checkValidData(Build.PRODUCT);
  }

  /**
   * Gets fingerprint.
   *
   * @return the fingerprint
   */
  public String getFingerprint() {
    return checkValidData(Build.FINGERPRINT);
  }

  /**
   * Gets hardware.
   *
   * @return the hardware
   */
  public String getHardware() {
    return checkValidData(Build.HARDWARE);
  }

  /**
   * Gets radio ver.
   *
   * @return the radio ver
   */
  public String getRadioVer() {
    String result = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      result = Build.getRadioVersion();
    }
    return checkValidData(result);
  }

  /**
   * Gets ip address.
   *
   * @param useIPv4 the use i pv 4
   * @return the ip address
   */
  public String getIPAddress(boolean useIPv4) {
    String result = null;
    try {
      List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
      for (NetworkInterface intf : interfaces) {
        List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
        for (InetAddress addr : addrs) {
          if (!addr.isLoopbackAddress()) {
            String sAddr = addr.getHostAddress().toUpperCase();
            boolean isIPv4 = addr instanceof Inet4Address;
            if (useIPv4) {
              if (isIPv4) result = sAddr;
            } else {
              if (!isIPv4) {
                int delim = sAddr.indexOf('%'); // drop ip6 port suffix
                result = delim < 0 ? sAddr : sAddr.substring(0, delim);
              }
            }
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return checkValidData(result);
  }

  /**
   * Gets ua.
   *
   * @return the ua
   */
  public String getUA() {
    final String system_ua = System.getProperty("http.agent");
    String result;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      result = new WebView(context).getSettings().getDefaultUserAgent(context) +
          "__" + system_ua;
    } else {
      result = new WebView(context).getSettings().getUserAgentString() +
          "__" + system_ua;
    }
    return checkValidData(result);
  }

  /**
   * Get lat long double [ ].
   *
   * @return the double [ ]
   */
  @SuppressWarnings("MissingPermission") public double[] getLatLong() {
    boolean hasFineLocationPermission =
        context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED ? true : false;
    boolean isGPSEnabled, isNetworkEnabled;

    double[] gps = new double[2];
    gps[0] = 0;
    gps[1] = 0;
    if (hasFineLocationPermission) {
      LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

      isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
      isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

      Location net_loc = null, gps_loc = null, final_loc = null;

      if (isGPSEnabled) {
        gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      }
      if (isNetworkEnabled) {
        net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
      }

      if (gps_loc != null && net_loc != null) {
        if (gps_loc.getAccuracy() >= net_loc.getAccuracy()) {
          final_loc = gps_loc;
        } else {
          final_loc = net_loc;
        }
      } else {
        if (gps_loc != null) {
          final_loc = gps_loc;
        } else if (net_loc != null) {
          final_loc = net_loc;
        } else {
          // GPS and Network both are null so try passive
          final_loc = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
        }
      }

      if (final_loc != null) {
        gps[0] = final_loc.getLatitude();
        gps[1] = final_loc.getLongitude();
      }

      return gps;
    }
    return gps;
  }

  /**
   * Get display xy coordinates int [ ].
   *
   * @param event the event
   * @return the int [ ]
   */
  public int[] getDisplayXYCoordinates(MotionEvent event) {
    int[] coordinates = new int[2];
    coordinates[0] = 0;
    coordinates[1] = 0;
    if (event.getAction() == MotionEvent.ACTION_DOWN) {
      coordinates[0] = (int) event.getX();     // X Coordinates
      coordinates[1] = (int) event.getY();     // Y Coordinates
    }
    return coordinates;
  }

  /**
   * Gets time.
   *
   * @return the time
   */
  public long getTime() {
    return System.currentTimeMillis();
  }

  /**
   * Gets formatted time.
   *
   * @return the formatted time
   */
  public String getFormattedTime() {

    long millis = System.currentTimeMillis();
    int sec = (int) (millis / 1000) % 60;
    int min = (int) ((millis / (1000 * 60)) % 60);
    int hr = (int) ((millis / (1000 * 60 * 60)) % 24);

    return String.format("%02d:%02d:%02d", hr, min, sec);
  }

  /**
   * Gets app name.
   *
   * @return the app name
   */
  public String getAppName() {
    String result;
    final PackageManager pm = context.getPackageManager();
    ApplicationInfo ai = null;
    try {
      ai = pm.getApplicationInfo(context.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    result = (ai != null ? (String) pm.getApplicationLabel(ai) : null);
    return checkValidData(result);
  }

  /**
   * Gets app version.
   *
   * @return the app version
   */
  public String getAppVersion() {
    String result = null;
    try {
      result = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return checkValidData(result);
  }

  /**
   * Gets app version code.
   *
   * @return the app version code
   */
  public String getAppVersionCode() {
    String result = null;
    try {
      result = String.valueOf(
          context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return checkValidData(result);
  }

  /**
   * Gets activity name.
   *
   * @return the activity name
   */
  public String getActivityName() {
    return checkValidData(context.getClass().getSimpleName());
  }

  /**
   * Gets package name.
   *
   * @return the package name
   */
  public String getPackageName() {
    return checkValidData(context.getPackageName());
  }

  /**
   * Gets store.
   *
   * @return the store
   */
  public String getStore() {
    String result = null;
    if (Build.VERSION.SDK_INT >= 3) {
      result = context.getPackageManager().getInstallerPackageName(context.getPackageName());
    }
    return checkValidData(result);
  }

  /**
   * Gets density.
   *
   * @return the density
   */
  public String getDensity() {
    String densityStr = null;
    final int density = context.getResources().getDisplayMetrics().densityDpi;
    switch (density) {
      case DisplayMetrics.DENSITY_LOW:
        densityStr = "LDPI";
        break;
      case DisplayMetrics.DENSITY_MEDIUM:
        densityStr = "MDPI";
        break;
      case DisplayMetrics.DENSITY_TV:
        densityStr = "TVDPI";
        break;
      case DisplayMetrics.DENSITY_HIGH:
        densityStr = "HDPI";
        break;
      case DisplayMetrics.DENSITY_XHIGH:
        densityStr = "XHDPI";
        break;
      case DisplayMetrics.DENSITY_400:
        densityStr = "XMHDPI";
        break;
      case DisplayMetrics.DENSITY_XXHIGH:
        densityStr = "XXHDPI";
        break;
      case DisplayMetrics.DENSITY_XXXHIGH:
        densityStr = "XXXHDPI";
        break;
    }
    return checkValidData(densityStr);
  }

  /**
   * Get accounts string [ ].
   *
   * @return the string [ ]
   */
  @SuppressWarnings("MissingPermission") public String[] getAccounts() {
    String[] result = null;
    if (context.checkCallingOrSelfPermission(Manifest.permission.GET_ACCOUNTS)
        == PackageManager.PERMISSION_GRANTED) {
      Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");
      result = new String[accounts.length];
      for (int i = 0; i < accounts.length; i++) {
        result[i] = accounts[i].name;
      }
    }
    return checkValidData(result);
  }

  /**
   * Is network available boolean.
   *
   * @return the boolean
   */
  @SuppressWarnings("MissingPermission") public boolean isNetworkAvailable() {
    if (context.checkCallingOrSelfPermission(Manifest.permission.INTERNET)
        == PackageManager.PERMISSION_GRANTED
        && context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE)
        == PackageManager.PERMISSION_GRANTED) {
      ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext()
          .getSystemService(Context.CONNECTIVITY_SERVICE);
      NetworkInfo netInfo = cm.getActiveNetworkInfo();
      return netInfo != null && netInfo.isConnected();
    }
    return false;
  }

  /**
   * Is running on emulator boolean.
   *
   * @return the boolean
   */
  public static boolean isRunningOnEmulator() {
    return Build.BRAND.contains("generic")
        || Build.DEVICE.contains("generic")
        || Build.PRODUCT.contains("sdk")
        || Build.HARDWARE.contains("goldfish")
        || Build.MANUFACTURER.contains("Genymotion")
        || Build.PRODUCT.contains("vbox86p")
        || Build.DEVICE.contains("vbox86p")
        || Build.HARDWARE.contains("vbox86");
  }

  /**
   * Gets android ad id.
   *
   * @param callback the callback
   */
  public void getAndroidAdId(final AdIdentifierCallback callback) {
    new Thread(new Runnable() {
      @Override public void run() {
        AdvertisingIdClient.Info adInfo;
        try {
          adInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
          String androidAdId = adInfo.getId();
          boolean adDoNotTrack = adInfo.isLimitAdTrackingEnabled();
          if (androidAdId == null) {
            androidAdId = "NA";
          }

          //Send Data to callback
          callback.onSuccess(androidAdId, (adDoNotTrack && adDoNotTrack));
        } catch (IOException e) {
          // Unrecoverable error connecting to Google Play services (e.g.,
          // the old version of the service doesn't support getting AdvertisingId).

        } catch (GooglePlayServicesNotAvailableException e) {
          // Google Play services is not available entirely.
        } catch (GooglePlayServicesRepairableException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

  /**
   * Is wifi enabled
   *
   * @return the boolean
   */
  public boolean isWifiEnabled() {
    boolean wifiState = false;

    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
    if (wifiManager != null) {
      wifiState = wifiManager.isWifiEnabled() ? true : false;
    }
    return wifiState;
  }

  /**
   * Gets Device Ringer Mode
   *
   * @return Device Ringer Mode
   */
  public int getDeviceRingerMode() {
    int ringerMode = RINGER_MODE_NORMAL;
    AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
    switch (audioManager.getRingerMode()) {
      case AudioManager.RINGER_MODE_NORMAL:
        ringerMode = RINGER_MODE_NORMAL;
        break;
      case AudioManager.RINGER_MODE_SILENT:
        ringerMode = RINGER_MODE_SILENT;
        break;
      case AudioManager.RINGER_MODE_VIBRATE:
        ringerMode = RINGER_MODE_VIBRATE;
    }
    return ringerMode;
  }

  /**
   * The interface Ad identifier callback.
   */
  public interface AdIdentifierCallback {
    /**
     * On success.
     *
     * @param adIdentifier the ad identifier
     * @param adDonotTrack the ad donot track
     */
    void onSuccess(String adIdentifier, boolean adDonotTrack);
  }

  private String handleIllegalCharacterInResult(String result) {
    if (result.indexOf(" ") > 0) {
      result = result.replaceAll(" ", "_");
    }
    return result;
  }

  private Intent getBatteryStatusIntent() {
    IntentFilter batFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    return context.registerReceiver(null, batFilter);
  }

  private String checkValidData(String data) {
    if (data == null || data.length() == 0) {
      data = "NA";
    }
    return data;
  }

  private String[] checkValidData(String[] data) {
    if (data == null || data.length == 0) {
      data = new String[] { "-" };
    }
    return data;
  }
}
