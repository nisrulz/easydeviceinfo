package github.nisrulz.easydeviceinfo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;
import java.util.Locale;

/**
 * The type Easy di sim mod.
 */
public class EasySimMod {
  private final TelephonyManager tm;
  private final Context context;

  /**
   * Instantiates a new Easy di sim mod.
   *
   * @param context the context
   */
  public EasySimMod(Context context) {
    this.context = context;
    tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
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
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(result));
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
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(result));
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

    return CheckValidityUtil.checkValidData(result);
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
    return CheckValidityUtil.checkValidData(result);
  }
}
