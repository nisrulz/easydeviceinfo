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
