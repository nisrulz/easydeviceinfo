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
import android.content.Context;
import android.os.Build;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * The type Easy  sim mod.
 */
public class EasySimMod {
  private final TelephonyManager tm;
  private final Context context;

  /**
   * Instantiates a new Easy  sim mod.
   *
   * @param context
   *     the context
   */
  public EasySimMod(final Context context) {
    this.context = context;
    tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
  }

  /**
   * Gets country.
   *
   * @return the country
   */
  public final String getCountry() {
    String result;
    if (tm != null && tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
      result = tm.getSimCountryIso().toLowerCase(Locale.getDefault());
    }
    else {
      Locale locale = Locale.getDefault();
      result = locale.getCountry().toLowerCase(locale);
    }
    return CheckValidityUtil.checkValidData(
        CheckValidityUtil.handleIllegalCharacterInResult(result));
  }

  /**
   * Is sim network locked.
   *
   * @return the boolean
   */
  public final boolean isSimNetworkLocked() {
    return tm != null ? tm.getSimState() == TelephonyManager.SIM_STATE_NETWORK_LOCKED : false;
  }

  /**
   * Gets carrier.
   *
   * @return the carrier
   */
  public final String getCarrier() {
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
  @SuppressWarnings("MissingPermission")
  public final String getIMSI() {
    String result = null;
    if (tm != null && PermissionUtil.hasPermission(context, Manifest.permission.READ_PHONE_STATE)) {
      result = tm.getSubscriberId();
    }

    return CheckValidityUtil.checkValidData(result);
  }

  /**
   * Gets sim serial.
   *
   * @return the sim serial
   */
  @SuppressWarnings("MissingPermission")
  public final String getSIMSerial() {
    String result = null;
    if (tm != null && PermissionUtil.hasPermission(context, Manifest.permission.READ_PHONE_STATE)) {
      result = tm.getSimSerialNumber();
    }
    return CheckValidityUtil.checkValidData(result);
  }

  @SuppressWarnings("MissingPermission")
  public final List<SubscriptionInfo> getActiveMultiSimInfo() {
    if (PermissionUtil.hasPermission(context, Manifest.permission.READ_PHONE_STATE)) {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        return SubscriptionManager.from(context).getActiveSubscriptionInfoList();
      }
    }
    return new ArrayList<>(0);
  }

  @SuppressWarnings("MissingPermission")
  public final boolean isMultiSim() {
    return getActiveMultiSimInfo().size() > 1 ? true : false;
  }

  @SuppressWarnings("MissingPermission")
  public final int getNumberOfActiveSim() {
    return getActiveMultiSimInfo().size();
  }
}
