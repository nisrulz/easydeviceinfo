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
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import github.nisrulz.easydeviceinfo.common.EasyDeviceInfo;
import java.io.File;
import java.util.Locale;

import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;
import static android.telephony.TelephonyManager.PHONE_TYPE_CDMA;
import static android.telephony.TelephonyManager.PHONE_TYPE_GSM;
import static android.telephony.TelephonyManager.PHONE_TYPE_NONE;

/**
 * EasyDevice Mod Class
 */
public class EasyDeviceMod {
  private final TelephonyManager tm;
  private final Context context;

  /**
   * Instantiates a new Easy  device mod.
   *
   * @param context
   *     the context
   */
  public EasyDeviceMod(final Context context) {
    this.context = context;
    tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
  }

  /**
   * Gets phone type.
   *
   * @return the phone type
   */
  @PhoneType
  public final int getPhoneType() {
    switch (tm.getPhoneType()) {
      case PHONE_TYPE_GSM:
        return PhoneType.GSM;

      case PHONE_TYPE_CDMA:
        return PhoneType.CDMA;
      case PHONE_TYPE_NONE:
      default:
        return PhoneType.NONE;
    }
  }

  /**
   * Gets phone no.
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
   *
   * @return the phone no
   */
  @SuppressLint("HardwareIds")
  @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
  public final String getPhoneNo() {
    String result = null;
    if (PermissionUtil.hasPermission(context, Manifest.permission.READ_PHONE_STATE)
        && tm.getLine1Number() != null) {
      result = tm.getLine1Number();
    }

    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets product.
   *
   * @return the product
   */
  public final String getProduct() {
    return CheckValidityUtil.checkValidData(Build.PRODUCT);
  }

  /**
   * Gets fingerprint.
   *
   * @return the fingerprint
   */
  public final String getFingerprint() {
    return CheckValidityUtil.checkValidData(Build.FINGERPRINT);
  }

  /**
   * Gets hardware.
   *
   * @return the hardware
   */
  public final String getHardware() {
    return CheckValidityUtil.checkValidData(Build.HARDWARE);
  }

  /**
   * Gets radio ver.
   *
   * @return the radio ver
   */
  public final String getRadioVer() {
    String result = null;
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
      result = Build.getRadioVersion();
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets device.
   *
   * @return the device
   */
  public final String getDevice() {
    return CheckValidityUtil.checkValidData(Build.DEVICE);
  }

  /**
   * Gets bootloader.
   *
   * @return the bootloader
   */
  public final String getBootloader() {
    return CheckValidityUtil.checkValidData(Build.BOOTLOADER);
  }

  /**
   * Gets board.
   *
   * @return the board
   */
  public final String getBoard() {
    return CheckValidityUtil.checkValidData(Build.BOARD);
  }

  /**
   * Gets display version.
   *
   * @return the display version
   */
  public final String getDisplayVersion() {
    return CheckValidityUtil.checkValidData(Build.DISPLAY);
  }

  /**
   * Gets language.
   *
   * @return the language
   */
  public final String getLanguage() {
    return CheckValidityUtil.checkValidData(Locale.getDefault().getLanguage());
  }

  /**
   * Device type int.
   * Based on metric : https://design.google.com/devices/
   *
   * @param activity
   *     the activity
   *
   * @return the int
   */

  @DeviceType
  public final int getDeviceType() {
    DisplayMetrics metrics = new Resources.getSystem().getDisplayMetrics();
    activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
    float yInches = metrics.heightPixels / metrics.ydpi;
    float xInches = metrics.widthPixels / metrics.xdpi;
    double diagonalInches = Math.sqrt(xInches * xInches + yInches * yInches);
    if (diagonalInches > 10.1) {
      return DeviceType.TV;
    } else if (diagonalInches <= 10.1 && diagonalInches > 7) {
      return DeviceType.TABLET;
    } else if (diagonalInches <= 7 && diagonalInches > 6.5) {
      return DeviceType.PHABLET;
    } else if (diagonalInches <= 6.5 && diagonalInches >= 2) {
      return DeviceType.PHONE;
    } else {
      return DeviceType.WATCH;
    }
  }

  /**
   * Gets manufacturer.
   *
   * @return the manufacturer
   */
  public final String getManufacturer() {
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(Build.MANUFACTURER));
  }

  /**
   * Gets model.
   *
   * @return the model
   */
  public final String getModel() {
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(Build.MODEL));
  }

