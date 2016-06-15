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

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

/**
 * The type Easy di battery mod.
 */
public class EasyBatteryMod {

  private final Context context;
  /**
   * The constant CHARGING_VIA_USB.
   */
  public static final int CHARGING_VIA_USB = 0;
  /**
   * The constant CHARGING_VIA_AC.
   */
  public static final int CHARGING_VIA_AC = 1;
  /**
   * The constant CHARGING_VIA_WIRELESS.
   */
  public static final int CHARGING_VIA_WIRELESS = 2;
  /**
   * The constant CHARGING_VIA_UNKNOWN_SOURCE.
   */
  public static final int CHARGING_VIA_UNKNOWN_SOURCE = 3;

  /**
   * The constant HEALTH_HAVING_ISSUES.
   */
  public static final int HEALTH_HAVING_ISSUES = 0;
  /**
   * The constant HEALTH_GOOD.
   */
  public static final int HEALTH_GOOD = 1;

  /**
   * Instantiates a new Easy di battery mod.
   *
   * @param context the context
   */
  public EasyBatteryMod(Context context) {
    this.context = context;
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

  private Intent getBatteryStatusIntent() {
    IntentFilter batFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
    return context.registerReceiver(null, batFilter);
  }

  /**
   * Gets battery health.
   *
   * @return the battery health
   */
  public int getBatteryHealth() {
    int health = HEALTH_HAVING_ISSUES;
    Intent batteryStatus = getBatteryStatusIntent();
    if (batteryStatus != null) {
      health = batteryStatus.getIntExtra(BatteryManager.EXTRA_HEALTH, 0);
      if (health == BatteryManager.BATTERY_HEALTH_GOOD) {
        health = HEALTH_GOOD;
      } else {
        health = HEALTH_HAVING_ISSUES;
      }
    }
    return health;
  }

  /**
   * Gets battery technology.
   *
   * @return the battery technology
   */
  public String getBatteryTechnology() {
    return CheckValidityUtil.checkValidData(
        getBatteryStatusIntent().getExtras().getString(BatteryManager.EXTRA_TECHNOLOGY));
  }

  /**
   * Gets battery temprature.
   *
   * @return the battery temprature
   */
  public float getBatteryTemprature() {
    float temp = 0.0f;
    Intent batteryStatus = getBatteryStatusIntent();
    if (batteryStatus != null) {
      temp = (float) (batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, 0) / 10);
    }
    return temp;
  }

  /**
   * Gets battery voltage.
   *
   * @return the battery voltage
   */
  public int getBatteryVoltage() {
    int volt = 0;
    Intent batteryStatus = getBatteryStatusIntent();
    if (batteryStatus != null) {
      volt = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, 0);
    }
    return volt;
  }

  /**
   * Gets charging source.
   *
   * @return the charging source
   */
  public int getChargingSource() {
    Intent batteryStatus = getBatteryStatusIntent();
    int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);

    switch (chargePlug) {
      case BatteryManager.BATTERY_PLUGGED_AC:
        return CHARGING_VIA_AC;
      case BatteryManager.BATTERY_PLUGGED_USB:
        return CHARGING_VIA_USB;
      case BatteryManager.BATTERY_PLUGGED_WIRELESS:
        return CHARGING_VIA_WIRELESS;
      default:
        return CHARGING_VIA_UNKNOWN_SOURCE;
    }
  }

  /**
   * Is battery present boolean.
   *
   * @return the boolean
   */
  public boolean isBatteryPresent() {
    return getBatteryStatusIntent().getExtras().getBoolean(BatteryManager.EXTRA_PRESENT);
  }
}