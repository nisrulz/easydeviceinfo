package github.nisrulz.easydeviceinfo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import java.io.File;
import java.util.Locale;

/**
 * The type Easy di device mod.
 */
public class EasyDIDeviceMod {
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
  private final TelephonyManager tm;
  private Context context;

  /**
   * Instantiates a new Easy di device mod.
   *
   * @param context the context
   */
  public EasyDIDeviceMod(Context context) {
    this.context = context;
    tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
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

    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets product.
   *
   * @return the product
   */
  public String getProduct() {
    return CheckValidityUtil.checkValidData(Build.PRODUCT);
  }

  /**
   * Gets fingerprint.
   *
   * @return the fingerprint
   */
  public String getFingerprint() {
    return CheckValidityUtil.checkValidData(Build.FINGERPRINT);
  }

  /**
   * Gets hardware.
   *
   * @return the hardware
   */
  public String getHardware() {
    return CheckValidityUtil.checkValidData(Build.HARDWARE);
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
    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets device.
   *
   * @return the device
   */
  public String getDevice() {
    return CheckValidityUtil.checkValidData(Build.DEVICE);
  }

  /**
   * Gets bootloader.
   *
   * @return the bootloader
   */
  public String getBootloader() {
    return CheckValidityUtil.checkValidData(Build.BOOTLOADER);
  }

  /**
   * Gets board.
   *
   * @return the board
   */
  public String getBoard() {
    return CheckValidityUtil.checkValidData(Build.BOARD);
  }

  /**
   * Gets display version.
   *
   * @return the display version
   */
  public String getDisplayVersion() {
    return CheckValidityUtil.checkValidData(Build.DISPLAY);
  }

  /**
   * Gets language.
   *
   * @return the language
   */
  public String getLanguage() {
    return CheckValidityUtil.checkValidData(Locale.getDefault().getLanguage());
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
   * Gets manufacturer.
   *
   * @return the manufacturer
   */
  public String getManufacturer() {
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(Build.MANUFACTURER));
  }

  /**
   * Gets model.
   *
   * @return the model
   */
  public String getModel() {
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(Build.MODEL));
  }

  /**
   * Gets build brand.
   *
   * @return the build brand
   */
  public String getBuildBrand() {
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(Build.BRAND));
  }

  /**
   * Gets build host.
   *
   * @return the build host
   */
  public String getBuildHost() {
    return CheckValidityUtil.checkValidData(Build.HOST);
  }

  /**
   * Gets build tags.
   *
   * @return the build tags
   */
  public String getBuildTags() {
    return CheckValidityUtil.checkValidData(Build.TAGS);
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
    return CheckValidityUtil.checkValidData(Build.USER);
  }

  /**
   * Gets build version release.
   *
   * @return the build version release
   */
  public String getBuildVersionRelease() {
    return CheckValidityUtil.checkValidData(Build.VERSION.RELEASE);
  }

  /**
   * Gets screen display id.
   *
   * @return the screen display id
   */
  public String getScreenDisplayID() {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    Display display = wm.getDefaultDisplay();
    return CheckValidityUtil.checkValidData(String.valueOf(display.getDisplayId()));
  }

  /**
   * Gets build version codename.
   *
   * @return the build version codename
   */
  public String getBuildVersionCodename() {
    return CheckValidityUtil.checkValidData(Build.VERSION.CODENAME);
  }

  /**
   * Gets build version incremental.
   *
   * @return the build version incremental
   */
  public String getBuildVersionIncremental() {
    return CheckValidityUtil.checkValidData(Build.VERSION.INCREMENTAL);
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
    return CheckValidityUtil.checkValidData(Build.ID);
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

    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets serial.
   *
   * @return the serial
   */
  public String getSerial() {
    return CheckValidityUtil.checkValidData(Build.SERIAL);
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
    return CheckValidityUtil.checkValidData(Build.VERSION.RELEASE);
  }
}