  /**
   * Gets build brand.
   *
   * @return the build brand
   */
  public final String getBuildBrand() {
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(Build.BRAND));
  }

  /**
   * Gets build host.
   *
   * @return the build host
   */
  public final String getBuildHost() {
    return CheckValidityUtil.checkValidData(Build.HOST);
  }

  /**
   * Gets build tags.
   *
   * @return the build tags
   */
  public final String getBuildTags() {
    return CheckValidityUtil.checkValidData(Build.TAGS);
  }

  /**
   * Gets build time.
   *
   * @return the build time
   */
  public final long getBuildTime() {
    return Build.TIME;
  }

  /**
   * Gets build user.
   *
   * @return the build user
   */
  public final String getBuildUser() {
    return CheckValidityUtil.checkValidData(Build.USER);
  }

  /**
   * Gets build version release.
   *
   * @return the build version release
   */
  public final String getBuildVersionRelease() {
    return CheckValidityUtil.checkValidData(Build.VERSION.RELEASE);
  }

  /**
   * Gets screen display id.
   *
   * @return the screen display id
   */
  public final String getScreenDisplayID() {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    if (wm != null) {
      Display display = wm.getDefaultDisplay();
      return CheckValidityUtil.checkValidData(String.valueOf(display.getDisplayId()));
    }
    return CheckValidityUtil.checkValidData("");
  }

  /**
   * Gets build version codename.
   *
   * @return the build version codename
   */
  public final String getBuildVersionCodename() {
    return CheckValidityUtil.checkValidData(Build.VERSION.CODENAME);
  }

  /**
   * Gets build version incremental.
   *
   * @return the build version incremental
   */
  public final String getBuildVersionIncremental() {
    return CheckValidityUtil.checkValidData(Build.VERSION.INCREMENTAL);
  }

  /**
   * Gets build version sdk.
   *
   * @return the build version sdk
   */
  public final int getBuildVersionSDK() {
    return Build.VERSION.SDK_INT;
  }

  /**
   * Gets build id.
   *
   * @return the build id
   */
  public final String getBuildID() {
    return CheckValidityUtil.checkValidData(Build.ID);
  }

  /**
   * Is Device rooted boolean.
   *
   * @return the boolean
   */
  public final boolean isDeviceRooted() {
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
   * Gets IMEI number
   *
   * You need to declare the below permission in the manifest file to use this properly
   *
   * <uses-permission android:name="android.permission.READ_PHONE_STATE"/>
   *
   * @return the imei
   *
   * @deprecated
   */
  @SuppressLint("HardwareIds")
  @RequiresPermission(Manifest.permission.READ_PHONE_STATE)
  @Deprecated
  public final String getIMEI() {
    String result = null;
    if (PermissionUtil.hasPermission(context, Manifest.permission.READ_PHONE_STATE)) {
      result = tm.getDeviceId();
    }

    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets serial.
   *
   * @return the serial
   */
  @SuppressLint("HardwareIds")
  public final String getSerial() {
    String result = null;
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
      result = Build.SERIAL;
    } else {
      if (PermissionUtil.hasPermission(context, Manifest.permission.READ_PHONE_STATE)) {
        result = Build.getSerial();
      }
    }
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets os codename.
   *
   * @return the os codename
   */
  public final String getOSCodename() {
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
      case Build.VERSION_CODES.N:
      case Build.VERSION_CODES.N_MR1:
        codename = "Nougat";
        break;
      case Build.VERSION_CODES.O:
        codename = "O";
        break;
      default:
        codename = EasyDeviceInfo.notFoundVal;
        break;
    }
    return codename;
  }

  /**
   * Gets os version.
   *
   * @return the os version
   */
  public final String getOSVersion() {
    return CheckValidityUtil.checkValidData(Build.VERSION.RELEASE);
  }

  /**
   * Gets orientation.
   *
   * @param activity
   *     the activity
   *
   * @return the orientation
   */
  @OrientationType
  public final int getOrientation(final Activity activity) {
    switch (activity.getResources().getConfiguration().orientation) {
      case ORIENTATION_PORTRAIT:
        return OrientationType.PORTRAIT;
      case ORIENTATION_LANDSCAPE:
        return OrientationType.LANDSCAPE;
      default:
        return OrientationType.UNKNOWN;
    }
  }
}
