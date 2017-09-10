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
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.RequiresPermission;

/**
 * EasyLocation Mod Class
 *
 * You need to declare the below permission in the manifest file to use this properly
 *
 * For Network based location
 * <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION"/>
 *
 * For more accurate location updates via GPS and network both
 * <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION"/>
 */

public class EasyLocationMod {
  private final boolean hasFineLocationPermission;
  private final boolean hasCoarseLocationPermission;
  private LocationManager lm;

  /**
   * Instantiates a new Easy location mod.
   *
   * @param context
   *     the context
   */
  public EasyLocationMod(Context context) {
    hasFineLocationPermission =
        PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_FINE_LOCATION);
    hasCoarseLocationPermission =
        PermissionUtil.hasPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);

    if (hasCoarseLocationPermission || hasFineLocationPermission) {
      lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }
  }

  /**
   * Get lat long double [ ].
   *
   * @return the double [ ]
   */
  @RequiresPermission(anyOf = {
      Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION
  })
  public final double[] getLatLong() {
    double[] gps = new double[2];
    gps[0] = 0;
    gps[1] = 0;

    if (hasCoarseLocationPermission && lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
      Location lastKnownLocation = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
      if (lastKnownLocation != null) {
        gps[0] = lastKnownLocation.getLatitude();
        gps[1] = lastKnownLocation.getLongitude();
      }
    }
    else if (hasFineLocationPermission) {
      boolean isGPSEnabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
      boolean isNetworkEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

      Location lastKnownLocationNetwork = null;
      Location lastKnownLocationGps = null;
      Location betterLastKnownLocation = null;

      if (isNetworkEnabled) {
        lastKnownLocationNetwork = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
      }

      if (isGPSEnabled) {
        lastKnownLocationGps = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      }
      if (lastKnownLocationGps != null && lastKnownLocationNetwork != null) {
        betterLastKnownLocation = getBetterLocation(lastKnownLocationGps, lastKnownLocationNetwork);
      }

      if (betterLastKnownLocation == null) {
        betterLastKnownLocation = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
      }

      if (betterLastKnownLocation != null) {
        gps[0] = betterLastKnownLocation.getLatitude();
        gps[1] = betterLastKnownLocation.getLongitude();
      }
    }
    return gps;
  }

  private Location getBetterLocation(final Location location1, final Location location2) {
    if (location1.getAccuracy() >= location2.getAccuracy()) {
      return location1;
    }
    else {
      return location2;
    }
  }
}
