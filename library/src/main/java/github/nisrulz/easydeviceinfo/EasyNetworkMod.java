package github.nisrulz.easydeviceinfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.List;

/**
 * The type Easy di network mod.
 */
public class EasyNetworkMod {
  private Context context;

  /**
   * The constant UNKNOWN.
   */
  public static final int UNKNOWN = 0;
  /**
   * The constant WIFI_WIFIMAX.
   */
  public static final int WIFI_WIFIMAX = 1;
  /**
   * The constant CELLULAR_UNKNOWN.
   */
  public static final int CELLULAR_UNKNOWN = 2;
  /**
   * The constant CELLULAR_2G.
   */
  public static final int CELLULAR_2G = 3;
  /**
   * The constant CELLULAR_3G.
   */
  public static final int CELLULAR_3G = 4;
  /**
   * The constant CELLULAR_4G.
   */
  public static final int CELLULAR_4G = 5;
  /**
   * The constant CELLULAR_UNIDENTIFIED_GEN.
   */
  public static final int CELLULAR_UNIDENTIFIED_GEN = 6;

  /**
   * Instantiates a new Easy di network mod.
   *
   * @param context the context
   */
  public EasyNetworkMod(Context context) {
    this.context = context;
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
   * Gets ip address v4
   *
   * @return the ip address
   */
  public String getIPv4Address() {
    String result = null;
    try {
      List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
      for (NetworkInterface intf : interfaces) {
        List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
        for (InetAddress addr : addrs) {
          if (!addr.isLoopbackAddress()) {
            String sAddr = addr.getHostAddress().toUpperCase();
            boolean isIPv4 = addr instanceof Inet4Address;
            if (isIPv4) result = sAddr;
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets ip address v6
   *
   * @return the ip address
   */
  public String getIPv6Address() {
    String result = null;
    try {
      List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
      for (NetworkInterface intf : interfaces) {
        List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
        for (InetAddress addr : addrs) {
          if (!addr.isLoopbackAddress()) {
            String sAddr = addr.getHostAddress().toUpperCase();
            boolean isIPv4 = addr instanceof Inet4Address;
            if (!isIPv4) {
              int delim = sAddr.indexOf('%'); // drop ip6 port suffix
              result = delim < 0 ? sAddr : sAddr.substring(0, delim);
            }
          }
        }
      }
    } catch (SocketException e) {
      e.printStackTrace();
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets network type.
   *
   * @return the network type
   */
  @SuppressWarnings("MissingPermission") public int getNetworkType() {
    int networkStatePermission =
        context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE);

    int result = UNKNOWN;

    if (networkStatePermission == PackageManager.PERMISSION_GRANTED) {
      ConnectivityManager cm =
          (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

      NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
      if (activeNetwork == null) {
        result = UNKNOWN;
      } else if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI
          || activeNetwork.getType() == ConnectivityManager.TYPE_WIMAX) {
        result = WIFI_WIFIMAX;
      } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
        TelephonyManager manager =
            (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (manager.getSimState() == TelephonyManager.SIM_STATE_READY) {
          switch (manager.getNetworkType()) {

            // Unknown
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
              result = CELLULAR_UNKNOWN;
              break;
            // Cellular Data–2G
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_IDEN:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
              result = CELLULAR_2G;
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
              result = CELLULAR_3G;
              break;
            // Cellular Data–4G
            case TelephonyManager.NETWORK_TYPE_LTE:
              result = CELLULAR_4G;
              break;
            // Cellular Data–Unknown Generation
            default:
              result = CELLULAR_UNIDENTIFIED_GEN;
              break;
          }
        }
      }
    }
    return result;
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
    return CheckValidityUtil.checkValidData(result);
  }
}
