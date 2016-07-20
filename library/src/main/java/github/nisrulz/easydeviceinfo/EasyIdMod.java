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
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;
import java.util.UUID;

/**
 * The type Easy  id mod.
 */
public class EasyIdMod {
  private final Context context;

  /**
   * Instantiates a new Easy id mod.
   *
   * @param context the context
   */
  public EasyIdMod(final Context context) {
    this.context = context;
  }

  /**
   * Gets android ad id.
   *
   * @param callback the callback
   */
  public final void getAndroidAdId(final AdIdentifierCallback callback) {
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
          callback.onSuccess(androidAdId, adDoNotTrack);
        } catch (IOException | GooglePlayServicesNotAvailableException e) {
          // Unrecoverable error connecting to Google Play services (e.g.,
          // the old version of the service doesn't support getting AdvertisingId).
          Log.d(EasyDeviceInfo.name, "Google Play Services Not Available Exception", e);
        } catch (GooglePlayServicesRepairableException e) {
          Log.d(EasyDeviceInfo.name, "Google Play Services Repairable Exception", e);
        }
      }
    }).start();
  }

  /**
   * Gets android id.
   *
   * @return the android id
   */
  public final String getAndroidID() {
    return CheckValidityUtil.checkValidData(
        Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID));
  }

  /**
   * Get accounts string [ ].
   *
   * @return the string [ ]
   */
  @SuppressWarnings("MissingPermission") public final String[] getAccounts() {
    String[] result = null;
    if (PermissionUtil.hasPermission(context, Manifest.permission.GET_ACCOUNTS)) {
      Account[] accounts = AccountManager.get(context).getAccountsByType("com.google");
      result = new String[accounts.length];
      for (int i = 0; i < accounts.length; i++) {
        result[i] = accounts[i].name;
      }
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets ua.
   *
   * @return the ua
   */
  public final String getUA() {
    final String systemUa = System.getProperty("http.agent");
    String result;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      result = WebSettings.getDefaultUserAgent(context) + "__" + systemUa;
    } else {
      result = new WebView(context).getSettings().getUserAgentString() + "__" + systemUa;
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets psuedo unique id.
   *
   * @return the psuedo unique id
   */
  @SuppressWarnings("deprecation") public final String getPseudoUniqueID() {
    // If all else fails, if the user does have lower than API 9 (lower
    // than Gingerbread), has reset their phone or 'Secure.ANDROID_ID'
    // returns 'null', then simply the ID returned will be solely based
    // off their Android device information. This is where the collisions
    // can happen.
    // Try not to use DISPLAY, HOST or ID - these items could change.
    // If there are collisions, there will be overlapping data
    String devIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10);

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
   * Gets gsfid.
   *
   * @return the gsfid
   */
  @SuppressWarnings("MissingPermission") public final String getGSFID() {
    final Uri uri = Uri.parse("content://com.google.android.gsf.gservices");
    final String idKey = "android_id";

    String[] params = { idKey };
    Cursor c = context.getContentResolver().query(uri, null, null, params, null);

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
}
